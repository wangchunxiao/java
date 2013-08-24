package com.sampleFactor;

public class Factor
{
	public static Product create(String str)
	{
		if("A".equals(str))
		{
			return new ConcreteProductA();
		}
		else if("B".equals(str))
		{
			return new ConcreteProductB();
		}
		else 
			return null;
	}
}
