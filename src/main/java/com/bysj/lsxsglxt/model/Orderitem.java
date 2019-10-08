package com.bysj.lsxsglxt.model;

import lombok.Data;

import java.util.Date;

@Data
public class Orderitem {
    private Integer id;

    private User userId;
    /**
     * 用户地址
     */
    private Useraddress userAddressId;

    private String address;

    /**
     * 确认收货时间
     */
    private Date confirmTime;

    /**
     * 收货人
     */
    private String consignee;

    private Date createTime;

    private String orderNumber;

    private Date payTime;

    private String phone;
    /**
     * 发货时间
     */
    private Date shipTime;

    private String updateName;

    private Date updateTime;

    private Integer status;
    /**
     * 总价
     */
    private Double totalPrice;
    /**
     * 邮编
     */
    private String zipCode;

}