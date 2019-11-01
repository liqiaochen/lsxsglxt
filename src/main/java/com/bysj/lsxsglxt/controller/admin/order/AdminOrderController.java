package com.bysj.lsxsglxt.controller.admin.order;


import com.bysj.lsxsglxt.annotation.LoginRequired;
import com.bysj.lsxsglxt.model.Admin;
import com.bysj.lsxsglxt.model.Order;
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
 * @ClassName: AdminOrderController
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/25 16:59
 * @version: 1.0
 */
@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @LoginRequired(name = "admin")
    @RequestMapping("/orderList.html")
    public ModelAndView shopList(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize , ModelAndView modelAndView){
        PageInfo<Order> orderPageInfo = orderService.showOrderAllPage(pageNum, pageSize);
        modelAndView.addObject("pageInfo",orderPageInfo);
        modelAndView.setViewName("admin/orderList");
        return modelAndView;
    }

    @LoginRequired(name = "admin")
    @RequestMapping("/orderDetails.html")
    public ModelAndView orderDetailsShow(Integer id){
        ModelAndView model = new ModelAndView("admin/orderDetails");
        Order order = orderService.showOrder(id);
        model.addObject("order", order);
        return model;
    }

    @LoginRequired(name = "admin")
    @RequestMapping("/delivery")
    @ResponseBody
    public ServerResponse orderDelivery(Integer id,HttpSession session){
        Admin admin = (Admin)session.getAttribute("admin");
        ServerResponse serverResponse = orderService.updateOrderStatusAdmin(3, id,admin.getUsername());
        return serverResponse;
    }


}
