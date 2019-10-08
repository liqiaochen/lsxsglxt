package com.bysj.lsxsglxt.model;

import lombok.Data;

import java.util.Date;

@Data
public class Picture {
    private Integer id;

    /**
     * 图片级别，（1首页图，2商品详情图）
     */
    private Integer level;
    /**
     * 图片标题
     */
    private String title;

    private Date time;

    private String url;
    /**
     * 管理员
     */
    private String adminName;

    private Integer productId;
}