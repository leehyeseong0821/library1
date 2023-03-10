package com.korit.library.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
        //클래스               메소드
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface ValidAspect {


}
