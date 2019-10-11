package com.bysj.lsxsglxt.mapper;

import com.bysj.lsxsglxt.model.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    /**
     * 根据用户名密码查询一个用户
     * @param name
     * @param password
     * @return
     */
    @Select("select * from admin where username=#{name} and password =#{password}")
    Admin selectByName(@Param("name") String name,@Param("password") String password);

    /**
     * 修改管理员除了密码
     * @param admin
     * @return
     */
    @Update("UPDATE `admin` SET `username` = #{username}," +
            "`nickName` = #{nickName}, `email` = #{email}, `remark` = #{remark} WHERE `id` = #{id};")
    Integer updateAdminByID(Admin admin);

    /**
     * 修改密码
     * @param password
     * @param id
     * @return
     */
    @Update("UPDATE `admin` SET `password` = #{password},"
            +"where id=#{id}")
    Integer updatePasswordById(@Param("password") String password,@Param("id") Integer id);

    @Update("UPDATE `admin` SET `url` = #{url},"
            +"where id=#{id}")
    Integer  updateUrlById(@Param("url") String url,@Param("id") Integer id);
}