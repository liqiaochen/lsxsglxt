package com.bysj.lsxsglxt.mapper;

import com.bysj.lsxsglxt.model.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CartMapper {

    /**
     * 查询用户的购物车信息,并且是未生成订单的
     * @param id
     * @return
     */
    @Select("select * from cart where user_id=#{id} and status=1")
    @ResultMap("BaseResultMap")
    List<Cart> cartListByUserId(Integer id);

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    @Insert("insert into cart(user_id,product_id,product_name,num,price,total,status)" +
            " VALUES (#{userId.id},#{productId.id}," +
            "#{productName},#{num},#{price},#{total},#{status}) ")
    Integer insertCart(Cart cart);

    /**
     * 通过id删除购物车
     * @param id
     * @return
     */
    @Delete("delete from cart where id=#{id}")
    Integer deleteCartById(Integer id);

    /**
     * 修改购物车
     * @param cart
     * @return
     */
    @Update("update  cart set user_id =#{userId.id} , product_id =#{productId.id}," +
            "product_name = #{productName},num = #{num},price = #{price}," +
            "total = #{total} " +
            "where id=#{id}")
    Integer updateCart(Cart cart);

    /**
     * 修改购物车(取消了生成订单)
     * @param cart
     * @return
     */
    @Update("update  cart set product_name = #{productName},num = #{num} ,price = #{price}," +
            "total = #{total} " +
            "where id=#{id}")
    Integer updateQuXiaoCart(Cart cart);

    /**
     * 修改购物车(生成订单)
     * @param cart
     * @return
     */
    @Update("update  cart set product_name = #{productName},num = #{num} ,price = #{price}," +
            "total = #{total} ，status=#{status}" +
            "where id=#{id}")
    Integer updateCartStatus(Cart cart);

    /**
     * 通过订单id查询购物车信息
     * @param orderId
     * @return
     */
    @Select("select * from cart where orderId=#{orderId}")
    @ResultMap("BaseResultMap")
    List<Cart> selectByOrderId(Integer orderId);
}