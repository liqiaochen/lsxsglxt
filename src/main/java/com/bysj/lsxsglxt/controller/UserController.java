package com.bysj.lsxsglxt.controller;

import com.bysj.lsxsglxt.model.User;
import com.bysj.lsxsglxt.model.Useraddress;
import com.bysj.lsxsglxt.service.user.UserService;
import com.bysj.lsxsglxt.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @ClassName: UserController
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/14 15:03
 * @version: 1.0
 */
@Controller
public class UserController {
    @Autowired
    private   UserService userService ;

    @RequestMapping("/login")
    public String login(String userName, String passWord, HttpSession session) {

        User login = userService.loginUser(userName, passWord);

        if (login!=null){
            session.setAttribute("user",login);
            return "redirect:index.html";
        }else {
            String msg="用户名密码错误";
            try {
                msg = URLEncoder.encode(msg,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "redirect:login.html?msg="+msg;
        }
    }

    @RequestMapping("/saveUser")
    public String saveUser(String userName, String passWord) {
        ServerResponse<Integer> i = userService.saveUser(userName, passWord);
        if (i!=null){
            return "redirect:login.html";
        }else {
            String msg="注册失败";
            try {
                msg = URLEncoder.encode(msg,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "redirect:register.html?msg="+msg;
        }
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:login.html";
    }

    /**
     * 更新用户
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    public ServerResponse updateUser(@RequestBody User user, HttpSession session){
        System.out.println(user);
        ServerResponse<User> user1 = userService.updateUser(user);
        if (user!=null) {
            return userService.reSession(user1.getData(),session);
        }
        return ServerResponse.createByError("修改失败");
    }


    @RequestMapping("/userAddress/{id}")
    @ResponseBody
    public ServerResponse userAddressOne(@PathVariable("id") Integer id){
        ServerResponse<Useraddress> userAddress = userService.selectUserAddressOne(id);
        return userAddress;
    }
    @RequestMapping("/userAddress/update")
    public String updateUserAddress(Useraddress useraddress,HttpSession session ,RedirectAttributes redirectAttributes){
        User user = (User) session.getAttribute("user");
        Boolean aBoolean = userService.updateUserAddress(useraddress, user.getId());
        if (aBoolean){
            redirectAttributes.addFlashAttribute("msg","保存成功");
        }else {
            redirectAttributes.addFlashAttribute("msg","保存失败");
        }
        return "redirect:/PersonalAddress.html";
    }
}
