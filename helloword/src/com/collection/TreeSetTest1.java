package com.collection;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest1
{
	//static ArrayList list=new ArrayList();
	//list.
	public static void main(String[] args)
	{
		TreeSet set=new TreeSet(new MyComparator());
		set.add("b");
		set.add("a");
		set.add("c");
		System.out.println(set);	

	}
}
class MyComparator implements Comparator
{
	@Override
	public int compare(Object o1, Object o2)
	{
		// TODO 自动生成的方法存根
		String s1=(String)o1;
		String s2=(String)o2;

		if(s1.compareTo(s2)==-1)
		{
			return 1;
		}
		else if(s1.compareTo(s2)==1)
		{
			return -1;
		}
		else
			return 0;
		//return s2.compareto(s1);
	}
}
