package com.proxy.dynamicproxy;

public class RealSubject implements Subject
{

	public void doSomething()
	{
		System.out.println("RealSubject DO SOMETHING");
	}

}
