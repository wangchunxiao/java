package com.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class WordStasitical
{
	public static void main(String[] args)
	{
		Random random=new Random();
		Map map=new TreeMap();
		for(int i=0;i<50;i++)
		{
			int j=random.nextInt(41)+10;
			Integer in=new Integer(j);
			if(map.get(in)==null)
			{
				map.put(in,new Integer(1));
			}
			else
			{
				Integer temp=(Integer)map.get(in);
				map.put(in,new Integer(temp.intValue()+1));				
			}
		}
		Set set=map.entrySet();
		for(Iterator iter=set.iterator();iter.hasNext();)
		{
			Map.Entry map_entry=(Map.Entry)iter.next();
			//Map.Entry map_entry=iter.next();子类型可以直接赋给父类型，而父类型不能直接赋给子类型
			System.out.println(map_entry.getKey() + ": " + map_entry.getValue());
		}
	}
}
class MyCompartor3 implements Comparator
{
	@Override
	public int compare(Object o1, Object o2)
	{
		// TODO 自动生成的方法存根
		return 0;
		
	}
}
