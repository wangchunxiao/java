package com.anotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Retention(value=RetentionPolicy.CLASS)//retention��������Annotation�ģ�Ҫ���÷��������RUNTIME������һ��enum��ֵ
@Retention(RetentionPolicy.RUNTIME)//Ĭ�ϵ����Ծ���VALUE,���Բ���ʹ��valut=������ʽ
public @interface AnnotationTest
{
	String value();
	String world() default "world";
	String hello() default "hello";
}
