/*
 * 用来测试system.gc这个函数以及java是否是引用计数的方法来回收内存
 * */
package com.jvm;

public class GcTest
{
	public Object instance = null;
	private static final int _1MB= 1024*1024;
	
	private byte[] bigSize = new byte[_1MB];
	
	public static void main(String[] args)
	{
		GcTest objA = new GcTest();
		GcTest objB = new GcTest();
		objA.instance=objB;
		objB.instance = objA;
		System.gc();
		
	}
}
