package com.bysj.lsxsglxt.controller.admin.shop;


import com.bysj.lsxsglxt.annotation.LoginRequired;
import com.bysj.lsxsglxt.model.Admin;
import com.bysj.lsxsglxt.model.Product;
import com.bysj.lsxsglxt.model.Producttype;
import com.bysj.lsxsglxt.model.ShopPojo;
import com.bysj.lsxsglxt.service.shop.ShopService;
import com.bysj.lsxsglxt.service.shop.ShopTypeService;
import com.bysj.lsxsglxt.utils.ServerResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName: ShopController
 * @description: 商品的核心
 * @author: LiQiaoChen
 * @date: Created in 2019/10/6 16:55
 * @version: 1.0
 */
@Controller
@RequestMapping("/admin/shop")
public class AdminShopController {

    @Autowired
    private ShopTypeService shopTypeService;

    @Autowired
    private ShopService shopService;

    /**
     * 分页，起始页默认1，分页大小默认5
     * @param pageNum
     * @param pageSize
     * @param modelAndView
     * @return
     */
    @LoginRequired(name = "admin")
    @RequestMapping("/shopList.html")
    public ModelAndView shopList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize , ModelAndView modelAndView){
        PageInfo<Product> productPageInfo = shopService.productListPage(pageNum, pageSize);
        modelAndView.addObject("pageInfo",productPageInfo);
        modelAndView.setViewName("admin/shopList");
        return modelAndView;
    }


    @LoginRequired(name = "admin")
    @RequestMapping("/shopUpdate.html")
    public ModelAndView shopUpdateShow(Integer id,ModelAndView modelAndView){
        Product product = shopService.selectByIdProduct(id);
        List<Producttype> productTypes = shopTypeService.showType(1);
        modelAndView.addObject("product", product);
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.setViewName("admin/shopUpdate");
        return modelAndView;
    }
    @LoginRequired(name = "admin")
    @RequestMapping("/shopUpdate")
    @ResponseBody
        public ServerResponse shopUpdate(@RequestBody  ShopPojo shop, HttpSession session){
        System.out.println(shop);
        Admin admin = (Admin)session.getAttribute("admin");
        ServerResponse<Integer> integerServerResponse = shopService.updateByIdProduct(shop,admin.getUsername());
        return integerServerResponse;
    }
    @LoginRequired(name = "admin")
    @RequestMapping("/shopAdd")
    @ResponseBody
    public ServerResponse shopAdd(@RequestBody  ShopPojo shop,HttpSession session){
        System.out.println(shop);
        Admin admin = (Admin)session.getAttribute("admin");
        ServerResponse<Integer> integerServerResponse = shopService.saveProduct(shop,admin.getUsername());
        return integerServerResponse;
    }

    @RequestMapping("/shopAddShow.html")
    public ModelAndView shopAddShow(ModelAndView modelAndView){
        List<Producttype> productTypes = shopTypeService.showType(1);
        productTypes.forEach((a)-> System.out.println(a));
        modelAndView.addObject("productTypes", productTypes);
        modelAndView.setViewName("admin/shopAdd");
        return modelAndView;
    }


    @RequestMapping("/deleteByIdShop")
    public String deleteByIdShop(Integer id){
        Integer i = shopService.deleteIdProduct(id);
        return "redirect:shopList.html";
    }



    @RequestMapping("/startStatus")
    @ResponseBody
    public ServerResponse startStatus(int[] ids){
        System.out.println(ids);
        ServerResponse serverResponse = shopService.updateStatus(ids,1);
        return serverResponse;
    }
    @RequestMapping("/stopStatus")
    @ResponseBody
    public ServerResponse stopStatus(int[] ids){
        System.out.println(ids);
        ServerResponse serverResponse = shopService.updateStatus(ids,2);
        return serverResponse;
    }
    @RequestMapping("/deleteStatus")
    @ResponseBody
    public ServerResponse deleteStatus(int[] ids){
        System.out.println(ids);
        ServerResponse serverResponse = shopService.deleteStatus(ids);
        return serverResponse;
    }
}
