package com.bulingfeng.origin.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author:bulingfeng
 * @Date: 2020/12/20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component2
public @interface Service2 {
    @AliasFor(
            annotation = Component2.class
    )
    String value() default "";
}
