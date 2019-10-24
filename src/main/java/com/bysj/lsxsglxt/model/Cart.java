package com.bysj.lsxsglxt.model;

import lombok.Data;

@Data
public class Cart {
    private Integer id;

    private User userId;

    private Product productId;

    private Integer num;
    /**
     * 商品单价
     */
    private Double price;
    /**
     * 商品名称
     */
    private  String productName;
    /**
     * 总计
     */
    private  Double total;
    /**
     * 状态(1：未生成订单2：未生成订单)
     */
    private Integer status;

    private Integer orderId;
}