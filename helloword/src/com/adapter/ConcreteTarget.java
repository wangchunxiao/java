package com.adapter;

public class ConcreteTarget implements Target
{

	@Override
	public void request()
	{
		System.out.println("这是我的目标角色实现类的函数实现,最终是要将其它的接口和");
	}

}
