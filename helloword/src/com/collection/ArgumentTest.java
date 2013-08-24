/*1.	Map.Entry map_entry=(Map.Entry)iter.next();
		//Object map_entry=iter.next();
		//���ﲻǿת���������Ͳ���ʹ�÷��������Ͷ�̬ì�ܣ���̬��ֻ��д�ĺ�����������Ҫ���õĺ�����object�ж�û��
		//��ȻҪ����ת������ʹ��
 * 2.���ֱ���map�ķ�ʽ������Ҫ����һ��setֻ�������е����Ͳ�һ��,һ����Map.Entry��һ������key�����ͣ�Ȼ����Ҫ����
 *   set��������map
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
		//һ�ֳ����ı�����ʽ
		Set set=map.keySet();
		for(Iterator iter=set.iterator();iter.hasNext(); )
		{
			String key=(String)iter.next();
			Integer value=(Integer)map.get(key);
			System.out.println(key + "= "+value);
		}
		
		
		System.out.println("-----------------------");
		//��һ�ֳ����ı���MAP�ķ�ʽ
		Set set2=map.entrySet();
		for(Iterator iter=set2.iterator();iter.hasNext();)
		{
			Map.Entry map_entry=(Map.Entry)iter.next();
			//Object map_entry=iter.next();
			//���ﲻǿת���������Ͳ���ʹ�÷��������Ͷ�̬ì�ܣ���̬��ֻ��д�ĺ�����������Ҫ���õĺ�����object�ж�û��
			//��ȻҪ����ת������ʹ��
			System.out.println(map_entry.getKey()+ "= " +map_entry.getValue());
		}
	}
}
