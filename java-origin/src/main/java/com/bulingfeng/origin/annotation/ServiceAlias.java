package com.bulingfeng.origin.annotation;

import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Author:bulingfeng
 * @Date: 2020/12/20
 */
@Service("test-alias")
public class ServiceAlias {
    public static void main(String[] args) {

        Component component2 = AnnotatedElementUtils.getMergedAnnotation(ServiceAlias.class, Component.class);
        System.out.println(component2);
    }
}
