package com.proxy.staticproxy;

public class ProxySubject extends Subject
{
	private RealSubject realsubject;
	
	@Override
	public void doSomething()
	{
		System.out.println("这是代理在调用真实角色的方法之前，调用的方法，相当于包装了真实方法，收了中介费");
		if(realsubject==null)
			realsubject=new RealSubject();
		realsubject.doSomething();	
		System.out.println("这是代理在调用真实角色的方法之后，调用的方法，相当于包装了真实方法，收了中介费");
	}
}
