package com.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client
{
	public static void main(String[] args)
	{
			RealSubject realsubject=new RealSubject();
	
			InvocationHandler handle=new DynamicSubject(realsubject);
			Class<?> classType=handle.getClass();
			Subject subject=(Subject)Proxy.newProxyInstance(classType.getClassLoader(),
															realsubject.getClass().getInterfaces(),
															handle);
			subject.doSomething();
	}
}
