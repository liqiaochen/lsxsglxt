package com.bysj.lsxsglxt.model;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String createAdminName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private String updateAdminName;
    /*{code=1002, title=可口可乐, price=44.0,
            typeId=13, imgs=[/lsxsglxt/upload/20191009/7c2bbb65229b46f49ab479df22bae635.png,
        /lsxsglxt/upload/20191008/7adace1e31d34654bfc55276957b05e0.jpg],
        unit=330ml*24, status=1, hot=1, stock=300,
            description=百事可乐爱心罐碳酸汽水饮料整箱330ml*24罐经典口味百事出品
        新老包装 箱/塑膜更克换重随机发货实物为准,
            id=2, recommend=否, turnover=0, createAdminName=admin,
            createTime=2019-10-08 21:41:27, updateAdminName=admin,
            updateTime=2019-10-09 16:12:04}*/


}
