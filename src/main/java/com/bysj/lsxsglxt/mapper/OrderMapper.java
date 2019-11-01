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
    @Select("select * from `order` where id=#{id}")
    @ResultMap("BaseResultMap")
    Order selectById(Integer id);

    /**
     * 查询所有订单
     * @return
     */
    @Select("select * from `order`")
    @ResultMap("BaseResultMap")
    List<Order> selectOrderAll();

    /**
     * 通过用户id查询订单
     * @param userId
     * @return
     */
    @Select("select * from `order` where user_id = #{userId}")
    @ResultMap("BaseResultMap")
    List<Order> selectByUserId(Integer userId);

    /**
     * 通过用户id和订单状态查询订单
     * @param userId
     * @param status
     * @return
     */
    @Select("select * from `order` where user_id = #{userId} and status =#{status}")
    @ResultMap("BaseResultMap")
    List<Order> selectByUserIdStatus(@Param("userId") Integer userId,@Param("status")Integer status);

    /**
     * 添加订单
     * @param order
     * @return
     */
    @Insert("insert into `order`(orderCode,total," +
            "`status`,payment,orderTime,user_id,orderItem_Id) " +
            "VALUES(#{orderCode},#{total},#{status},#{payment}," +
            "#{orderTime},#{userId.id},#{orderItemId.id}) ")
    @Options(useGeneratedKeys=true, keyProperty="id")
    Integer insertOrder(Order order);

    /**
     * 修改订单
     * @param order
     * @return
     */
    @Update("update  `order` set orderCode=#{orderCode},total=#{total},`status`=#{status}" +
            "payment=#{payment},orderTime=#{orderTime},user_id=#{userId.id},orderItem_Id=#{orderItemId.id}\n" +
            " where id=#{id}")
    Integer updateOrder(Order order);

    /**
     * 修改订单状态
     * @param status
     * @param id
     * @return
     */
    @Update("update  `order` set `status`=#{status}\n" +
            " where id=#{id}")
    Integer updateOrderStatus(@Param("status") Integer status,@Param("id") Integer id );
}