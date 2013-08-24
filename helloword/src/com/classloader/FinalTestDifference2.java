/*
 * 这两个例子是不同的，主要是因为这个类会不会被初始化的问题，也就是说，只有初始化了这个类才能执行静态代码块
 * 1.只有初始化才能执行静态代码块
 * 2.当静态变量是常量的时候，我们使用类的静态变量是不会初始化的
 * 3.当静态变量需要运行才知道结果的话，我们就要初始化这个类
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