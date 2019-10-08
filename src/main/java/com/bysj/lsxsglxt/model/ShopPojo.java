package com.bysj.lsxsglxt.model;


import lombok.Data;

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
    private String code;
    private String title;
    private double price;
    private Integer typeId;
    private List<String> imgs;
    private String unit;
    private Integer status;
    private Integer hot;
    private Long stock;
    private String description;
}
