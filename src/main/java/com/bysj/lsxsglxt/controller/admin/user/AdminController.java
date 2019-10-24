package com.bysj.lsxsglxt.controller.admin.user;


import com.bysj.lsxsglxt.model.Admin;
import com.bysj.lsxsglxt.service.user.UserService;
import com.bysj.lsxsglxt.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @ClassName: UserController
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/10 9:01
 * @version: 1.0
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

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
            try {
                msg = URLEncoder.encode(msg,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
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
    public ServerResponse updateAdmin(Admin admin,HttpSession session){
        ServerResponse<Admin> admin1 = userService.updateAdmin(admin);
        if (admin!=null) {
            return userService.reSession(admin1.getData(),session);
        }
        return admin1;
    }

    @RequestMapping("/updateUrl")
    @ResponseBody
    public ServerResponse updateUrl(String url, Integer id,HttpSession session){
            ServerResponse<Admin> admin = userService.updateAdminUrl(url, id);

            if (admin!=null) {
                System.out.println(admin);
                return userService.reSession(admin.getData(),session);
            }
        return  ServerResponse.createByError("执行出现了问题");
    }

    @RequestMapping("/rePassword")
    @ResponseBody
    public ServerResponse rePassword(String password,Integer id,HttpSession session){
        ServerResponse<Admin> admin = userService.rePassword(password,id);
        if (admin!=null) {
            return userService.reSession(admin.getData(),session);
        }
        return  ServerResponse.createByError("执行出现了问题");
    }
}
