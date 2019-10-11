package com.bysj.lsxsglxt.model;

import lombok.Data;

@Data
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String url;
    private String remark;
}