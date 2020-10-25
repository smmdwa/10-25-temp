package com.duan.wechat_ordering.service.impl;

import com.duan.wechat_ordering.Utils.KeyUtil;
import com.duan.wechat_ordering.converter.OrderMaster2OrderDTO;
import com.duan.wechat_ordering.dto.CartDTO;
import com.duan.wechat_ordering.dto.OrderDTO;
import com.duan.wechat_ordering.entity.OrderDetail;
import com.duan.wechat_ordering.entity.OrderMaster;
import com.duan.wechat_ordering.entity.ProductInfo;
import com.duan.wechat_ordering.enums.OrderStatusEnum;
import com.duan.wechat_ordering.enums.PayStatusEnum;
import com.duan.wechat_ordering.enums.ResultEnum;
import com.duan.wechat_ordering.exception.SellException;
import com.duan.wechat_ordering.repository.OrderDetailRepository;
import com.duan.wechat_ordering.repository.OrderMasterRepository;
import com.duan.wechat_ordering.service.OrderService;
import com.duan.wechat_ordering.service.ProductInfoService;
import com.duan.wechat_ordering.service.WebSocket;
import org.apache.tomcat.util.digester.ObjectCreateRule;
import org.aspectj.weaver.ast.Or;
import org.omg.CORBA.ORB;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OrderImpl implements OrderService {
    @Autowired
    private ProductInfoService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        /** 查询商品数量 价格不能从前端传过来的查询*/

        String orderId=KeyUtil.getKey();//生成随机的KEY
        BigDecimal orderAmount=new BigDecimal(BigInteger.ZERO);
        List<CartDTO>cartDTOList=new ArrayList<>();
        //1.查询商品 数量 价格
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo=productService.findOne(orderDetail.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2. 计算商品总价
            orderAmount=productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //订单详情入库  前端传过来两个 一个商品id  一个数量  那我们现在就要生成订单 需要两个：1.订单ID 2.DetailID
            //用随机数生成订单号
            orderDetail.setDetailId(KeyUtil.getKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);/** 把productInfo的属性拷贝到orderDetail  */
            orderDetailRepository.save(orderDetail);

            CartDTO cartDTO=new CartDTO();
            cartDTO.setProductQuantity(orderDetail.getProductQuantity());
            cartDTO.setProductId(orderDetail.getProductId());
            cartDTOList.add(cartDTO);
        }
        //3.x写入订单数据库  orderMaster 和orderDetail
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderAmount(orderAmount);
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());

        BeanUtils.copyProperties(orderDTO,orderMaster);/** 注意 orderDTO里为null 也会拷贝进orderMaster中 */


        orderMasterRepository.save(orderMaster);


        //4.扣库存

        //新方法 使用lambda表达式来获取 cartDTOList
        //老方法是：  在42-57行中 从orderDetail 中获取数量和商品id
//        orderDTO.getOrderDetailList().stream().map(e ->
//                new CartDTO(e.getProductId(),e.getProductQuantity())
//        ).collect(Collectors.toList());
//        System.out.println("ddddddddddddddddddddddddddddddd");
        productService.decreaseStock(cartDTOList);


        //websocket通知前台  已经来了新订单了
        webSocket.sendMessage("新订单 订单号："+orderDTO.getOrderId());

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster=orderMasterRepository.findOne(orderId);
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        List<OrderDetail> orderDetailList=orderDetailRepository.findByOrderId(orderId);
        /**   这个判断方法记一下 好像是判断集合是否为空  TODO*/
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String BuyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage  =orderMasterRepository.findByBuyerOpenid(BuyerOpenid,pageable);

        List<OrderDTO>orderDTOList= OrderMaster2OrderDTO.convert(orderMasterPage.getContent());
        Page<OrderDTO>orderDTOPage=new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {

        //判断订单状态
        //比如如果是完结状态，那就不能取消啦
        //只有新下单才可以取消
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            System.out.println("取消订单 -------订单状态不正确！");
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }


        //修改订单状态
        OrderMaster orderMaster=new OrderMaster();
        /** 这里小心orderDTO 它的orderStatus和orderMaster可能不一致*/
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateResult= orderMasterRepository.save(orderMaster);
        if(updateResult==null){//如果返回的为空 说明失败了
            System.out.println("更新失败了！！！");
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList=new ArrayList<>();
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            CartDTO cartDTO=new CartDTO();
            cartDTO.setProductId(orderDetail.getProductId());
            cartDTO.setProductQuantity(orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }
        productService.increaseStock(cartDTOList);
        //如果已经支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS)) {
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if(updateResult==null){
            System.out.println("更新失败了！！！");
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改支付状态
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if(updateResult==null){
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable){
        Page<OrderMaster> orderMasterPage  =orderMasterRepository.findAll(pageable);
        List<OrderDTO>orderDTOList= OrderMaster2OrderDTO.convert(orderMasterPage.getContent());
        Page<OrderDTO>orderDTOPage=new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }
}
