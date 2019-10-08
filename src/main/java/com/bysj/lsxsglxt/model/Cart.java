package com.bysj.lsxsglxt.model;

import lombok.Data;

@Data
public class Cart {
    private Integer id;

    private Integer userId;

    private Integer productId;

    private Integer num;

    private Double price;


}