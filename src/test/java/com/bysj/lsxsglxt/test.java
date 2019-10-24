package com.bysj.lsxsglxt;

import com.bysj.LsxsglxtApplication;
import com.bysj.lsxsglxt.mapper.PictureMapper;
import com.bysj.lsxsglxt.mapper.ProductMapper;
import com.bysj.lsxsglxt.mapper.ProducttypeMapper;
import com.bysj.lsxsglxt.model.Picture;
import com.bysj.lsxsglxt.model.Product;
import com.bysj.lsxsglxt.model.Producttype;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: test
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/9/24 21:20
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LsxsglxtApplication.class)
public class test {
    @Autowired
    private    ProducttypeMapper producttypeMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PictureMapper pictureMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public void Test01(){
        Producttype producttype = new Producttype();
        producttype.setName("饮料");
        producttype.setStatus(1);
        int i = producttypeMapper.saveType(producttype);
        System.out.println(i);
    }

    /**
     * 查询id为1的产品
     */
    @Test
    public void Testall(){
        List<Product> products = productMapper.selectListProduct();
        products.forEach((a)-> System.out.println(a));
    }

    @Test
    public void Test3(){
        Picture picture = pictureMapper.selectById(1);
        System.out.println(picture);
    }

    @Test
    public void Test4(){
        Picture picture =new Picture();
        picture.setLevel(1);
        picture.setTitle("可乐");
        picture.setAdminName("admin");
        picture.setUrl("adasdasdad");
        picture.setTime(new Date());
        picture.setProductId(1);
        Integer integer = pictureMapper.savePicture(picture);
        System.out.println(integer);
        System.out.println(picture);
    }

    /**
     * 测试hotList和recommendList方法
     */
    @Test
    public void  Test5() {
        List<Product> products = productMapper.hotList(1);
        products.forEach((a)-> System.out.println("1:"+a.getHot()));

        List<Product> products1 = productMapper.recommendList(1);
        products1.forEach((a)-> System.out.println("2:"+a.getRecommend()));
    }
}
