/*
 * 这是Adapter的核心角色,就是用它来将源角色转化成为目标角色
 * */

package com.adapter;

public class Adapter extends Adaptee implements Target
{
	@Override
	public void request()
	{
		//我用目标角色的方法实现了源角色要实现的功能
		doSomething();
	}
	
}
