package com.bulingfeng.origin.annotation;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;
/**
 * @Author:bulingfeng
 * @Date: 2020/12/20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface Component2 {

    String value() default "";
}
