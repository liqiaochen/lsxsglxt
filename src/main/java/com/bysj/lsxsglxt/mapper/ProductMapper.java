package com.bysj.lsxsglxt.mapper;

import com.bysj.lsxsglxt.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductMapper {

    public  Product selectById(Integer id);

    public  Product selectById2(Integer id);

    /**
     * 查询所有的商品
     * @return
     */
    public List<Product> selectListProduct();

    /**
     * 添加一个商品
     * @param product
     * @return
     */
    @Insert("INSERT INTO `product`(`code`, `title`, " +
            "`price`, `note`, `stock`, " +
            "`hot`, `unit`, `status`, " +
            "`createTime`, `createAdmin_name`,`type_id`) " +
            "VALUES (#{code},#{title},#{price},#{note},#{stock}," +
            "#{hot},#{unit},#{status},#{createTime},#{createAdminName},#{typeId.id});")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public Integer saveProduct(Product product);

    @Delete("DELETE FROM product WHERE id=#{id}")
    public Integer deleteIdProduct(Integer id);

    /**
     * 根据id修改一个商品
     * @param product
     * @return
     */
    @Update("UPDATE `product` SET " +
            "`code` = #{code}, `title` = #{title}, `price` = #{price}," +
            " `note` = #{note}, `stock` = #{stock}," +
            " `recommend` = #{recommend}, `turnover` = #{turnover}, " +
            "`hot` = #{hot}, `unit` = #{unit}, `status` = #{status}, " +
            "`createTime` = #{createTime}, `createAdmin_name` = #{createAdminName}, " +
            "`updateTime` = #{updateTime}, `updateAdmin_name` = #{updateAdminName}, " +
            "`type_id` = #{typeId.id} WHERE `id` = #{id};")
    public Integer updateByIdProduct(Product product);


    /**
     * 批量的修改
     * @param ids
     * @param status
     * @return
     */
    @Update("<script>"
            + "UPDATE product SET   status= #{status} WHERE id in "
            + "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>"
            +       "#{id}"
            + "</foreach>"
            +"</script>")
    public int updateStatus(@Param("ids") int[] ids,@Param("status") int status);


    /**
     * 批量的删除
     * @param ids
     * @return
     */
    @Delete("<script>"
            + "delete from  product  WHERE id in "
            + "<foreach item='id' index='index' collection='ids' open='(' separator=',' close=')'>"
            +       "#{id}"
            + "</foreach>"
            +"</script>")
    public int deleteStatus(@Param("ids")int[] ids);

}