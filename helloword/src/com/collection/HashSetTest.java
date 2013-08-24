/*
 * 一些关于hashcode和equals的说明
 * 1.我们重写equals方法的时候，要重写hashcode方法，已满足相等的对象有相同的hashcode。即hashcode，equals这两个方法一般都会同时重写
 * 2.equals如果返回true,则hashcode一定应该是相同,但是如果equals返回假，则hashcode可以相同也可以不同，
 * 3.集合在判定是否已经添加的时候，先判断这个对象的hashcode是否和集合中的一样，如果不一样则加入，如果一样，则继续判断equals是否相等，如果不等则加入，如果相等则不加入
 * 4.这里使用迭代器和C++不太一样，C++是迭代器直接指向容器中的元素，这里的迭代器好似指向元素前面的这个位置(such as:如果是第一个元素，就指向第一个元素前面),所以这里迭代和C++不一样
 * 5.接4  如果迭代器调用了iter.next() 这个时候迭代器就已经自动向后移动了，不像C++我们要for(iterator pa=XX.begin();pa!=xx.end();pa++)pa还要++
 *   java的for循环  for(Iterator iter=xx.iterator();iter.hasnext(); )
 *    */
package com.collection;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest
{
	public static void main(String[] args)
	{
		HashSet set=new HashSet();
		set.add(new People("liran"));
		set.add(new People("liran"));
		//System.out.println(set.size());
		set.add(new People("zhangsan"));
		set.add(new People("lisi"));
		set.add(new People("wangwu"));
		set.add(new Integer(2));
		Iterator iter=set.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		
	}
}

class People
{
	
	String name;
	@Override
	public String toString()
	{
		// TODO 自动生成的方法存根
		return name;
		//return super.toString();
	}
	People(String temp)
	{
		name=temp;
	}
	@Override
	public int hashCode()
	{
		// TODO 自动生成的方法存根
		return name.hashCode();
		//return super.hashCode();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true;
		else if(null!=obj && obj instanceof People)
		{
			People p=(People)obj;
			return name.equals(p.name);
		}
		else
			return false;
	}
}