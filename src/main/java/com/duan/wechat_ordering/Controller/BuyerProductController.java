package com.duan.wechat_ordering.Controller;


import com.duan.wechat_ordering.Utils.ResultVOUtil;
import com.duan.wechat_ordering.VO.ProductInfoVO;
import com.duan.wechat_ordering.VO.ProductVO;
import com.duan.wechat_ordering.VO.ResultVO;
import com.duan.wechat_ordering.entity.ProductCategory;
import com.duan.wechat_ordering.entity.ProductInfo;
import com.duan.wechat_ordering.enums.ProductStatusEnum;
import com.duan.wechat_ordering.service.ProductCategoryService;
import com.duan.wechat_ordering.service.ProductInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product",key = "123")
    public ResultVO list(){
        /**这里要做的事情就是 查询所有的上架商品，找到他们的所有类别，**/
        //1.查询所有的上架商品   一次性查完所有的上架商品，这样就方便第三步 取ProductInfoVO
        List<ProductInfo>productInfoList =productInfoService.findUpAll();

        //2.查询类目
        List<Integer>categoryTypeList=new ArrayList<>();
        for(ProductInfo productInfo:productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }
        List<ProductCategory> productCategoryList=categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductVO> productVOList=new ArrayList<>();
        //3.数据拼装
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO=new ProductVO() ;
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO>productInfoVOList=new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

//        ResultVO<List<ProductVO>>resultVO=new ResultVO<>();
//        resultVO.setData(productVOList);

        return ResultVOUtil.success(productVOList);
    }
}
