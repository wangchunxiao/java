/*
 * 1.这个例子中虽然是主动使用了child.a  但是由于a是父类的静态变量，只有程序访问在确实在当前接口或类中定义的静态变量的时候
 *   才算初始化这个类，这里显然a是父类的不是child类的，所以不能算是对child类的初始化
 * 2.上述的对静态方法同样适用，我们做了测试
 * 3.接口和类不一样，主动使用实现了某个接口的类的时候，不能初始化这个接口
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






















