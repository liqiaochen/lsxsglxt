package com.bysj.lsxsglxt.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: AdminIndexController
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/4 0:37
 * @version: 1.0
 */
@RequestMapping("/admin")
@Controller
public class AdminIndexController {


    @RequestMapping("index.html")
    public String reAdminIndex(){
    return "admin/index";
    }

    @RequestMapping("orderList.html")
    public String reAdminOrderList(){
        return "admin/orderList";
    }

    @RequestMapping("orderUpdate.html")
    public String reAdminOrderUpdate(){
        return "admin/orderUpdate";
    }

    @RequestMapping("shopList.html")
    public String reAdminShopList(){
        return "admin/shopList";
    }

    @RequestMapping("shopAdd.html")
    public String reAdminShopAdd(){
        return "admin/shopAdd";
    }

    @RequestMapping("shopUpdate.html")
    public String reAdminShopUpdate(){
        return "admin/shopUpdate";
    }

    @RequestMapping("shopType.html")
    public String reAdminShopType(){
        return "admin/shopType";
    }

    @RequestMapping("shopRecommend.html")
    public String reAdminShopRecommend(){
        return "admin/shopRecommend";
    }
}
