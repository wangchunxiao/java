package com.decorator;

public class Client
{
	public static void main(String[] args)
	{
		//节点流
		Component component1=new ConcreteComponent();
	
		//过滤流
		Component component2=new ConcreteDecorator1(component1);
	
		//过滤流
		Component component3=new ConcreteDecorator2(component2);
		
		
		
		component3.doSomething();
	}
}
