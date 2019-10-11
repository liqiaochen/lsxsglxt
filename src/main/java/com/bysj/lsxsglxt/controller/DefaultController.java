package com.bysj.lsxsglxt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName: DefaultController
 * @description: 设置启动页
 * @author: LiQiaoChen
 * @date: Created in 2019/9/28 13:43
 * @version: 1.0
 */
@Configuration
public class DefaultController extends WebMvcConfigurationSupport {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;
    /**
     *设置欢迎页
     * @param registry
     * @see ViewControllerRegistry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController( "/" ).setViewName( "forward:/index.html" );
        //设置优先级，Ordered用来处理相同接口实现类的优先级问题
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        super.addViewControllers( registry );
    }



    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
       /* List<String> list = new ArrayList<>();
        list.add("/admin/login");
        list.add("/user/login");
        list.add("/index.html");
        list.add("/");
        list.add("/display.html");*/
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("classpath:/static/upload/");
        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

}
