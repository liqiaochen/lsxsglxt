package com.bysj.lsxsglxt.mapper;
import com.bysj.lsxsglxt.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ProductMapper {
    /**
     * 通过id查询商品
     * @param id
     * @return
     */
    Product selectById(Integer id);

    Product selectById2(Integer id);

    /**
     * 查询所有的商品
     * @return
     */
    List<Product> selectListProduct();

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
    Integer saveProduct(Product product);

    @Delete("DELETE FROM product WHERE id=#{id}")
    Integer deleteIdProduct(Integer id);

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
    Integer updateByIdProduct(Product product);


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
    int updateStatus(@Param("ids") int[] ids,@Param("status") int status);


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
    int deleteStatus(@Param("ids")int[] ids);

    /**
     * 查询所有的上架的商品或者下架的商品，按照编号升序
     * @param status
     * @return
     */
    @Select("select product.*"+
            "from product \n"+
            "WHERE product.status=#{status} \n" +
            "ORDER BY code ASC \n")
    @ResultMap("BaseResultMap")
    List<Product> selectListProductStatus(Integer status);


    /**
     * 查询所有的热门商品或者不热门商品并且是上架的，按照销量降序,只显示前10条
     * @param hot
     * @return
     */
    @Select("select product.*"+
            "from product \n"+
            "    WHERE product.hot = #{hot} and product.status=1 \n" +
            "    ORDER BY turnover DESC \n" +
            "   LIMIT 0,10")
    @ResultMap("BaseResultMap")
    List<Product> hotList(Integer hot);

    /**
     * 查询所有的热门商品或者不热门商品并且是上架的并按照类别Id分类，按照销量降序
     * @param hot
     * @param typeId
     * @return
     */
    @Select("select product.*"+
            "from product \n" +
            "    WHERE product.hot = #{hot} and product.status=1 and product.type_id=#{typeId} \n" +
            "    ORDER BY turnover DESC \n")
    @ResultMap("BaseResultMap")
    List<Product> hotListAndType(@Param("hot") Integer hot,@Param("typeId") Integer typeId);

    /**
     * 查询所有的推荐商品或者不推荐的商品并且是上架的，按照销量降序，只显示前5条。
     * @param recommend
     * @return
     */
    @Select("select product.*"+
            "from product \n"+
            "    WHERE product.recommend = #{recommend} and product.status=1 \n" +
            "    ORDER BY turnover DESC \n" +
            "    LIMIT 0,5")
    @ResultMap("BaseResultMap")
    List<Product> recommendList(Integer recommend);

    /**
     * 查询所有的推荐商品或者不推荐的商品并且是上架的并按照类别Id分类，按照编号升序
     * @param recommend
     * @param typeId
     * @return
     */
    @Select("select product.*"+
            "from product \n"+
            "    WHERE product.recommend = #{recommend} and product.status=1 and product.type_id=#{typeId} \n" +
            "    ORDER BY code ASC \n")
    @ResultMap("BaseResultMap")
    List<Product> recommendListAndType(@Param("recommend") Integer recommend,@Param("typeId") Integer typeId);

    /**
     * 更据类别查询商品，按编号升序
     * @param typeId
     * @return
     */
    @Select("select product.*"+
            "from product \n"+
            "    WHERE  product.status=1 and product.type_id=#{typeId} \n" +
            "    ORDER BY code ASC \n")
    @ResultMap("BaseResultMap")
    List<Product> listType(Integer typeId);

    /**
     * 根据title查询
     * @param keyword
     * @return
     */
    @Select("select product.*"+
            "from product \n"+
            "    WHERE  product.status=1 and product.title  LIKE '%${keyword}%' \n" +
            "    ORDER BY code ASC  \n")
    @ResultMap("BaseResultMap")
    List<Product> listLike(String keyword);

}