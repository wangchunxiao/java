package com.proxy.dynamicproxy;

public interface Subject  //这里我们和之前静态定义的不同是因为后面在proxy的newinstance方法中用的是interface类型
{
	public void doSomething();	
}
