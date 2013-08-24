package com.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler
{
	Object obj;
		
	public DynamicSubject(Object obj)
	{
		this.obj=obj;
	}
	
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable
	{
		System.out.println("danamic do something in invoke function before the real do!");
		//System.out.println("proxy==obj:   "+(proxy==obj));System.out.println(proxy.getClass()+"    "+obj.getClass());
		method.invoke(obj,args);
		System.out.println("danamic do something in invoke function after the real do!");		
		return null;
	}
}
