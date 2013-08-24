/*
 * 1.�����������Ȼ������ʹ����child.a  ��������a�Ǹ���ľ�̬������ֻ�г��������ȷʵ�ڵ�ǰ�ӿڻ����ж���ľ�̬������ʱ��
 *   �����ʼ������࣬������Ȼa�Ǹ���Ĳ���child��ģ����Բ������Ƕ�child��ĳ�ʼ��
 * 2.�����ĶԾ�̬����ͬ�����ã��������˲���
 * 3.�ӿں��಻һ��������ʹ��ʵ����ĳ���ӿڵ����ʱ�򣬲��ܳ�ʼ������ӿ�
 * */

package com.classloader;

class Parent3
{
	static int a = 3;
	
	static
	{
		System.out.println("Parent3 static block");
	}
	
	static void doSomething()
	{
		System.out.println("do something");
	}
	
	public static void testNotDefineMethodInTheClass()
	{
		
	}
}

class Child3 extends Parent3
{
	static
	{
		System.out.println("Child3 static block");
	}
}

public class Test6
{
	public static void main(String[] args)
	{
		System.out.println(Child3.a);
		//Child3.testNotDefineMethodInTheClass();
		Child3.doSomething();
	}
}






















