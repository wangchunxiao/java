package com.collection;

import java.util.LinkedList;

public class LinkedListTest
{
	public static void main(String[] args)
	{
		LinkedList list=new LinkedList();
		list.add("liran");
		list.add("suixin");
		list.add(1,"love");
		list.addFirst(new Integer(1));
		list.addLast(".");
		
		System.out.println(list);
	}
}
