/*
 * 1.声明一个引用，而没new等于没有主动调用，不会初始化
 * 2.在一个类加载器的前提下，如果一个类已经被初始化了，将不会再次被初始化，所以我们下面的这个parent被初始化了
 *   然后就不能再次被初始化，现在是child主动调用了，虽然应该初始化parent，但是不初始化了
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




















