/*
 * method.setAccessible(true);//非常重要的一句代码，这个是在Method的父类中定义的方法，可以supperess java的访问权限
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
			
			method.setAccessible(true);//非常重要的一句代码，这个是在Method的父类中定义的方法，可以supperess java的
									   //访问权限
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