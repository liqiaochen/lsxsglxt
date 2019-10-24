package com.bysj.lsxsglxt.model;


import lombok.Data;

/**
 * @ClassName: CartPojo
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/23 23:06
 * @version: 1.0
 */
@Data
public class CartPojo {
    private Integer id;
    private String imgUrl;
    private String title;
    private double price;
    private int num;
    private double total;
}
