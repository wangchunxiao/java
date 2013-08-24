package com.Parttern;

public class StrategyEnvironment
{
	private StrategyPattern strategy;
	StrategyEnvironment(StrategyPattern strategy)
	{
		this.strategy=strategy;
	}
	public StrategyPattern getStrategy()
	{
		return strategy;
	}
	public void setStrategy(StrategyPattern strategy)
	{
		this.strategy = strategy;
	}
	public int calculate(int a,int b)
	{
		return strategy.strategy_function(a,b);
	}
}
