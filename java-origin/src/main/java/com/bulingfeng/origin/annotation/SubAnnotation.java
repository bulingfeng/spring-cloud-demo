package com.bulingfeng.origin.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
/**
 * @Author:bulingfeng
 * @Date: 2020/12/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@MyAnnotation
public @interface SubAnnotation {

    @AliasFor(annotation=MyAnnotation.class)
    String value() default "";
}
