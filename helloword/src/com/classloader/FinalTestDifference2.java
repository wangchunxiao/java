/*
 * �����������ǲ�ͬ�ģ���Ҫ����Ϊ�����᲻�ᱻ��ʼ�������⣬Ҳ����˵��ֻ�г�ʼ������������ִ�о�̬�����
 * 1.ֻ�г�ʼ������ִ�о�̬�����
 * 2.����̬�����ǳ�����ʱ������ʹ����ľ�̬�����ǲ����ʼ����
 * 3.����̬������Ҫ���в�֪������Ļ������Ǿ�Ҫ��ʼ�������
 */
package com.classloader;

import java.util.Random;

public class FinalTestDifference2
{
	public static void main(String[] args)
	{
		System.out.println(FinalTest2.x);
	}
}

class FinalTest2
{
	public static final int x=new Random().nextInt(100);
	static
	{
		System.out.println("FinalTest static block");
	} 
}