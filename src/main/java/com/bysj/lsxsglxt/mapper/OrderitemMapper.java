package com.bysj.lsxsglxt.mapper;

import com.bysj.lsxsglxt.model.Orderitem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface OrderitemMapper {

    /**
     * 通过订单详情id查询订单详情
     * @param id
     * @return
     */
    @Select("select * orderItem  from where id=#{id}")
    @ResultMap("BaseResultMap")
    Orderitem selectById(Integer id);

    /**
     * 添加订单详情
     * @param orderItem
     * @return
     */
    @Insert("insert into order(confirmTime, consignee, createTime, orderCode,\n" +
            "payTime, phone, shipTime, updatename, updatetime, `status`, totalPrice, zipcode,remake,address_id) " +
            "VALUES(#{confirmTime},#{consignee},#{createTime}," +
            "#{orderCode},#{payTime},#{phone},#{shipTime},#{updateName}," +
            "#{updateTime},#{status},#{totalPrice},#{zipCode},#{remake},#{addressId.id}) ")
    @Options(useGeneratedKeys=true, keyProperty="id")
    Integer insertOrder(Orderitem orderItem);


}