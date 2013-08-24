/*
 * 1.自动装箱和拆箱只针对8个原生数据类型，即在需要使用对象类型的情况下，我们可以直接把它们赋过去，注意这时候
 *   并不是说用了原生类型，而是用的还是对象，只是编译器帮我们做了这些事情
 * 2.集合中只能是对象，而且泛型也只能是对象
 * 3.这个例子还用了增强的for循环
 * */
package com.autoboxing;

import java.util.ArrayList;
import java.util.Collection;

public class AutoBoxxingTest
{
	public static void main(String[] args)
	{
		Collection<Integer> c=new ArrayList<Integer>();
		int a=3;
		c.add(a);
		c.add(a+3);
		c.add(10);
		
		for(Integer i : c)
		{
			System.out.println(i);
		}
	}
}
