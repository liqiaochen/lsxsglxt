package com.bysj.lsxsglxt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"javassistProxyFactory","handler"})
public class Useraddress {
    private Integer id;

    /**
     * 收件人
     */
    private String consignee;

    /**
     * 电话
     */
    private String phone;
    /**
     * 邮编
     */
    private String zipCode;
    /**
     * 街道
     */
    private String street;
    /**
     * 地区
     */
    private String region;
    /**
     * 标签
     */
    private String tag;
    private Integer userId;


}