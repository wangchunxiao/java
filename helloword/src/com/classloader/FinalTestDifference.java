/*
 * �����������ǲ�ͬ�ģ���Ҫ����Ϊ�����᲻�ᱻ��ʼ�������⣬Ҳ����˵��ֻ�г�ʼ������������ִ�о�̬�����
 * 1.ֻ�г�ʼ������ִ�о�̬�����
 * 2.����̬������final���ǳ�����ʱ������ʹ����ľ�̬�����ǲ����ʼ����
 * 3.����̬������Ҫ���в�֪������Ļ������Ǿ�Ҫ��ʼ�������
 */

package com.classloader;

public class FinalTestDifference
{
	public static void main(String[] args)
	{
		System.out.println(FinalTest.x);
	}
}

class FinalTest
{
	public static final int x=6/3;
	static
	{
		System.out.println("FinalTest static block");
	} 
}


