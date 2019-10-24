package com.bysj.lsxsglxt.model;


import lombok.Data;

import java.util.List;

/**
 * @ClassName: CartPojo
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/23 22:37
 * @version: 1.0
 */
@Data
public class CartsPojo {

    private List<CartPojo> cartPojo;

    private Double cartTotalPrice;
}
