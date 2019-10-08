package com.bysj.lsxsglxt.service.shop;


import com.bysj.lsxsglxt.mapper.PictureMapper;
import com.bysj.lsxsglxt.mapper.ProductMapper;
import com.bysj.lsxsglxt.model.Picture;
import com.bysj.lsxsglxt.model.Product;
import com.bysj.lsxsglxt.model.Producttype;
import com.bysj.lsxsglxt.model.ShopPojo;
import com.bysj.lsxsglxt.utils.ServerResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: ShopService
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/6 17:00
 * @version: 1.0
 */
@Service
public class ShopService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private PictureMapper pictureMapper;


    /**
     * 添加商品
     * @param shop
     * @param adminName
     * @return
     */
    public ServerResponse<Integer> saveProduct(ShopPojo shop,String adminName){
        //添加Product
        Product product=new Product();
        Date now=new Date();
        conversionProduct(shop, adminName, product, now);
        Producttype producttype = new Producttype();
        producttype.setId(shop.getTypeId());
        product.setTypeId(producttype);
        Integer integer = productMapper.saveProduct(product);

        //插入图片
        Integer id = product.getId();
        Picture picture = new Picture();
        picture.setProductId(id);
        picture.setTitle(shop.getTitle());
        picture.setAdminName(adminName);
        picture.setTime(now);
        List<String> imgs = shop.getImgs();
        for (int i=0;i<imgs.size();i++){
            picture.setUrl(imgs.get(i));
            picture.setLevel(i);
            pictureMapper.savePicture(picture);
        }
        return  ServerResponse.createBySuccess("添加商品成功",integer);
    }

    private void conversionProduct(ShopPojo shop, String adminName, Product product, Date now) {
        product.setCode(shop.getCode());
        product.setCreateTime(now);
        product.setHot(shop.getHot());
        product.setNote(shop.getDescription());
        product.setStock(shop.getStock());
        product.setPrice(shop.getPrice());
        product.setTitle(shop.getTitle());
        product.setUnit(shop.getUnit());
        product.setStatus(shop.getStatus());
        product.setCreateAdminName(adminName);
    }

    /**
     * 查询商品
     * @param pageNum
     * @param pageSize
     * @return
     */
        public PageInfo<Product> productListPage(int pageNum,int pageSize){
            PageHelper.startPage(pageNum,pageSize);
            List<Product> products = productMapper.selectListProduct();
            PageInfo<Product> pageTypeInfo = new PageInfo<>(products);
            return pageTypeInfo;

        }

    /**
     * 更据id删除Product
     * @param id
     * @return
     */
    public Integer deleteIdProduct(Integer id){
         Integer i =productMapper.deleteIdProduct(id);
         return  i;
        }

    /**
     * 更据id查询Product
     * @param id
     * @return
     */
    public Product selectByIdProduct(Integer id){
            Product product = productMapper.selectById(id);
            return  product;
        }


}
