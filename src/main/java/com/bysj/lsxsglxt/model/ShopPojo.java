package com.bysj.lsxsglxt.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ShopPojo
 * @description: 商品的包装类
 * @author: LiQiaoChen
 * @date: Created in 2019/10/8 19:33
 * @version: 1.0
 */
@Data
public class ShopPojo {
    private Integer id;
    private String code;
    private String title;
    private double price;
    private Integer typeId;
    private Integer recommend;
    private Integer turnover;
    private List<String> imgs;
    private String unit;
    private Integer status;
    private Integer hot;
    private Long stock;
    private String description;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String createAdminName;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String updateAdminName;
}
