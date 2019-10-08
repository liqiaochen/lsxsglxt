package com.bysj.lsxsglxt.service.shop;


import com.bysj.lsxsglxt.mapper.ProducttypeMapper;
import com.bysj.lsxsglxt.model.Producttype;
import com.bysj.lsxsglxt.utils.ServerResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: shopType
 * @description: 商品类别管理
 * @author: LiQiaoChen
 * @date: Created in 2019/10/5 17:11
 * @version: 1.0
 */
@Service
@Transactional(readOnly=false,rollbackFor=Exception.class)
public class ShopTypeService {

    @Autowired
    private ProducttypeMapper producttypeMapper;

    /**
     * 保存一个商品类别
     * @param producttype
     * @return
     */
    public ServerResponse svaeType(Producttype producttype){
        int i = producttypeMapper.saveType(producttype);
        if (i!=0) {
            return ServerResponse.createBySuccess("成功",i);
        }
        else {
            return  ServerResponse.createByError("失败");
        }
    }

    /**
     * 查询所有的商品类别,不分页分页
     * @return
     */
    public List<Producttype> showType(){
        List<Producttype> productTypes = producttypeMapper.showType();
        return  productTypes;
    }

    /**
     * 根据类别状态，查询所有的商品类别,
     * @param status
     * @return
     */
    public List<Producttype> showType(Integer status){
        List<Producttype> productTypes = producttypeMapper.showTypeStatus(status);
        return  productTypes;
    }

    /**
     * 查询所有的商品类别,并进行分页
     * @return
     */
    public PageInfo<Producttype> showTypePage(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Producttype> productTypes = producttypeMapper.showType();
        PageInfo<Producttype> pageTypeInfo = new PageInfo<>(productTypes);
        return  pageTypeInfo;
    }

    /**
     * 批量修改
     * @param Ids
     * @param status
     * @return
     */
    public ServerResponse updateType(int[] Ids,int status){
        int i = producttypeMapper.updateType(Ids,status);
        if (i!=0) {
            if (Ids.length==i){
                return ServerResponse.createBySuccess("已全部修改。共",i);
            }
            return ServerResponse.createBySuccess("修改一部分，有一些不用改。共",i);
        }
        else {
            return  ServerResponse.createByError("批量修改失败");
        }

    }

    /**
     * 批量删除
     * @param Ids
     * @return
     */
    public ServerResponse deleteType(int[] Ids){
        int i = producttypeMapper.deleteType(Ids);
        if (i!=0) {
            if (Ids.length==i){
                return ServerResponse.createBySuccess("已全部删除。共",i);
            }
            return ServerResponse.createBySuccess("删除一部分，有一些不用改。共",i);
        }
        else {
            return  ServerResponse.createByError("批量删除失败");
        }

    }

    /**
     * 删除一个
     * @param id
     * @return
     */
    public ServerResponse deleteIdType(int id){
        int i = producttypeMapper.deleteByIdType(id);
        if (i!=0) {
            return ServerResponse.createBySuccess("成功",i);
        }
        else {
            return  ServerResponse.createByError("失败");
        }

    }

    /**
     * 通过ProductType对产品类型进行修改
     * @param producttype
     * @return
     */
    public ServerResponse updateByProductType(Producttype producttype){
        int i = producttypeMapper.updateByProductType(producttype);
        if (i!=0) {
            return ServerResponse.createBySuccess("修改成功",i);
        }
        else {
            return  ServerResponse.createByError("修改失败");
        }

    }
}
