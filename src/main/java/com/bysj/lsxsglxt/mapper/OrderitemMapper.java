package com.bysj.lsxsglxt.mapper;
import com.bysj.lsxsglxt.model.Orderitem;
import org.apache.ibatis.annotations.*;

import java.util.Date;

public interface OrderitemMapper {

    /**
     * 通过订单详情id查询订单详情
     * @param id
     * @return
     */
    @Select("select * from orderItem   where id=#{id}")
    @ResultMap("BaseResultMap")
    Orderitem selectById(Integer id);

    /**
     * 添加订单详情
     * @param orderItem
     * @return
     */
    @Insert("insert into orderItem(confirmTime, createTime, orderCode,\n" +
            "payTime, phone, shipTime, updatename, updatetime, `status`, totalPrice,remake,address_id) " +
            "VALUES(#{confirmTime},#{createTime}," +
            "#{orderCode},#{payTime},#{phone},#{shipTime},#{updateName}," +
            "#{updateTime},#{status},#{totalPrice},#{remake},#{addressId.id}) ")
    @Options(useGeneratedKeys=true, keyProperty="id")
    Integer insertOrder(Orderitem orderItem);


    /**
     * 通过修改订单详情变成支付
     * @param id
     * @param status
     * @param now
     * @return
     */
    @Update("update orderItem set status=#{status},payTime=#{now} where id=#{id}")
    Integer updateOrderItemStatusPay(@Param("id") Integer id,@Param("status") Integer status,@Param("now") Date now);

    /**
     * 通过修改订单详情变成收货
     * @param id
     * @param status
     * @param now
     * @return
     */
    @Update("update orderItem set status=#{status},confirmTime=#{now} where id=#{id}")
    Integer updateOrderItemStatusReceipt(@Param("id") Integer id,@Param("status") Integer status,@Param("now") Date now);

    /**
     * 通过修改订单详情变成发货
     * @param id
     * @param status
     * @param now
     * @param name
     * @return
     */
    @Update("update orderItem set status=#{status},shipTime=#{now},updateName=#{name} where id=#{id}")
    Integer updateOrderItemStatusDeliver (@Param("id") Integer id,@Param("status") Integer status,
                                          @Param("now") Date now,@Param("name") String name);


    /**
     * 通过修改订单详情变成取消订单
     * @param id
     * @param status
     * @return
     */
    @Update("update orderItem set status=#{status} where id=#{id}")
    Integer updateOrderItemStatusCancel(@Param("id") Integer id,@Param("status") Integer status);

}