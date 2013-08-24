package com.generic;

public class SimpleCollection<T>
{
		T[] objArray;
		int index=0;
		public SimpleCollection()
		{
			objArray=(T[])new Object[10];
		}
		public SimpleCollection(int capasity)
		{
			objArray=(T[])new Object[capasity];
		}
		
		public void add(T t)
		{
			objArray[index++]=t;
		}
		
		public T get(int index)
		{
			return objArray[index];
		}
		
		
		public static void main(String[] args)
		{
			SimpleCollection<Integer> c1=new SimpleCollection<Integer>();
			for(int i=0;i<10;i++)
			{
				c1.add(new Integer(i));
			}
			for(int i=0;i<10;i++)
			{
				System.out.println(c1.get(i));
			}
		}
		
}
