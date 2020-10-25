package com.duan.wechat_ordering.Controller;


import com.duan.wechat_ordering.Utils.KeyUtil;
import com.duan.wechat_ordering.dto.OrderDTO;
import com.duan.wechat_ordering.entity.ProductCategory;
import com.duan.wechat_ordering.entity.ProductInfo;
import com.duan.wechat_ordering.enums.ProductStatusEnum;
import com.duan.wechat_ordering.exception.SellException;
import com.duan.wechat_ordering.form.ProductForm;
import com.duan.wechat_ordering.service.OrderService;
import com.duan.wechat_ordering.service.ProductCategoryService;
import com.duan.wechat_ordering.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
//@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/lists")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size",defaultValue = "5")Integer size,
                             Map<String ,Object> map){
        PageRequest pageRequest=PageRequest.of(page-1,size);  //从第一页开始
        Page<ProductInfo> productInfoPage=productInfoService.findAll(pageRequest);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("/product/lists",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false)String productId,
                              Map<String,Object>map){
        //如果是新增操作
        if(productId==null){
            List<ProductCategory>productCategoryList=productCategoryService.findAll();
            map.put("productCategoryList",productCategoryList);
            return new ModelAndView("/product/index",map);
        }
        ProductInfo productInfo=productInfoService.findOne(productId);
        List<ProductCategory>productCategoryList=productCategoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        map.put("productInfo",productInfo );

        return new ModelAndView("/product/index",map);
    }

    @PostMapping("/save")
    /**********表单验证的方法  **/
    @CacheEvict(cacheNames = "product",key = "123")
    public ModelAndView save( @Valid ProductForm productForm,
                             BindingResult bindingResult,
                             Map<String, Object>map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/lists");
            return new ModelAndView("/common/fail",map);
        }
        try {
            if(StringUtils.isEmpty(productForm.getProductId())){
                ProductInfo productInfo=new ProductInfo();
                BeanUtils.copyProperties(productForm,productInfo);
                productInfo.setProductId(KeyUtil.getKey());
                productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
                productInfoService.save(productInfo);
            }
            else{
                ProductInfo productInfo=productInfoService.findOne(productForm.getProductId());
                BeanUtils.copyProperties(productForm,productInfo);
                productInfoService.save(productInfo);
            }
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/lists");
            return new ModelAndView("/common/fail",map);
        }
        map.put("msg","保存商品信息成功！");
        map.put("url","/sell/seller/product/lists");
        return new ModelAndView("/common/success",map);
    }

    @GetMapping("/onsale")
    public ModelAndView onsale(@RequestParam("productId")String productId,
                               Map<String,Object>map){
        /**************************** 这里注意 不是判断productInfo为空 而是捕捉到这个异常  */
        try {
            ProductInfo productInfo=productInfoService.onSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/lists");
            return new ModelAndView("/common/fail",map);
        }
        map.put("msg","上架商品成功！");
        map.put("url","/sell/seller/product/lists");
        return new ModelAndView("/common/success",map);
    }
    @GetMapping("/unsale")
    public ModelAndView unsale(@RequestParam("productId")String productId,
                               Map<String,Object>map){
        /**************************** 这里注意 不是判断productInfo为空 而是捕捉到这个异常  */
        try {
            ProductInfo productInfo=productInfoService.unSale(productId);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/lists");
            return new ModelAndView("/common/fail",map);
        }
        map.put("msg","下架商品成功！");
        map.put("url","/sell/seller/product/lists");
        return new ModelAndView("/common/success",map);
    }
}
