package com.bysj.lsxsglxt.controller;


import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @ClassName: DefaultController
 * @description: 设置启动页
 * @author: LiQiaoChen
 * @date: Created in 2019/9/28 13:43
 * @version: 1.0
 */
public class DefaultController extends WebMvcConfigurationSupport {
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
}
