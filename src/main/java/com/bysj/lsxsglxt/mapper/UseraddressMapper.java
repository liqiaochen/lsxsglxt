package com.bysj.lsxsglxt.mapper;

import com.bysj.lsxsglxt.model.Useraddress;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户地址的dao
 **/
public interface UseraddressMapper {
    /**
     * 通过用户id查询用户地址
     * @param id
     * @return
     */
    @Select("select * from userAddress where user_id=#{id}")
    @Results(id="userAddress",value = {
            @Result(id = true,column ="id",property ="id"),
            @Result(column = "consignee",property ="consignee"),
            @Result(column = "phone",property ="phone"),
            @Result(column = "zipCode",property ="zipCode"),
            @Result(column = "Street",property ="street"),
            @Result(column = "Region",property ="region"),
            @Result(column = "Tag",property ="tag"),
            @Result(column = "user_id",property ="userId"),
    }
    )
    List<Useraddress> selectByUserId(Integer id);

    /**
     * 通过地址id查询地址信息
     * @param id
     * @return
     */
    @Select("select * from userAddress where id=#{id}")
    @ResultMap("userAddress")
    Useraddress selectById(Integer id);

    @Update("UPDATE `useraddress` SET `consignee` = #{consignee}," +
            " `phone` = #{phone}, `zipCode` = #{zipCode}, `Street` = #{street}, " +
            "`Region` = #{region}," +
            " `Tag` = #{tag}, `user_id` = #{userId} WHERE `id` = #{id};")
    Integer  updateUserAddress(Useraddress userAddres);
}