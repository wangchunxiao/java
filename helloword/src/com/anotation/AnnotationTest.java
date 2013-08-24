package com.anotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Retention(value=RetentionPolicy.CLASS)//retention用来修饰Annotation的，要想用反射必须是RUNTIME，那是一个enum的值
@Retention(RetentionPolicy.RUNTIME)//默认的属性就是VALUE,所以不用使用valut=这种形式
public @interface AnnotationTest
{
	String value();
	String world() default "world";
	String hello() default "hello";
}
