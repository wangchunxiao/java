package com.collection;

import java.util.LinkedList;

public class MyStackAndQueue
{
	public static void main(String[] args)
	{
		MyStack stack=new MyStack();
		MyQueue queue=new MyQueue();
		for(int i=0;i<3;i++)
		{
			stack.push(new Integer(i));
			queue.put(new Integer(i));
		}
		for(int i=0;i<3;i++)
		{
			System.out.println(stack.pop());		
		}
		for(int i=0;i<3;i++)
		{
			System.out.println(queue.get());
		}
	}
}

class MyStack
{
	LinkedList list=new LinkedList();
	
	public void push(Object e)
	{
		list.addLast(e);
	}
	public Object pop()
	{
		return list.remove(list.size()-1);
	}
	public Object peek()
	{
		return list.peekLast();
	}
}

class MyQueue
{
	LinkedList list=new LinkedList();
	
	public void put(Object e)
	{
		list.addLast(e);
	}
	public Object get()
	{
		return list.remove();
	}
	public Object peek()
	{
		return list.peekFirst();
	}
}