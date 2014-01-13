package com.proxy.dynamicproxy;

/*������14�꣬���²��Եķ���
 *1.�������쿪����Ϊ����������invoke(proxy,args)����������ѭ��
 *2.��ʵ��֮����������������ԭ�����û��Ū�������������ΪInvocationHandler��ʵ���������и���Ա��������ô��̬����Ķ�̬�����ֲ������ˣ���ʵ
 *��Ȼ�����������object�ģ��Ͳ���ĳһ��������������
 *3.��һ����̬������Խ�����еĸ��ָ����Ķ�����һ�ִ���ʽ�����⣬ʲô��˼������˵�ҵ�һ�ִ���ʽ���Ƕ�ֻ��10��Ǯ�������еĶ��󣻵����������
 *  10��Ǯ�ģ���5��Ǯ�ģ��Ǿ���Ҫ������̬�����ˣ�
 * */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DyTest implements InvocationHandler
{
	public Object realobj;
	public DyTest(Object obj)
	{
		this.realobj = obj;
	}
	
	public static Object factory(Object obj)
	{
		Class classtype= obj.getClass();
		return Proxy.newProxyInstance(classtype.getClassLoader(),classtype.getInterfaces(),new DyTest(obj));
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable
	{
		System.out.println("proxy worked begin!");
		method.invoke(realobj,args);
		System.out.println("proxy worked end!");
		return null;
	}
	
	
	public static void main(String[] args)
	{
		Object t = factory(new String("ddd"));
		t.toString();
	}

}
