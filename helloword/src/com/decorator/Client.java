package com.decorator;

public class Client
{
	public static void main(String[] args)
	{
		//�ڵ���
		Component component1=new ConcreteComponent();
	
		//������
		Component component2=new ConcreteDecorator1(component1);
	
		//������
		Component component3=new ConcreteDecorator2(component2);
		
		
		
		component3.doSomething();
	}
}
