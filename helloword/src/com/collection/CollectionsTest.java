/*1.collections是定义了一组静态方法已实现对collection的操作 ;Arrays定义了一组操作array的方法  这个是类似的
 *2.collections的reverseorder方法有两个重载，没有参数的是对可以自然排序的它会返回逆序，另一个重载就是接受一个comparator对象的参数，对自己定义的类要自己定义比较器
 *
 * */


package com.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class CollectionsTest
{
	public static void main(String[] args)
	{
		LinkedList list=new LinkedList();
		list.add(new Dog(1));
		list.add(new Dog(2));
		
		Comparator r=Collections.reverseOrder(new MyComparator2());
		Collections.sort(list,r);
		System.out.println(list);
	}
}

class Dog
{
	int age;
	Dog(int temp)
	{
		this.age=temp;
	}
	@Override
	public String toString()
	{
		// TODO 自动生成的方法存根
		//return super.toString();
		Integer int_object=new Integer(age);
		return int_object.toString();
	}
}
class MyComparator2 implements Comparator
{
	@Override
	public int compare(Object o1, Object o2)
	{
		// TODO 自动生成的方法存根
		Dog d1=(Dog)o1;
		Dog d2=(Dog)o2;
		return (d1.age-d2.age);
	}
}