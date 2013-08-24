/*
 * method.setAccessible(true);//�ǳ���Ҫ��һ����룬�������Method�ĸ����ж���ķ���������supperess java�ķ���Ȩ��
 * */
package com.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Private 
{
	public static void main(String[] args) throws Exception
	{
			Privater p=new Privater();
			Class<?> classType=p.getClass();
			Method method=classType.getDeclaredMethod("modifierString",new Class[]{String.class});
			
			method.setAccessible(true);//�ǳ���Ҫ��һ����룬�������Method�ĸ����ж���ķ���������supperess java��
									   //����Ȩ��
			String result=(String)method.invoke(p,new Object[]{"liran"});
			
			Field field=classType.getDeclaredField("age");
			field.setAccessible(true);
			field.set(p,25);
			int age=(int)field.get(p);
			
			System.out.println(result + age);
	
	}

	
}
class Privater
{
	private String modifierString(String s)
	{
		return "hello "+s;
	}
	private int age=10;
}