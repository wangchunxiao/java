package com.decorator;

public class ConcreteDecorator1 extends Decorator
{
	public ConcreteDecorator1(Component component)
	{
		super(component);
	}
	
	@Override
	public void doSomething()
	{
		super.doSomething();
		doAnotherthing();
	}
	
	public void doAnotherthing()
	{
		System.out.println("����B");
	}
}
