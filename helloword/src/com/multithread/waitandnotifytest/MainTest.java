package com.multithread.waitandnotifytest;

public class MainTest
{
	public static void main(String[] args)
	{
		Sample sample=new Sample();
		
		Thread t1=new ThreadForIncrease(sample);
		Thread t2=new ThreadForDecrease(sample);
		
		Thread t3=new ThreadForIncrease(sample);
		Thread t4=new ThreadForDecrease(sample);
		
		t1.start();
		t2.start();
		
		t3.start();
		t4.start();
	}
}
