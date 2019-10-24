package com.bysj.lsxsglxt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Integer id;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 下单时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    /**
     * 下单人的姓名
     */
    private String userName;
    /**
     * 总计
     */
    private Double total;
    /**
     * 订单状态（12345）
     */
    private Integer status;
    /**
     * 订单详情
     */
    private Orderitem  orderItemId;
    /**
     * 用户信息
     */
    private User userId;
    /**
     * 支付方式
     */
    private String payment;
    /**
     * 购物车
     */
    private List<Cart> carts;


}