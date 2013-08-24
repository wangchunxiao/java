package com.collection;

import java.util.ArrayList;

public class ArrayListTest2
{
	public static void main(String[] args)
	{
		ArrayList list=new ArrayList();
		list.add("lrian");
		list.add(new Integer(2));
		list.add("hello");
		String str;
		str=(String)list.get(0);
		System.out.println(str);
		Integer object_i=(Integer)list.get(1);
		System.out.println(object_i.intValue());
		Object[] array=list.toArray();
		for(int i=0;i<array.length;i++)
		{
			System.out.println(array[i]);
		}

		
		System.out.println("---------------------------------");
		System.out.println(list);
	}
}
