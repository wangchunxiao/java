package com.Parttern;

public class StrategyPattern2 implements StrategyPattern
{
	@Override
	public int strategy_function(int a, int b)
	{
		// TODO 自动生成的方法存根
		return a+b;
	}
}
class StrategyPattern3 implements StrategyPattern
{
	@Override
	public int strategy_function(int a, int b)
	{
		// TODO 自动生成的方法存根
		return a-b;
	}
}
