package com.bysj.lsxsglxt.service.user;


import com.bysj.lsxsglxt.mapper.AdminMapper;
import com.bysj.lsxsglxt.model.Admin;
import com.bysj.lsxsglxt.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserService
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/10 9:04
 * @version: 1.0
 */
@Service
public class UserService {
    @Autowired
    private AdminMapper adminMapper;


    public Admin login(String name, String password){
        Admin admin = adminMapper.selectByName(name, password);
        if (admin!=null){
            return admin;
        }else
            {
                return  null;
            }
    }

    public ServerResponse<Integer> updateAdmin(Admin admin){
        Integer integer = adminMapper.updateAdminByID(admin);
        if (integer==0){
            return ServerResponse.createByError("更新失败");
        }
        return ServerResponse.createBySuccess("更新成功",integer);
    }

    public ServerResponse<Integer> updateAdminUrl(String url,Integer id){
        Integer integer = adminMapper.updateUrlById(url,id);
        if (integer==0){
            return ServerResponse.createByError("图片保存失败");
        }
        return ServerResponse.createBySuccess("图片保存成功",integer);
    }

    public ServerResponse<Integer> rePassword(String password,Integer id){
        Integer integer = adminMapper.updatePasswordById(password,id);
        if (integer==0){
            return ServerResponse.createByError("密码更新失败");
        }
        return ServerResponse.createBySuccess("密码更新成功",integer);
    }
}
