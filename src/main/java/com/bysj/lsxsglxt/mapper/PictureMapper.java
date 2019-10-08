package com.bysj.lsxsglxt.mapper;

import com.bysj.lsxsglxt.model.Picture;
import org.apache.ibatis.annotations.*;

public interface PictureMapper {

    /**
     * 根据产品id查询产品图片
     * @param id
     * @return
     */
    @Results(id = "selectByProductId",value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "level",property = "level"),
            @Result(column = "title",property = "title"),
            @Result(column = "time",property = "time"),
            @Result(column = "url",property = "url"),
            @Result(column = "Admin_name",property = "adminName"),
            @Result(column = "product_id",property = "productId")
    }
    )
    @Select("select * from picture where product_id=#{id}")
    public Picture selectByProductId(Integer id);

    /**
     * 根据图片id查询产品
     * @param id
     * @return
     */
    @ResultMap(value = "selectByProductId")
    @Select("select * from picture where id=#{id}")
    public Picture selectById(Integer id);

    /**
     * 添加一个图片
     * @param picture
     * @return
     */
    @Insert("INSERT INTO picture(`level`,title,time,url,admin_name,product_id) VALUES(#{level},#{title},#{time},#{url},#{adminName},#{productId});")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public Integer savePicture(Picture picture);


}