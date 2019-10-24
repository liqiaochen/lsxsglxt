package com.bysj.lsxsglxt.service.shop;
import com.bysj.lsxsglxt.mapper.CartMapper;
import com.bysj.lsxsglxt.model.Cart;
import com.bysj.lsxsglxt.model.CartPojo;
import com.bysj.lsxsglxt.model.User;
import com.bysj.lsxsglxt.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: CartService
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/23 14:44
 * @version: 1.0
 */
@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;



    public ServerResponse<Integer> addCart(Cart cart,Integer userId){
        User user = new User();
        user.setId(userId);
        cart.setUserId(user);
        Integer integer = cartMapper.insertCart(cart);
        if (integer!=null){
            return  ServerResponse.createBySuccess("添加成功", integer);
        }else {

            return ServerResponse.createByError("添加失败");
        }
    }

    public ServerResponse<Integer> deleteCart(Integer id){
        Integer integer = cartMapper.deleteCartById(id);
        if (integer!=null){
            return  ServerResponse.createBySuccess("删除成功", integer);
        }else {

            return ServerResponse.createByError("删除成功");
        }
    }

    /**
     * 获取用户的购物车
     * @param userId
     * @return
     */
    public List<Cart> cartListUser(Integer userId){
        List<Cart> carts = cartMapper.cartListByUserId(userId);
        return carts;
    }

    /**
     * 修改购物车
     * @param cartPojo
     * @return
     */
    public ServerResponse<Integer> updateCart(List<CartPojo> cartPojo){
        Cart cart ;
        int i=0;
        for (CartPojo pojo : cartPojo){
            cart   = new Cart();
            Integer id = pojo.getId();
            String title = pojo.getTitle();
            double price = pojo.getPrice();
            int num = pojo.getNum();
            double total = pojo.getTotal();
            cart.setId(id);
            cart.setProductName(title);
            cart.setPrice(price);
            cart.setTotal(total);
            cart.setNum(num);
            Integer integer = cartMapper.updateQuXiaoCart(cart);
            i=i+integer;
        }
        if (i==cartPojo.size()){
            return ServerResponse.createBySuccess("成功", i);
        }else {
            return ServerResponse.createBySuccess("失败", i);
        }
    }
}
