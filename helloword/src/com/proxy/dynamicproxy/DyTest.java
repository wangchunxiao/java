package com.proxy.dynamicproxy;

/*这是我14年，重新测试的方法
 *1.我异想天开的认为可以这样，invoke(proxy,args)，这样将死循环
 *2.其实我之所以上面那样做的原因就是没有弄清楚，我以致认为InvocationHandler的实现类里面有个成员变量，那么动态代理的动态就体现不出来了，其实
 *不然，这个类型是object的，就不受某一个具体对象的限制
 *3.而一个动态代理可以解决所有的各种各样的对象在一种代理方式的问题，什么意思，就是说我的一种代理方式就是都只收10块钱，对所有的对象；但是如果有收
 *  10块钱的，有5块钱的，那就需要两个动态代理了！
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
