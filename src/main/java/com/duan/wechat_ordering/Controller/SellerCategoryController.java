package com.duan.wechat_ordering.Controller;


import com.duan.wechat_ordering.entity.ProductCategory;
import com.duan.wechat_ordering.exception.SellException;
import com.duan.wechat_ordering.form.CategoryForm;
import com.duan.wechat_ordering.service.ProductCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;
    @GetMapping("/lists")
    public ModelAndView lists(Map<String ,Object> map){
        List<ProductCategory> productCategoryList=productCategoryService.findAll();
        map.put("productCategoryList",productCategoryList);
        return new ModelAndView("/category/lists",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false)Integer categoryId,
                              Map<String ,Object> map){
        //如果为空 也就是在新增页面
        if(categoryId==null){
            return new ModelAndView("/category/index",map);
        }
        else {
            ProductCategory productCategory=productCategoryService.findOne(categoryId);
            map.put("productCategory",productCategory);
            return new ModelAndView("/category/index",map);
        }

    }
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String ,Object> map){
        if(bindingResult.hasErrors()){
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/lists");
            return new ModelAndView("/common/fail",map);
        }
        try {
            ProductCategory productCategory=new ProductCategory();
            BeanUtils.copyProperties(categoryForm,productCategory);
            productCategoryService.save(productCategory);
        }catch (SellException e ){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/lists");
            return new ModelAndView("/common/fail",map);
        }
        map.put("msg","提交成功！");
        map.put("url","/sell/seller/category/lists");
        return new ModelAndView("/common/success",map);

    }
}
