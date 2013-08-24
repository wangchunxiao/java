package com.decorator;

public class ConcreteDecorator2 extends Decorator
{
	public ConcreteDecorator2(Component component)
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
		System.out.println("¹¦ÄÜC");
	}
}
