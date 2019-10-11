package com.bysj.lsxsglxt.controller.admin.user;


import com.bysj.lsxsglxt.model.Admin;
import com.bysj.lsxsglxt.service.user.UserService;
import com.bysj.lsxsglxt.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: UserController
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/10 9:01
 * @version: 1.0
 */
@RequestMapping("/admin")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public String login(String username,String password,HttpSession session){
        Admin admin = userService.login(username, password);
        if (admin!=null){
            session.setAttribute("admin", admin);
            return "redirect:index.html";
        }else {
            String msg="用户名密码错误";
            return "redirect:adminLogin.html?msg="+msg;
        }

    }
    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:adminLogin.html";
    }

    @RequestMapping("/updateAdmin")
    @ResponseBody
    public ServerResponse<Integer> updateAdmin(Admin admin){
        ServerResponse<Integer> integerServerResponse = userService.updateAdmin(admin);
        return integerServerResponse;
    }

    @RequestMapping("/updateUrl")
    @ResponseBody
    public ServerResponse<Integer> updateUrl(String url,Integer id){
        ServerResponse<Integer> integerServerResponse = userService.updateAdminUrl(url,id);
        return integerServerResponse;
    }

    @RequestMapping("/rePassword")
    @ResponseBody
    public ServerResponse<Integer> rePassword(String password,Integer id){
        ServerResponse<Integer> integerServerResponse = userService.rePassword(password,id);
        return integerServerResponse;
    }
}
