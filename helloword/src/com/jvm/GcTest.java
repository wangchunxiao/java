/*
 * ��������system.gc��������Լ�java�Ƿ������ü����ķ����������ڴ�
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