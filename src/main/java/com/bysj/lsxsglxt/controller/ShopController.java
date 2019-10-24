package com.bysj.lsxsglxt.controller;


import com.bysj.lsxsglxt.model.Product;
import com.bysj.lsxsglxt.service.shop.ShopService;
import com.bysj.lsxsglxt.service.shop.ShopTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: ShopController
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/20 16:48
 * @version: 1.0
 */
@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopTypeService shopTypeService;

    @Autowired
    private ShopService shopService;

    @RequestMapping("/type")
    public String productTypeId(Integer id){
        return  "redirect:/search?search="+id;
    }

    @RequestMapping("/product")
    public ModelAndView productId(Integer id){
        Product product = shopService.productsOne(id);
        ModelAndView modelAndView = new ModelAndView("/display");
        modelAndView.addObject("product",product);
        return  modelAndView;
    }


}
