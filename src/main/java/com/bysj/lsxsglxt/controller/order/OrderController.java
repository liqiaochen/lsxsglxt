package com.bysj.lsxsglxt.controller.order;


import com.bysj.lsxsglxt.model.CartsPojo;
import com.bysj.lsxsglxt.model.Order;
import com.bysj.lsxsglxt.model.User;
import com.bysj.lsxsglxt.service.order.OrderService;
import com.bysj.lsxsglxt.utils.ServerResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: Ordercontroller
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/24 15:14
 * @version: 1.0
 */
@RequestMapping("/order")
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("/showOrderPage")
    public ModelAndView showOrderPage(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "2") int pageSize, HttpSession session){
        ModelAndView mv = new ModelAndView("order/PersonalOrder");
        User user = (User)session.getAttribute("user");
        PageInfo<Order> orderPageInfo = orderService.showOrder(pageNum, pageSize, user.getId());
        mv.addObject("orderPageInfo",orderPageInfo);
        return  mv;
    }

    @RequestMapping("/addOrder")
    @ResponseBody
    public ServerResponse addOrder(HttpSession session,String remake,Integer payment,Integer addressId){
        CartsPojo cartsPojo = (CartsPojo)session.getAttribute("cartsPojo");
        User user = (User)session.getAttribute("user");
        String paymentString = "";
        if (payment == 1) {
            paymentString="在线支付";
        }
        ServerResponse serverResponse = orderService.addOrder(cartsPojo,user,addressId,remake,paymentString);
        return  serverResponse;
    }
}
