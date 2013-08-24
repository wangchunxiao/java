package com.proxy.staticproxy;

public class RealSubject extends Subject
{

	@Override
	public void doSomething()
	{
		System.out.println("realsubject doing it!");
	}
	
}
