package com.bysj.lsxsglxt.service.order;


import com.bysj.lsxsglxt.mapper.CartMapper;
import com.bysj.lsxsglxt.mapper.OrderMapper;
import com.bysj.lsxsglxt.mapper.OrderitemMapper;
import com.bysj.lsxsglxt.mapper.UseraddressMapper;
import com.bysj.lsxsglxt.model.*;
import com.bysj.lsxsglxt.utils.OrderCodeUtil;
import com.bysj.lsxsglxt.utils.ServerResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: OrderService
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/24 14:01
 * @version: 1.0
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderitemMapper orderitemMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private UseraddressMapper useraddressMapper;
    /**
     * 查询用户的订单,并分页
     * @param userId
     * @return
     */
    public PageInfo<Order> showOrder(Integer pageNum,Integer pageSize,Integer userId){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orders = orderMapper.selectByUserId(userId);
        PageInfo<Order> pageTypeInfo = new PageInfo<>(orders);
        return pageTypeInfo;
    }


    public ServerResponse addOrder(CartsPojo cartsPojo, User user,Integer addressId,String remake,String payment){
       try {
           Order order =new Order();
           Orderitem orderitem =new Orderitem();
           /**
            * 获取地址信息
            */
           Useraddress useraddress = useraddressMapper.selectById(addressId);
           //生成订单编号
           String orderCode = OrderCodeUtil.getOrderCode();
           //生成时间
           Date now=new Date();

           /**
            * 填充订单详情
            */
           orderitem.setAddressId(useraddress);
           orderitem.setStatus(1);
           orderitem.setPhone(user.getTelPhone());
           orderitem.setOrderCode(orderCode);
           orderitem.setTotalPrice(cartsPojo.getCartTotalPrice());
           orderitem.setRemake(remake);
           orderitem.setCreateTime(now);
           Integer orderItemId = orderitemMapper.insertOrder(orderitem);

           /**
            * 填充订单信息
            */
           order.setOrderItemId(orderitem);
           order.setUserId(user);
           order.setUserName(user.getUserName());
           order.setOrderCode(orderCode);
           order.setOrderTime(now);
           order.setStatus(1);
           order.setPayment(payment);
           Integer orderId = orderMapper.InsertOrder(order);

           /**
            * 修改购物车，并且将状态改为生成订单
            */
           List<CartPojo> cartPojos = cartsPojo.getCartPojo();
           Cart cart ;
           int i=0;
           for (CartPojo pojo : cartPojos){
               cart = ConverCart(pojo);
               cart.setStatus(2);
               cart.setOrderId(orderId);
               Integer integer = cartMapper.updateCartStatus(cart);
               i=i+integer;
           }
           return ServerResponse.createBySuccess("生成订单成功", "");
       }catch (Exception e) {
           System.out.println(e);
           return ServerResponse.createByError("生成订单失败");
       }
    }

    private Cart ConverCart(CartPojo pojo) {
        Cart cart;
        cart   = new Cart();
        Integer id = pojo.getId();
        String title = pojo.getTitle();
        double price = pojo.getPrice();
        int num = pojo.getNum();
        double total = pojo.getTotal();
        cart.setId(id);
        cart.setProductName(title);
        cart.setPrice(price);
        cart.setTotal(total);
        cart.setNum(num);
        return cart;
    }

}
