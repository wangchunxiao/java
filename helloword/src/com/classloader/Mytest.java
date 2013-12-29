/*
 * 这个例子说明了类的加载
 * 1.首先jvm有一种预处理机制，就是不一定是类主动调用的时候才加载这个类到内存，而且这个时候就是加载了如果出现错误也不报错
 *   而是等到具体的主动调用的时候再报错
 * 2.类的主动调用一共有六种方式，这六种方式会使类进行初始化，则一定会使类加载到内存
 * 3.类的处理顺序是，1)加载到内存.2)验证，解析，初始化
 * 4.六种主动调用的方式有，实例化对象，调用类的静态方法，调用类的静态成员函数(例如赋值)，主动调用子类时也要先初始化父类，
 *   反射，和启动类(有main方法的类)。
 * 
 * 5.前一天晚上看没理解，11.1号看深入理解jvm之后理解了这个实例。首先加载是类加载的一个步骤，加载负责三件事，读入类的二进制字节流，将静态存储结构转化
 * 为方法区运行时数据结构，在内存中生成类的class对象。验证不多说，准备阶段比较重要。准备阶段分配类变量(static)的内存，内存是在方法去内进行分配，
 * 其次对所有类变量(static)赋0值(不同类型的0值不同,如bool就是false，reference就是null)。初始化阶段才真正执行java代码，如我的staitc变量初始化，
 * static代码块等操作，按照出现顺序执行。
 * 6.这个例子中就是准备阶段分配内存然后将三个static变量赋0值，然后初始化阶段执行java代码，由于count2的代码靠后，则又被重新初始化为0
 * */

package com.classloader;

public class Mytest
{
	public static void main(String[] args)
	{
		SingleTon singleton=SingleTon.getInstance();
		System.out.println(singleton.count1);
		System.out.println(singleton.count2);
	}
}
class SingleTon
{
	private static SingleTon singleton=new SingleTon();
	public static int count1;
	public static int count2=0;
	
	public SingleTon()
	{
		count1++;
		count2++;
	}
	
	public static SingleTon getInstance()
	{
		return singleton;
	}
}