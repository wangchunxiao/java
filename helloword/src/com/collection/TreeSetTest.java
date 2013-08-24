/*1.对于java和c++的关于插入到必须有排序的容器和集合中的问题
 *a.对于C++，当插入Map和set中的时候，自定义类型必须要重载<符号，而且必选是严格若排序，即两个相等，返回假
 *b。对于JAVA，TreeSet是有顺序的，而hashset没有顺序，自定义类型插入到TreeSet，必须使用的构造方法是传递一个实现Comparator对象，且这个Compartor类要是implements Comparator,且
 *  重载compare函数，注意这里有个问题就是，要分别比较<,>,=，因为它们返回值是不一样的
 *c.可以看出C++非0就是真，而JAVA不是这样，刚才的compare函数必须是返回a>b 返回1  a=b返回0  a<b返回-1 
 * 
 * */
package com.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTest
{
	public static void main(String[] args)
	{
		TreeSet set=new TreeSet(new MyComparator1());
		//set.add(new Integer(2));
		set.add(new Person("liran",100));
		set.add(new Person("zhangssan",80));
		
		for(Iterator iter=set.iterator();iter.hasNext(); )
		{
			System.out.println(iter.next());
		}
	}
}

class Person
{
	int socre;
	String name;
	Person(String temp,int i)
	{
		name=temp;
		socre=i;
	}
	@Override
	public String toString()
	{
		// TODO 自动生成的方法存根
		//return super.toString();
		return (name+socre);
	}
}
class MyComparator1 implements Comparator
{
	@Override
	public int compare(Object o1, Object o2)
	{
		// TODO 自动生成的方法存根
		Person p1=(Person)o1;
		Person p2=(Person)o2;
		if(p1.socre<p2.socre)
		{
			return -1;
		}
		else if(p1.socre>p2.socre)
		{
			return 1;
		}
		else
			return 0;
	//return (p1.socre-p2.socre);
	}
}