package com.bysj.lsxsglxt.service.shop;
import com.bysj.lsxsglxt.mapper.PictureMapper;
import com.bysj.lsxsglxt.mapper.ProductMapper;
import com.bysj.lsxsglxt.mapper.ProducttypeMapper;
import com.bysj.lsxsglxt.model.Picture;
import com.bysj.lsxsglxt.model.Product;
import com.bysj.lsxsglxt.model.Producttype;
import com.bysj.lsxsglxt.model.ShopPojo;
import com.bysj.lsxsglxt.utils.ServerResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    private ProducttypeMapper producttypeMapper;
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
        conversionProduct(shop,product);
        product.setCreateTime(now);
        product.setCreateAdminName(adminName);
        Producttype producttype = new Producttype();
        producttype.setId(shop.getTypeId());
        product.setTypeId(producttype);
        Integer integer = productMapper.saveProduct(product);

        //插入图片
        Integer id = product.getId();
        Picture picture = getPicture(shop, adminName, now, id);
        List<String> imgs = shop.getImgs();
        //根据图片的多少来创建多少个图片记录
        for (int i=0;i<imgs.size();i++){
            picture.setUrl(imgs.get(i));
            picture.setLevel(i);
            pictureMapper.savePicture(picture);
        }
        return  ServerResponse.createBySuccess("添加商品成功",integer);
    }

    private Picture getPicture(ShopPojo shop, String adminName, Date now, Integer id) {
        Picture picture = new Picture();
        picture.setProductId(id);
        picture.setTitle(shop.getTitle());
        picture.setAdminName(adminName);
        picture.setTime(now);
        return picture;
    }

    private void conversionProduct(ShopPojo shop,Product product) {
        product.setCode(shop.getCode());
        product.setHot(shop.getHot());
        product.setNote(shop.getDescription());
        product.setStock(shop.getStock());
        product.setPrice(shop.getPrice());
        product.setTitle(shop.getTitle());
        product.setUnit(shop.getUnit());
        product.setStatus(shop.getStatus());
    }

    /**
     * 查询商品,
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

    public ServerResponse<Integer> updateByIdProduct(ShopPojo shop,String adminName){
        //修改Product
        Product product=new Product();
        Date now=new Date();
        conversionProduct(shop, product);
        //设置其他 recommend，turnover
        product.setRecommend(shop.getRecommend());
        product.setTurnover(shop.getTurnover());
        //设置时间
        product.setCreateTime(shop.getCreateTime());
        product.setCreateAdminName(shop.getCreateAdminName());
        product.setUpdateTime(now);
        product.setUpdateAdminName(adminName);
        //设置id
        product.setId(shop.getId());
        //获取productType对象
        Producttype producttype = new Producttype();
        producttype.setId(shop.getTypeId());
        product.setTypeId(producttype);
        Integer integer = productMapper.updateByIdProduct(product);
        Integer productId = product.getId();
        //根据图片的路径判断是否存在图片
        List<String> imgs = shop.getImgs();
        int size = imgs.size();
        for (int i=0;i<size;i++){
            String url = imgs.get(i);
            Picture picture = pictureMapper.selectByUrl(url);
            if (picture==null){
                Picture picture2 = getPicture(shop, adminName, now, productId);
                //保证Level不会重复
                picture2.setLevel(i+size);
                picture2.setUrl(url);
                pictureMapper.savePicture(picture2);
            }else {
                Integer level = picture.getLevel();
                Integer pictureId = picture.getId();
                //如果图片顺序发生改变level也一起改变
                if (level!=i){
                    pictureMapper.updateByIdLevelPicture(pictureId, i);
                }
            }
        }
        return  ServerResponse.createBySuccess("修改成功",integer);
    }

    /**
     * 批量修改
     * @param Ids
     * @param status
     * @return
     */
    public ServerResponse updateStatus(int[] Ids,int status){
        int i = productMapper.updateStatus(Ids,status);
        return getServerResponse(Ids, i, "已全部修改。共", "修改一部分，有一些不用改。共", "批量修改失败");

    }

    /**
     * 批量删除
     * @param Ids
     * @return
     */
    public ServerResponse deleteStatus(int[] Ids){
        int i = productMapper.deleteStatus(Ids);
        return getServerResponse(Ids, i, "已全部删除。共", "删除一部分，有一些不用改。共", "批量删除失败");

    }

    private ServerResponse getServerResponse(int[] Ids, int i, String s, String s2, String msg) {
        if (i != 0) {
            if (Ids.length == i) {
                return ServerResponse.createBySuccess(s, i);
            }
            return ServerResponse.createBySuccess(s2, i);
        } else {
            return ServerResponse.createByError(msg);
        }
    }

    /**
     * 查询首页商品，热门以及推荐
     * @return
     */
    public Map<String,List> productIndex (){
        Map<String,List> map = new HashMap<>();
        List<Product> hotList = productMapper.hotList(1);
        List<Product> hotLists =new ArrayList<>();
        hotLists.addAll(hotList);
        List<Product> lightHotList;
        int  size = hotLists.size();
        int length= 3;
        if (size>length) {
            lightHotList = hotLists.subList(0, 2);
            Product p1 = lightHotList.get(0);
            Product p2 = lightHotList.get(1);
            Iterator<Product> it = hotList.iterator();
           while (it.hasNext()) {
               Product product = it.next();
               if (product.getId().equals(p1.getId()) || product.getId().equals(p2.getId())) {
                   it.remove();
               }
           }

        }else {
            lightHotList = hotList;
        }
        List<Product> recommendList = productMapper.recommendList(1);
        List<Producttype> types = producttypeMapper.showTypeStatus(1);
        map.put("lightHotList",lightHotList);
        map.put("hotList",hotList);
        map.put("recommendList",recommendList);
        map.put("types",types);
        return  map;
    }

    /**
     * 更据类别id查询商品
     * @param typeId
     * @return
     */
    public PageInfo<Product> produceType(int pageNum,int pageSize,Integer typeId) {
        PageHelper.startPage(pageNum,pageSize);
        List<Product> products = productMapper.listType(typeId);
        PageInfo<Product> pageTypeInfo = new PageInfo<>(products);
        return pageTypeInfo;
    }

    /**
     * 通过商品id查询商品
     * @param id
     * @return
     */
    public Product productsOne(Integer id){
        Product product = productMapper.selectById2(id);
        return product;
    }

    /**
     * 上下架并分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<Product> productsListPageStatus(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Product> products = productMapper.selectListProductStatus(1);
        PageInfo<Product> pageTypeInfo = new PageInfo<>(products);
        return pageTypeInfo;
    }

    /**
     * 模糊查询，分页
     * @param pageNum
     * @param pageSize
     * @param keyword
     * @return
     */
    public PageInfo<Product> productListLike(int pageNum,int pageSize,String keyword){
        PageHelper.startPage(pageNum,pageSize);
        List<Product> products = productMapper.listLike(keyword);
        PageInfo<Product> pageTypeInfo = new PageInfo<>(products);
        return pageTypeInfo;

    }
}
