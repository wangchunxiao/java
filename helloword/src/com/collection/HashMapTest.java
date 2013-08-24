/*
 *	Object value=map.get(new Integer(2));
		//这里需要注意，我们在调用哪个hashmap的get方法的时候，我们传递的key是一个对象，对于这个例子，我们就是新new
		//了一个对象，然后通过这个新对象进行访问的，虽然是新对象，但是由于equals值是相同的和原来的对象，所以是可以
		//作为这个进行Key值进行使用的
		
		System.out.println(value);
		//这里有个多态的应用，我们返回的是一个Object对象，但它实际上是一个String对象，所有我们在输出的时候它调用
		//了String的tostring方法，并没有使用object的tostring方法 

	看后面的视频总结
	1. 另这里我们在看底层实现的时候已经发现，hashset是用hashmap实现的，而hashmap的最底层的数据结构还是数组，这个数组的寻址
	 就是通过hash函数来实现的，并且解决冲突的方法就是链表解决(这里还用了一个小窍门，采用的是头插法应用的是程序的局部性原理)
		 
 * */
package com.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapTest
{
	public static void main(String[] args)
	{
		HashMap map=new HashMap();
		map.put(new Integer(0),"liran");
		map.put(new Integer(1),"liran");
		map.put(new Integer(2),"zhangsan");
		
		Set set=map.keySet();
		for(Iterator itor=set.iterator();itor.hasNext();)
		{
			System.out.println(itor.next());
		}
		System.out.println("-----------------------------");
		Collection collection=map.values();
		for(Iterator itor=collection.iterator();itor.hasNext(); )
		{
			System.out.println(itor.next());
		}
		
		System.out.println("-----------------------------");
		Object value=map.get(new Integer(2));
		//这里需要注意，我们在调用哪个hashmap的get方法的时候，我们传递的key是一个对象，对于这个例子，我们就是新new
		//了一个对象，然后通过这个新对象进行访问的，虽然是新对象，但是由于equals值是相同的和原来的对象，所以是可以
		//作为这个进行Key值进行使用的
		
		
		System.out.println(value);
		//这里有个多态的应用，我们返回的是一个Object对象，但它实际上是一个String对象，所有我们在输出的时候它调用
		//了String的tostring方法，并没有使用object的tostring方法
		
	}
}
