package com.reflect;

import java.lang.reflect.Method;

public class ClassForNameTest
{
	public static void main(String[] args) throws Exception
	{
		Class<?> c1 = Class.forName("java.lang.String");
		Method[] method_array=c1.getDeclaredMethods();
		for(Method m2:method_array)
		{
			System.out.println(m2);
		}
	}
}
