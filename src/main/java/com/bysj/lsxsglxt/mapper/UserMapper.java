package com.bysj.lsxsglxt.mapper;

import com.bysj.lsxsglxt.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有的用户并分页
     * @return
     */
    @Select("select * from user")
    List<User> showListPage();

    /**
     * 注册用户
     * @param userName
     * @param passWord
     * @return
     */
    @Insert("insert into user (username,password,`balance`, `Signature`, `hobby`, `telPhone`, `remark`) " +
            "values(#{userName},#{passWord},0,'无','无','无','无')")
    Integer saveUser(String userName,String passWord);

    /**
     * 根据用户名和密码查询出一个用户
     * @param userName
     * @param passWord
     * @return
     */
    @Select("select * from user where username=#{userName} and password=#{passWord}")
    @Results(id="user",
            value={ @Result(id=true,column = "id",property = "id"),
                    @Result(column = "username",property = "userName"),
                    @Result(column = "password",property = "passWord"),
                    @Result(column = "balance",property = "balance"),
                    @Result(column = "Signature",property = "signature"),
                    @Result(column = "hobby",property = "hobby"),
                    @Result(column = "telPhone",property = "telPhone"),
                    @Result(column = "remark",property = "remark"),
                    @Result(column = "id",property="useraddress",javaType=List.class,
                            many = @Many(select="com.bysj.lsxsglxt.mapper.UseraddressMapper.selectByUserId"))
            }
    )
    User selectUserOne(String userName,String passWord);

    /**
     * 更据id查询用户
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    @ResultMap("user")
    User selectUserOneById(Integer id);

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @Update("update user set  username=#{userName},signature=#{signature}," +
            "hobby=#{hobby},telPhone=#{telPhone},remark=#{remark}" +
            "where id=#{id}")
    Integer updateUser(User user);

    /**
     * 通过id删除用户
     * @param id
     * @return
     */
    @Delete("delete from user where id=#{id}")
    Integer deleteById(Integer id);
}