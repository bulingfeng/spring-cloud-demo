package com.bulingfeng.origin.annotation;
import org.springframework.core.annotation.AnnotatedElementUtils;
/**
 * @Author:bulingfeng
 * @Date: 2020/12/20
 */
@SubAnnotation("bulingfeng")
public class TestAnnotation {
    public static void main(String[] args) {
        MyAnnotation myAnnotation = AnnotatedElementUtils.getMergedAnnotation(TestAnnotation.class, MyAnnotation.class);
        System.out.println(myAnnotation);
    }
}
