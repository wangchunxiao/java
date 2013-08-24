package com.sort;

public class QuickSort
{
	public static void main(String[] args)
	{
		int a=4;
		int b=5;
		Integer c=new Integer(6);
		Integer d=new Integer(7);
		
		QSort.swap(new int[]{a,b});
		QSort.swap(new int[]{c,d});
		
		System.out.println(a+"  "+ b);
		System.out.println(c+"  "+ d);
	}
}

class QSort
{
	private int[] array;
	public QSort()
	{
		
	}
	public static int findQvote(int low,int high,int[] array)
	{
		int qvote=0;
		int temp=array[low];
		while(low<high)
		{
			while(array[high]>temp)
				high--;
			
		}
		
		return qvote;
	}
	public static void swap(int[] array)
	{
//		int temp;
//		temp=a;
//		a=b;
//		b=temp;
//		
//		Integer temp;
//		temp=a;
//		a=b;
//		b=a;
//		
//		int temp=array[0];
//		array[0]=array[1];
//		array[1]=temp;
	}
}
