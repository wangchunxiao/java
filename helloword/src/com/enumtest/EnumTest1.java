package com.enumtest;

public class EnumTest1
{
	public static void main(String[] args)
	{
		//enumCompareTo(OpConstant.valueOf(args[0]));
		for(OpConstant c:OpConstant.values())
		{
			System.out.printf("%d,%s\n",c.ordinal(),c);
		}
	}
	
	public static void enumCompareTo(OpConstant constant)
	{
		System.out.println(constant);
		for(OpConstant c:OpConstant.values())
		{
			System.out.println(constant.compareTo(c));
		}
	}
		
}

enum OpConstant
{
	TURN_LEFT,TURN_RIGHT,SHOOT
}
