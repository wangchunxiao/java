package com.Parttern;

public class StrategyPattern2 implements StrategyPattern
{
	@Override
	public int strategy_function(int a, int b)
	{
		// TODO �Զ����ɵķ������
		return a+b;
	}
}
class StrategyPattern3 implements StrategyPattern
{
	@Override
	public int strategy_function(int a, int b)
	{
		// TODO �Զ����ɵķ������
		return a-b;
	}
}
