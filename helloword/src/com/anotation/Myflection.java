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
		//�������ΪRetentionPolicy.CLASS���򷵻ؼ٣���ΪJVM���Է���ķ�ʽ���ã�������ʶ��
		
		if(method.isAnnotationPresent(AnnotationTest.class))
		{
			method.invoke(annotationUse,new Object[]{});
			AnnotationTest myannotation=method.getAnnotation(AnnotationTest.class);
			System.out.println(myannotation.hello());
		}
		Annotation[] array_annotation=method.getAnnotations();
		//����ע�ⷵ�ص��������û��annotation���򳤶�Ϊ0��֮ǰҪ��û��Ԫ�غܶ඼�Ƿ���NULL������null���ǲ���ֱ�ӱ�����
		//�����ڷ��س���Ϊ0�����֣����ǿ���ֱ�ӱ���
		for(Annotation annotation_temp:array_annotation)
		{
			System.out.println(annotation_temp);//Ҳֻ���ҵ�RUNTIME��annotation
		}
	}
}
