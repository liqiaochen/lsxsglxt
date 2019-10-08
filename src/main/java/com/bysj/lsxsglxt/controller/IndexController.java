package com.bysj.lsxsglxt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: IndexController
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/9/25 14:33
 * @version: 1.0
 */
@Controller
public class IndexController {

    @RequestMapping("/index.html")
    public String reindex(){
        return "index";
    }
    @RequestMapping("/login.html")
    public String relogin(){
        return  "login";
    }
    @RequestMapping("/register.html")
    public String reregister(){
        return "register";
    }
    @RequestMapping("/carts.html")
    public String recarts(){
        return "carts";
    }
    @RequestMapping("/display2.html")
    public String redisplay2(){
        return "display2";
    }
    @RequestMapping("/display.html")
    public String redisplay(){
        return "display";
    }
    @RequestMapping("/purchase.html")
    public String repurchase(){
        return "purchase";
    }
    @RequestMapping("PersonalInfo.html")
    public String rePersonalInfo(){
        return "PersonalInfo";
    }
    @RequestMapping("PersonalAddress.html")
    public String rePersonalAddress(){
        return "PersonalAddress";
    }
    @RequestMapping("PersonalOrder.html")
    public String rePersonalOrder(){
        return "order/PersonalOrder";
    }
    @RequestMapping("order-view.html")
    public String rePersonalOrderView(){
        return "order/order-view";
    }
    @RequestMapping("/test.html")
    public String retest(){
        return "test";
    }
}
