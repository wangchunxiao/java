package com.sampleFactor;

public class Client
{
	public static void main(String[] args)
	{
		Product p1=Factor.create("A");
		Product p2=Factor.create("B");
		System.out.println(p1.getClass().getName());
		System.out.println(p2.getClass().getName());
	}
}
