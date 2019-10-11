package com.bysj.lsxsglxt.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: LoginRequired
 * @description: 在需要登录验证的Controller的方法上使用此注解
 * @author: LiQiaoChen
 * @date: Created in 2019/9/28 13:43
 * @version: 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LoginRequired {
   String name() default "";
}
