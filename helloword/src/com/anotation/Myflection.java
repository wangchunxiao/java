package com.anotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Myflection
{
	public static void main(String[] args) throws Exception
	{
		AnnotationUse annotationUse=new AnnotationUse();
		//Class<AnnotationTest> classType=annotationUse.getClass();
		Class<AnnotationUse> classType=AnnotationUse.class;
		Method method=classType.getMethod("output",new Class[]{});
		
		System.out.println(method.isAnnotationPresent(AnnotationTest.class));
		//如果设置为RetentionPolicy.CLASS，则返回假，因为JVM不以反射的方式调用，即不能识别
		
		if(method.isAnnotationPresent(AnnotationTest.class))
		{
			method.invoke(annotationUse,new Object[]{});
			AnnotationTest myannotation=method.getAnnotation(AnnotationTest.class);
			System.out.println(myannotation.hello());
		}
		Annotation[] array_annotation=method.getAnnotations();
		//这里注意返回的数组如果没有annotation，则长度为0，之前要是没有元素很多都是返回NULL，对于null我们不能直接遍历，
		//而对于返回长度为0的这种，我们可以直接遍历
		for(Annotation annotation_temp:array_annotation)
		{
			System.out.println(annotation_temp);//也只能找到RUNTIME的annotation
		}
	}
}
