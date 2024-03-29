package com.bysj.lsxsglxt.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private Integer id;

    private String userName;
    /**
     * 密码信息(加密)
     */
    private String passWord;
    /**
     * 账户余额
     */
    private Double balance;
    /**
     * 个性签名
     */
    private String signature;
    /**
     * 爱好
     */
    private String hobby;
    /**
     * 电话号码
     */
    private String telPhone;
    /**
     * 备注
     */
    private String remark;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 地址集合
     */
    private List<Useraddress> useraddress;
}