package com.bysj.lsxsglxt.mapper;

import com.bysj.lsxsglxt.model.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface ProductMapper {

    public  Product selectById(Integer id);

    public  Product selectById2(Integer id);

    /**
     * 查询所有的商品
     * @return
     */
    public List<Product> selectListProduct();

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
}