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
    public PageInfo<Order> showOrderPage(Integer pageNum,Integer pageSize,Integer userId){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orders = orderMapper.selectByUserId(userId);
        PageInfo<Order> pageTypeInfo = new PageInfo<>(orders);
        return pageTypeInfo;
    }

    /**
     * 查询用户的订单根据用户id和订单状态,并分页
     * @param pageNum
     * @param pageSize
     * @param userId
     * @param status
     * @return
     */
    public PageInfo<Order> showOrderPageStatus(Integer pageNum,Integer pageSize,Integer userId,Integer status){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orders = orderMapper.selectByUserIdStatus(userId,status);
        PageInfo<Order> pageTypeInfo = new PageInfo<>(orders);
        return pageTypeInfo;
    }
    /**
     * 查询订单通过订单id
     * @param id
     * @return
     */
    public Order showOrder(Integer id){
        Order order = orderMapper.selectById(id);
        return order;
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
           System.out.println("--------------"+orderitem);
           order.setUserId(user);
           order.setUserName(user.getUserName());
           order.setOrderCode(orderCode);
           order.setOrderTime(now);
           order.setStatus(1);
           order.setPayment(payment);
           order.setTotal(cartsPojo.getCartTotalPrice());
           Integer orderId = orderMapper.insertOrder(order);

           /**
            * 修改购物车，并且将状态改为生成订单
            */
           List<CartPojo> cartPojos = cartsPojo.getCartPojo();
           Cart cart ;
           int i=0;
           for (CartPojo pojo : cartPojos){
               cart = ConverCart(pojo);
               cart.setStatus(2);
               cart.setOrderId(order.getId());
               Integer integer = cartMapper.updateCartStatus(cart);
               i=i+integer;
           }
           if (i==cartPojos.size()) {
               return ServerResponse.createBySuccess("生成订单成功", "");
           }else {
               return ServerResponse.createByError("修改购物车失败");
           }
       }catch (Exception e) {
           return ServerResponse.createByError("生成订单失败");
       }
    }

    /**
     * 修改订单状态
     * @return
     */
    public ServerResponse updateOrderStatus(int status,Integer orderId){
        try {
            Date now = new Date();
            orderMapper.updateOrderStatus(status,orderId);
            Order order = orderMapper.selectById(orderId);
            Orderitem orderItem = order.getOrderItemId();
            Integer orderItemId = orderItem.getId();
            //客户付款
            if (status==2){
                orderitemMapper.updateOrderItemStatusPay(orderItemId,status,now);
                return ServerResponse.createBySuccess("付款成功","");
            }//收货
            else if(status==4){
                orderitemMapper.updateOrderItemStatusReceipt(orderItemId,status,now);
                return ServerResponse.createBySuccess("收货成功","");
            }//取消订单
            else  if (status==5){
                orderitemMapper.updateOrderItemStatusCancel(orderItemId,status);
                return ServerResponse.createBySuccess("取消订单成功","");
            }
            return ServerResponse.createByError("出现了意外");
        }catch (Exception e){
            return ServerResponse.createByError(e.toString());
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
