package com.bysj.lsxsglxt.service.user;


import com.bysj.lsxsglxt.mapper.AdminMapper;
import com.bysj.lsxsglxt.mapper.UserMapper;
import com.bysj.lsxsglxt.mapper.UseraddressMapper;
import com.bysj.lsxsglxt.model.Admin;
import com.bysj.lsxsglxt.model.User;
import com.bysj.lsxsglxt.model.Useraddress;
import com.bysj.lsxsglxt.utils.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

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
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UseraddressMapper userAddresMapper;


    public Admin login(String name, String password){
        Admin admin = adminMapper.selectByName(name, password);
        if (admin!=null){
            return admin;
        }else
            {
                return  null;
            }
    }

    public ServerResponse<Admin> updateAdmin(Admin admin){
        Integer integer = adminMapper.updateAdminByID(admin);
        if (integer==0){
            return ServerResponse.createByError("更新失败");
        }
        Admin admin1 = adminMapper.selectByPrimaryKey(admin.getId());
        return ServerResponse.createBySuccess("更新成功",admin1);
    }

    public ServerResponse<Admin> updateAdminUrl(String url,Integer id){
        Integer integer = adminMapper.updateUrlById(url,id);
        if (integer==0){
            return ServerResponse.createByError("图片保存失败");
        }
        Admin admin1 = adminMapper.selectByPrimaryKey(id);
        return ServerResponse.createBySuccess("图片保存成功",admin1);
    }

    public ServerResponse<Admin> rePassword(String password,Integer id){
        Integer integer = adminMapper.updatePasswordById(password,id);
        if (integer==0){
            return ServerResponse.createByError("密码更新失败");
        }
        Admin admin1 = adminMapper.selectByPrimaryKey(id);
        return ServerResponse.createBySuccess("密码更新成功",admin1);
    }

    /**
     * 刷新session
     * @param admin
     * @param session
     * @return
     */
    public ServerResponse reSession(Admin admin,HttpSession session){
        session.removeAttribute("admin");
        session.setAttribute("admin", admin);
        return ServerResponse.createBySuccess("成功",admin);
    }
    public ServerResponse reSession(User user,HttpSession session){
        session.removeAttribute("user");
        session.setAttribute("user", user);
        return ServerResponse.createBySuccess("成功",user);
    }

    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
    public User loginUser(String name, String password){
        User user = userMapper.selectUserOne(name, password);
        if (user!=null){
            System.out.println(user);
            return user;
        }else
        {
            return  null;
        }
    }

    /**
     * 注册
     * @param userName
     * @param passWord
     * @return
     */
    public ServerResponse<Integer> saveUser(String userName,String passWord){
        Integer i = userMapper.saveUser(userName,passWord);
        if (i==0){
            return ServerResponse.createByError("注册失败");
        }
        return ServerResponse.createBySuccess("注册成功",i);
    }

    public ServerResponse<User> updateUser(User user){
        Integer i = userMapper.updateUser(user);
        if (i==0){
            return ServerResponse.createByError("更新失败");
        }
        User user1 = userMapper.selectUserOneById(user.getId());
        return ServerResponse.createBySuccess("更新成功",user1);
    }

    public ServerResponse<Integer> deleteUser(Integer id){
        Integer i = userMapper.deleteById(id);
        if (i==0){
            return ServerResponse.createByError("更新失败");
        }
        return ServerResponse.createBySuccess("更新成功",i);
    }


    public ServerResponse<Useraddress> selectUserAddressOne(Integer id){
        Useraddress useraddress = userAddresMapper.selectById(id);
        if (useraddress!=null){
            return ServerResponse.createBySuccess("查询成功",useraddress);
        }
        return ServerResponse.createByError("查询失败");

    }

    public Boolean updateUserAddress(Useraddress useraddress,Integer userId){
        useraddress.setUserId(userId);
        Integer integer = userAddresMapper.updateUserAddress(useraddress);
        if (integer!=null){
            return true;
        }
        return false;
    }

    public Boolean insertUserAddress(Useraddress useraddress,Integer userId){
        useraddress.setUserId(userId);
        Integer integer = userAddresMapper.insertUserAddress(useraddress);
        if (integer!=null){
            return true;
        }
        return false;
    }

}
