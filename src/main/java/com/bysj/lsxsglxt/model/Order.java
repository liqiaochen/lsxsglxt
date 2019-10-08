package com.bysj.lsxsglxt.model;

import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Integer id;
    private Date orderTime;
    private String userName;
    private Double price;
    private Integer status;
    private Orderitem  orderId;
    private List<Product> productId;
    private User userId;



}