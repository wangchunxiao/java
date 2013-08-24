/*
 * 1.invocationHandler�е�invoke���������ǵ��ô���������ʵ����ķ���������з������ͣ����ǲ��ܷ��ؿ�
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
		// TODO �Զ����ɵķ������
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
		//return null;��Ϊmethod�з������ͣ�������ǲ������Ǹ��������ͣ��ͻ��׳��쳣�������ĵ�˵��
	}
	public static void main(String[] args)
	{
		 List<String> list=(List)factory(new Vector<String>());
		 list.add("liran");
		 list.add("suixin");
	}
}