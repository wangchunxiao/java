/*
 * 1.这个用来测试classloader这个类的方法，同时验证加载某个类并不等于主动使用某个类，也就是说这个类不会连接和初始化
 * 2.复习下，赋值给静态变量默认值发生在连接阶段
 * 
 * */

package com.classloader;


class C2
{
	static
	{
		System.out.println("初始化c2");
	}
}
public class Test7
{
	public static void main(String[] args) throws ClassNotFoundException
	{
		ClassLoader loader=ClassLoader.getSystemClassLoader();
		Class<?> classType=loader.loadClass("com.classloader.C2");
		
		System.out.println("---------------------------");
		classType=Class.forName("com.classloader.C2");
	}
}
