/*1.	Map.Entry map_entry=(Map.Entry)iter.next();
		//Object map_entry=iter.next();
		//这里不强转成子类类型不能使用方法并不和多态矛盾，多态是只重写的函数，而我们要调用的函数在object中都没有
		//当然要向下转化才能使用
 * 2.两种遍历map的方式，都是要返回一个set只不过当中的类型不一样,一个是Map.Entry另一个就是key的类型，然后都需要遍历
 *   set进而遍历map
 * */
package com.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ArgumentTest
{
	public static void main(String[] args)
	{
		HashMap map=new HashMap();
		for(int i=0;i<args.length;i++)
		{
			if(map.get(args[i])==null)
			{
				map.put(args[i],new Integer(1));
			}
			else
			{
				Integer in=(Integer)map.get(args[i]);
				in=new Integer((in.intValue())+1);
				map.put(args[i],in);
			}
			System.out.println(args[i]);
		}
		
		
		System.out.println("-----------------------");
		//一种常见的遍历方式
		Set set=map.keySet();
		for(Iterator iter=set.iterator();iter.hasNext(); )
		{
			String key=(String)iter.next();
			Integer value=(Integer)map.get(key);
			System.out.println(key + "= "+value);
		}
		
		
		System.out.println("-----------------------");
		//另一种常见的遍历MAP的方式
		Set set2=map.entrySet();
		for(Iterator iter=set2.iterator();iter.hasNext();)
		{
			Map.Entry map_entry=(Map.Entry)iter.next();
			//Object map_entry=iter.next();
			//这里不强转成子类类型不能使用方法并不和多态矛盾，多态是只重写的函数，而我们要调用的函数在object中都没有
			//当然要向下转化才能使用
			System.out.println(map_entry.getKey()+ "= " +map_entry.getValue());
		}
	}
}
