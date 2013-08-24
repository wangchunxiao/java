/*
 * 1.����һ�����ã���ûnew����û���������ã������ʼ��
 * 2.��һ�����������ǰ���£����һ�����Ѿ�����ʼ���ˣ��������ٴα���ʼ��������������������parent����ʼ����
 *   Ȼ��Ͳ����ٴα���ʼ����������child���������ˣ���ȻӦ�ó�ʼ��parent�����ǲ���ʼ����
 * */

package com.classloader;

class Parent2
{
	static int a = 3;
	
	static
	{
		System.out.println("Parent2 static block");
	}
}

class Child2 extends Parent2
{
	static int b = 4;
	
	static
	{
		System.out.println("Child2 static block");
	}
}


public class TestParent
{
	static
	{
		System.out.println("TestParent static block");
	}
	
	public static void main(String[] args)
	{
		Parent2 parent;
		
		System.out.println("-------------------------");
		
		parent = new Parent2();
		
		System.out.println(Parent2.a);
		
		System.out.println(Child2.b);
	}
}




















