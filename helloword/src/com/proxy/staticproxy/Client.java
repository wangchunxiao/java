package com.proxy.staticproxy;

public class Client
{
	public static void main(String[] args)
	{
		ProxySubject prosubject=new ProxySubject(); 
		prosubject.doSomething();
	}
}
