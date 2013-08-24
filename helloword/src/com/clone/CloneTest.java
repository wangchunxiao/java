/*
 * 1.深复制和浅复制.浅复制对于对象的引用，只是复制了这个引用(只复制了指针，并没有重新申请一块内存存放这个对象)，而深复制
 *   是将为要复制的对象申请一块新的内存区域，然后将对象的一些值复制过去，而这个时候的对象的引用就不是以前要复制的那个对象
 *   的引用了
 * 2.protected修饰的方法一般都是希望子类重写的，这点又看了下书，访问权限的级别从小到大,private-default-protected-public
 *   所谓protected就是比default多了子类可以看到这个成员，但子类如果不重写这个方法(改为Public),则其他的包中是看不到这个方法的
 * 3.java都是面向对象，所以访问权限和C++的描述不一样，C++要面向过程和对象，所以会考虑继承的权限，而java要是访问这个类中成员
 *   也是在另一个类中实现的，所以就不须说继承的级别，直接就是在另一个类中看的到和看不到就行了
 * 4.对于clone()方法，要想复制的类一定要实现cloneable这个接口，这个接口是空的和Serializable是一样的,只是告诉编译器要
 *   这么干，这么编译。一定要重写父类的clone方法，且第一个语句一定是调用super.clone()，这是jdk文档说的，调用的效果就是
 *   调用了object类的clone方法，分配内存等等工作
 * 5.object类的clone对象是浅复制
 * */
package com.clone;

import java.io.Serializable;

public class CloneTest implements Serializable
{
	public static void main(String[] args) throws CloneNotSupportedException
	{
		Student s1=new Student(20,"liran");
		Student s2=(Student)(s1.clone());
		
		System.out.println(s1.name+" "+s1.age);
		System.out.println(s1==s2);
	}
}

class Student implements Cloneable,Serializable
{
	public int age;
	public String name;
	public Student(){};
	public Student(int age,String name){
		this.age=age;
		this.name=name;
	}
	
	@Override
	public  Object clone() throws CloneNotSupportedException
	{
		// TODO 自动生成的方法存根
		return super.clone();
	}
}
