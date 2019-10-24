package com.bysj.lsxsglxt.controller;


import com.bysj.lsxsglxt.annotation.LoginRequired;
import com.bysj.lsxsglxt.model.Admin;
import com.bysj.lsxsglxt.model.User;
import com.bysj.lsxsglxt.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.net.URLEncoder;

/**
 * @ClassName: AuthenticationInterceptor
 * @description: 登录拦截器
 * @author: LiQiaoChen
 * @date: Created in 2019/10/10 9:16
 * @version: 1.0
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private  final String adminString ="admin";
    private  final String userString ="user";
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            String scheme = request.getScheme();
            String serverName = request.getServerName();
            int port = request.getServerPort();
            String path = request.getContextPath();
            String basePath = scheme + "://" + serverName + ":" + port + path+"/";
            HttpSession httpSession = request.getSession();
            String msg = "未登录";
            msg = URLEncoder.encode(msg,"UTF-8");

            String name = methodAnnotation.name();
            if (name.equals(adminString)){
                Admin admin = (Admin) httpSession.getAttribute("admin");
                if (admin!=null){
                    return true ;
                }else {
                    System.out.println("Admin为null，未登录");
                    response.sendRedirect(basePath+"admin/adminLogin.html?msg="+msg);
                    return  false;
                }
            }else if (name.equals(userString)){
                User user = (User)httpSession.getAttribute("user");
                if (user!=null){
                    return true;
                }else {
                    System.out.println("User为null，未登录");
                    response.sendRedirect(basePath+"login.html?msg="+msg);
                    return  false;
                }
            }else {
                System.out.println("LoginRequired注解出现了问题");
                response.sendError(500, "登录验证出现问题");
                return  false;
            }
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
