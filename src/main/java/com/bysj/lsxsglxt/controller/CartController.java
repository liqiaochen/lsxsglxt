package com.bysj.lsxsglxt.controller;

import com.bysj.lsxsglxt.model.Cart;
import com.bysj.lsxsglxt.model.CartPojo;
import com.bysj.lsxsglxt.model.CartsPojo;
import com.bysj.lsxsglxt.model.User;
import com.bysj.lsxsglxt.service.shop.CartService;
import com.bysj.lsxsglxt.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName: CartController
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/23 14:43
 * @version: 1.0
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private  CartService cartService;


    @RequestMapping("/add")
    @ResponseBody
    public ServerResponse addCart(@RequestBody Cart cart, HttpSession session){
        System.out.println(cart);
        User user =(User) session.getAttribute("user");
        ServerResponse<Integer> integerServerResponse = cartService.addCart(cart, user.getId());
        return  integerServerResponse;

    }

    @RequestMapping("/delete")
    @ResponseBody
    public ServerResponse deleteCart(Integer id){
        ServerResponse<Integer> integerServerResponse = cartService.deleteCart(id);
        return  integerServerResponse;
    }
    @RequestMapping("/sumbit")
    @ResponseBody
    public ServerResponse sumbit(@RequestBody CartsPojo cartsPojo, HttpSession httpSession){
        httpSession.setAttribute("cartsPojo", cartsPojo);
        return  ServerResponse.createBySuccess("提交成功", "");
    }

    /**
     * 取消订单
     * @param httpSession
     * @return
     */
    @RequestMapping("/quxiao")
    @ResponseBody
    public ServerResponse quxiao(HttpSession httpSession){
        CartsPojo cartsPojo =(CartsPojo) httpSession.getAttribute("cartsPojo");
        List<CartPojo> cartPojo = cartsPojo.getCartPojo();
        ServerResponse<Integer> integerServerResponse = cartService.updateCart(cartPojo);
        httpSession.removeAttribute("cartsPojo");
        return  integerServerResponse;
    }
}
