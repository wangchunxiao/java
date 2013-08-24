/*
 * 1.下面这个例子自动拆箱和装箱，装箱 把 int直接放到集合里，拆箱从集合中取出的Integer直接进行了 +1操作
 * 2.不要试图把null放进集合中
 * */
package com.autoboxing;

import java.util.HashMap;

public class AutoBoxxingTest2
{
	public static void main(String[] args)
	{
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		for(String word :args)
		{
			Integer in=map.get(word);
			map.put(word,((in==null)? 1 : in+1));//这里我犯了一个错误，把null添加进集合中，显然是不行的
		}
		System.out.println(map);
	}
}
