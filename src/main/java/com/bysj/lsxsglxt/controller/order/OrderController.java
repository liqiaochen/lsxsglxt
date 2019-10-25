package com.bysj.lsxsglxt.controller.order;
import com.bysj.lsxsglxt.annotation.LoginRequired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @LoginRequired(name = "user")
    @RequestMapping("/showOrderPage")
    public ModelAndView showOrderPage(@RequestParam(value = "serverResponse", required =false) ServerResponse serverResponse,
                                      @RequestParam(required =false) Integer status,@RequestParam(defaultValue = "1") int pageNum,
                                      @RequestParam(defaultValue = "2") int pageSize, HttpSession session,
                                      RedirectAttributes redirectAttributes){
        ModelAndView mv;
        if (serverResponse!=null&&!serverResponse.checkIsSuccess()){
            mv = new ModelAndView("redirect:/purchase.html");
            redirectAttributes.addFlashAttribute("msg",serverResponse.getMsg());
       }else {
            mv = new ModelAndView("order/PersonalOrder");
            User user = (User) session.getAttribute("user");
            //如果没有则是查询所有订单
            if (status==null) {
                PageInfo<Order> orderPageInfo = orderService.showOrderPage(pageNum, pageSize, user.getId());
                mv.addObject("orderPageInfo", orderPageInfo);
                mv.addObject("status", 0);
            }   //更据状态查询订单
            else{
                PageInfo<Order> orderPageInfo = orderService.showOrderPageStatus(pageNum, pageSize, user.getId(), status);
                mv.addObject("orderPageInfo", orderPageInfo);
                mv.addObject("status", status);
            }
       }
        return  mv;
    }

    @RequestMapping("/addOrder")
    public String addOrder(HttpSession session, String remake, Integer payment, Integer addressId, RedirectAttributes redirectAttributes){
        CartsPojo cartsPojo = (CartsPojo)session.getAttribute("cartsPojo");
        User user = (User)session.getAttribute("user");
        String paymentString = "";
        if (payment == 1) {
            paymentString="在线支付";
        }
        ServerResponse serverResponse = orderService.addOrder(cartsPojo,user,addressId,remake,paymentString);
        redirectAttributes.addFlashAttribute("serverResponse",serverResponse);
        return  "redirect:showOrderPage";
    }
    @LoginRequired(name = "user")
    @RequestMapping("/orderItem")
    public ModelAndView showOrderItem(Integer id) {
        ModelAndView mv=new ModelAndView("order/order-view");
        Order order = orderService.showOrder(id);
        mv.addObject("order" , order);
        return mv;
    }

    /**
     * 付款
     * @return
     */
    @RequestMapping("/pay")
    @ResponseBody
    public ServerResponse payMany(Integer id){
        int Status = 2;
        ServerResponse serverResponse = orderService.updateOrderStatus(Status, id);
        return serverResponse;
    }

    /**
     * 收货
     * @return
     */
    @RequestMapping("/receipt")
    @ResponseBody
    public ServerResponse receipt(Integer id){
        int Status = 4;
        ServerResponse serverResponse = orderService.updateOrderStatus(Status, id);
        return serverResponse;
    }
    /**
     * 取消订单
     * @return
     */
    @RequestMapping("/cancelOrder")
    @ResponseBody
    public ServerResponse cancelOrder(Integer id){
        int Status = 5;
        ServerResponse serverResponse = orderService.updateOrderStatus(Status, id);
        return serverResponse;
    }
}
