package com.bysj.lsxsglxt.controller.admin.shop;


import com.bysj.lsxsglxt.annotation.LoginRequired;
import com.bysj.lsxsglxt.model.Producttype;
import com.bysj.lsxsglxt.service.shop.ShopTypeService;
import com.bysj.lsxsglxt.utils.ServerResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: ShopTypeController
 * @description: 商品的类别
 * @author: LiQiaoChen
 * @date: Created in 2019/10/5 17:21
 * @version: 1.0
 */
@Controller
@RequestMapping("/admin/shop")
public class ShopTypeController {

    @Autowired
    private ShopTypeService shopTypeService;

    /**
     * 商品类别展示并分页
     * @param pageNum
     * @param pageSize
     * @param modelAndView
     * @return
     */
    @LoginRequired(name = "admin")
    @RequestMapping("/shopType.html")
    public ModelAndView reAdminShopType(@RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "5") int pageSize , ModelAndView modelAndView){
        PageInfo<Producttype> pageInfo = shopTypeService.showTypePage(pageNum, pageSize);
        System.out.println(pageInfo);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("admin/shopType");
        return modelAndView;
    }

    @RequestMapping("/saveType")
    @ResponseBody
    public ServerResponse saveType(Producttype producttype){
        System.out.println(producttype);
        ServerResponse serverResponse = shopTypeService.svaeType(producttype);
        return serverResponse;
    }

    @RequestMapping("/startType")
    @ResponseBody
    public ServerResponse startType(int[] ids){
        System.out.println(ids);
        ServerResponse serverResponse = shopTypeService.updateType(ids,1);
        return serverResponse;
    }
    @RequestMapping("/stopType")
    @ResponseBody
    public ServerResponse stopType(int[] ids){
        System.out.println(ids);
        ServerResponse serverResponse = shopTypeService.updateType(ids,2);
        return serverResponse;
    }
    @RequestMapping("deleteType")
    @ResponseBody
    public ServerResponse deleteType(int[] ids){
        System.out.println(ids);
        ServerResponse serverResponse = shopTypeService.deleteType(ids);
        return serverResponse;
    }

    @RequestMapping("deleteIdType")
    public String deleteIdType(int id){
        ServerResponse serverResponse = shopTypeService.deleteIdType(id);
        String msg = serverResponse.getMsg();
        System.out.println(msg);
        if (serverResponse.checkIsSuccess()) {
            return "redirect:shopType.html";
        }
        return "redirect:shopType.html";
    }

    @RequestMapping("updateType")
    @ResponseBody
    public ServerResponse updateType(Producttype producttype){
        System.out.println(producttype);
        ServerResponse serverResponse = shopTypeService.updateByProductType(producttype);
        return serverResponse;
    }

}
