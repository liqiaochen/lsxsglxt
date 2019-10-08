package com.bysj.lsxsglxt.mapper;

import com.bysj.lsxsglxt.model.Producttype;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProducttypeMapper {
    /**
     * 保存商品类别
     * @param producttype
     * @return
     */
    @Insert("INSERT INTO productType(`name`,`status`) VALUES (#{name},#{status})")
    public int saveType(Producttype producttype);

    /**
     * 查询所有的商品类别
     * @return
     */
    @Select("select * from productType")
    public List<Producttype> showType();

    /**
     * 根据类别状态，查询所有的商品类别,
     * @param status
     * @return
     */
    @Select("select * from productType where status=#{status}")
    public List<Producttype> showTypeStatus(Integer status);

    /**
     * 批量的修改
     * @param ids
     * @param status
     * @return
     */
    @Update("<script>"
            + "UPDATE productType SET status = #{status} WHERE id in "
            + "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>"
            +       "#{id}"
            + "</foreach>"
            +"</script>")
    public int updateType(@Param("ids") int[] ids,@Param("status") int status);


    /**
     * 批量的删除
     * @param ids
     * @return
     */
    @Delete("<script>"
            + "delete from  productType  WHERE id in "
            + "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>"
            +       "#{id}"
            + "</foreach>"
            +"</script>")
    public int deleteType(@Param("ids")int[] ids);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @Delete("delete from productType  WHERE id = #{id}")
    public int deleteByIdType(int id);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    @Select("select * from productType  WHERE id = #{id}")
    public Producttype showByIdType(String id);

    /**
     * 修改类型
     * @param productType
     * @return
     */
    @Update("update productType set status = #{status},name = #{name}  WHERE id = #{id}")
    public int updateByProductType(Producttype productType);
}