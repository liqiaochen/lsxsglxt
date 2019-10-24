package com.bysj.lsxsglxt.mapper;

import com.bysj.lsxsglxt.model.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderMapper {

    /**
     * 通过订单id查询订单
     * @param id
     * @return
     */
    @Select("select * from order where id=#{id}")
    @ResultMap("BaseResultMap")
    Order selectById(Integer id);

    /**
     * 通过用户id查询订单
     * @param userId
     * @return
     */
    @Select("select * from order where user_id = userId")
    @ResultMap("BaseResultMap")
    List<Order> selectByUserId(Integer userId);

    /**
     * 添加订单
     * @param order
     * @return
     */
    @Insert("insert into order(orderCode,total,userName," +
            "`status`,payment,orderTime,user_id,orderItem_Id) " +
            "VALUES(#{orderCode},#{total},#{userName},#{status},#{payment}," +
            "#{orderTime},#{userId.id},#{orderItemId.id}) ")
    @Options(useGeneratedKeys=true, keyProperty="id")
    Integer InsertOrder(Order order);

    /**
     * 修改订单
     * @param order
     * @return
     */
    @Update("update from order set orderCode=#{orderCode},total=#{total},`status`=#{status}" +
            "payment=#{payment},orderTime=#{orderTime},user_id=#{userId.id},orderItem_Id=#{orderItemId.id}\n" +
            " where id=#{id}")
    Integer updateOrder(Order order);

    /**
     * 修改订单状态
     * @param status
     * @param id
     * @return
     */
    @Update("update from order set `status`=#{status}\n" +
            " where id=#{id}")
    Integer updateOrderStatus(@Param("status") Integer status,@Param("id") Integer id );
}