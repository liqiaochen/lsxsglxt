package com.bysj.lsxsglxt.controller.admin;


import com.bysj.lsxsglxt.annotation.LoginRequired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: AdminIndexController
 * @description: 后台控制器
 * @author: LiQiaoChen
 * @date: Created in 2019/10/4 0:37
 * @version: 1.0
 */
@RequestMapping("/admin")
@Controller
public class AdminIndexController {

    @LoginRequired(name = "admin")
    @RequestMapping("/index.html")
    public String reAdminIndex(){
    return "admin/index";
    }

    @RequestMapping("/orderList.html")
    public String reAdminOrderList(){
        return "admin/orderList";
    }

    @RequestMapping("/orderUpdate.html")
    public String reAdminOrderUpdate(){
        return "admin/orderUpdate";
    }

    @RequestMapping("/shopList.html")
    public String reAdminShopList(){
        return "admin/shopList";
    }

    @RequestMapping("/shopAdd.html")
    public String reAdminShopAdd(){
        return "admin/shopAdd";
    }

    @RequestMapping("/shopUpdate.html")
    public String reAdminShopUpdate(){
        return "admin/shopUpdate";
    }

    @RequestMapping("/shopType.html")
    public String reAdminShopType(){
        return "admin/shopType";
    }

    @RequestMapping("/shopRecommend.html")
    public String reAdminShopRecommend(){
        return "admin/shopRecommend";
    }


    @RequestMapping("/adminEditPwd.html")
    public String reAdminEditPwd(){
        return "admin/adminEditPwd";
    }
    @RequestMapping("/adminLogin.html")
    public String reAdminLogin(){
        return "admin/adminLogin";
    }
    @RequestMapping("/adminPersonal.html")
    public String reAdminPersonal(){
        return "admin/adminPersonal";
    }
}
