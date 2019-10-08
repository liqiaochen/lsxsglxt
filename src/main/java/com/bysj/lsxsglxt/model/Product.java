package com.bysj.lsxsglxt.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Product {
    private Integer id;
    /**
     * 商品编码
     */
    private String code;
    /**
     * 名称
     */
    private String title;

    private Double price;
    /**
     * 描述
     */
    private String note;
    /**
     * 库存
     */
    private Long stock;
    /**
     * 是否推荐
     */
    private Integer recommend;
    /**
     * 成交量
     */
    private Integer turnover;

    private Integer hot;
    /**
     * 单位
     */
    private String unit;
    /**
     * 上架或者下架
     */
    private Integer status;

    private Date createTime;
    /**
     * 创建者
     */
    private String createAdminName;
    private Date updateTime;
    /**
     * 修改者
     */
    private String updateAdminName;
    /**
     * typeId
     */
    private Producttype typeId;
    /**
     * 图片
     */
    private List<Picture> picId;

}