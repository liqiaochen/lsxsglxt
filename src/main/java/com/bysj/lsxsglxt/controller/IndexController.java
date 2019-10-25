package com.bysj.lsxsglxt.controller;


import com.bysj.lsxsglxt.annotation.LoginRequired;
import com.bysj.lsxsglxt.model.Cart;
import com.bysj.lsxsglxt.model.Product;
import com.bysj.lsxsglxt.model.User;
import com.bysj.lsxsglxt.service.shop.CartService;
import com.bysj.lsxsglxt.service.shop.ShopService;
import com.bysj.lsxsglxt.utils.Other;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: IndexController
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/9/25 14:33
 * @version: 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private CartService cartService;

    @RequestMapping("/index.html")
    public ModelAndView reindex(){
        ModelAndView modelAndView = new ModelAndView("index");
        Map<String, List> map = shopService.productIndex();
        modelAndView.addAllObjects(map);
        return modelAndView;
    }
    @RequestMapping("/login.html")
    public String relogin(){
        return  "login";
    }
    @RequestMapping("/register.html")
    public String reregister(){
        return "register";
    }
    @LoginRequired(name = "user")
    @RequestMapping("/carts.html")
    public ModelAndView recarts(HttpSession session) {
        ModelAndView mv = new ModelAndView("carts");
        User user = (User) session.getAttribute("user");
        List<Cart> carts = cartService.cartListUser(user.getId());
        mv.addObject("carts", carts);
        return mv;
    }
    @LoginRequired(name = "user")
    @RequestMapping("/purchase.html")
    public String repurchase(){
        return "purchase";
    }



    @LoginRequired(name = "user")
    @RequestMapping("/PersonalInfo.html")
    public String rePersonalInfo(){
        return "PersonalInfo";
    }

    /**
     * 用户地址
     */
    @LoginRequired(name = "user")
    @RequestMapping("/PersonalAddress.html")
    public ModelAndView rePersonalAddress(@ModelAttribute("msg") String msg){
        ModelAndView mv = new ModelAndView("PersonalAddress");
        if (msg!=null) {
            mv.addObject("msg", msg);
        }
        return mv;
    }
    @LoginRequired(name = "user")
    @RequestMapping("/PersonalOrder.html")
    public String rePersonalOrder(){
        return "redirect:/order/showOrderPage";
    }

    @LoginRequired(name = "user")
    @RequestMapping("/PersonalOrder2.html")
    public String rePersonalOrder2(){
        return "PersonalOrder";
    }
    

    @RequestMapping("/search")
    public ModelAndView research(@RequestParam(value = "search", required =false) String msg,
                                 @RequestParam(defaultValue = "1") int pageNum,
                                 @RequestParam(defaultValue = "8") int pageSize){
        ModelAndView mv = new ModelAndView("search");
        if (msg!=null){
            boolean anInt = Other.isInt(msg);
            if (anInt){
                Integer i = Integer.parseInt(msg);
                PageInfo<Product> productPageInfo = shopService.produceType(pageNum, pageSize, i);
                mv.addObject("pageInfo",productPageInfo);
                mv.addObject("search", msg);
            }
            else {
                PageInfo<Product> productPageInfo = shopService.productListLike(pageNum, pageSize,msg);
                mv.addObject("pageInfo",productPageInfo);
                mv.addObject("search", msg);
            }
        }else {
            PageInfo<Product> productPageInfo = shopService.productsListPageStatus(pageNum, pageSize);
            mv.addObject("pageInfo",productPageInfo);
            mv.addObject("search", msg);
        }
        return mv;
    }

    @RequestMapping("/test.html")
    public String retestContext(){
        return "test";
    }
}
