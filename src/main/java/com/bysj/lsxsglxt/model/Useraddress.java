package com.bysj.lsxsglxt.model;

import lombok.Data;

@Data
public class Useraddress {
    private Integer id;

    private String address;
    /**
     * 收件人
     */
    private String consignee;

    private String phone;

    private String zipCode;

    private Integer userId;


}