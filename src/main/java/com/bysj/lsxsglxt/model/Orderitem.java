package com.bysj.lsxsglxt.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Orderitem {
    private Integer id;

    /**
     * 收货时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date confirmTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 付款时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    private String phone;
    /**
     * 发货时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date shipTime;

    private String updateName;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private Integer status;
    /**
     * 总价
     */
    private Double totalPrice;
    /**
     * 备注
     */
    private String remake;
    /**
     * 用户地址
     */
    private Useraddress addressId;

}