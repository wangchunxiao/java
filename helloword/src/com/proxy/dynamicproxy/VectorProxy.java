/*
 * 1.invocationHandler中的invoke方法，我们调用传进来的真实对象的方法，如果有返回类型，我们不能返回空
 * */

package com.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Vector;

public class VectorProxy implements InvocationHandler
{
	Object obj;
	public VectorProxy(Object obj)
	{
		this.obj=obj;
	}
	
	public static Object factory(Object realobj)
	{
		Class<?> classType=realobj.getClass();
		return Proxy.newProxyInstance(
									  classType.getClassLoader(),
									  realobj.getClass().getInterfaces(),
									  new VectorProxy(realobj));
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable
	{
		// TODO 自动生成的方法存根
		System.out.println("before function invoke "+method);
		if(null!=args)
		{
			for(Object array_obj:args)
			{
				System.out.println(array_obj);
			}
		}
		//method.invoke(proxy,args);
		Object result=method.invoke(obj,args);
		System.out.println("after function invoke "+method);
		return result;
		//return null;因为method有返回类型，如果我们不返回那个返回类型，就会抛出异常，这是文档说的
	}
	public static void main(String[] args)
	{
		 List<String> list=(List)factory(new Vector<String>());
		 list.add("liran");
		 list.add("suixin");
	}
}
