/*
 *	Object value=map.get(new Integer(2));
		//������Ҫע�⣬�����ڵ����ĸ�hashmap��get������ʱ�����Ǵ��ݵ�key��һ�����󣬶���������ӣ����Ǿ�����new
		//��һ������Ȼ��ͨ������¶�����з��ʵģ���Ȼ���¶��󣬵�������equalsֵ����ͬ�ĺ�ԭ���Ķ��������ǿ���
		//��Ϊ�������Keyֵ����ʹ�õ�
		
		System.out.println(value);
		//�����и���̬��Ӧ�ã����Ƿ��ص���һ��Object���󣬵���ʵ������һ��String�������������������ʱ��������
		//��String��tostring��������û��ʹ��object��tostring���� 

	���������Ƶ�ܽ�
	1. �����������ڿ��ײ�ʵ�ֵ�ʱ���Ѿ����֣�hashset����hashmapʵ�ֵģ���hashmap����ײ�����ݽṹ�������飬��������Ѱַ
	 ����ͨ��hash������ʵ�ֵģ����ҽ����ͻ�ķ�������������(���ﻹ����һ��С���ţ����õ���ͷ�巨Ӧ�õ��ǳ���ľֲ���ԭ��)
		 
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
		//������Ҫע�⣬�����ڵ����ĸ�hashmap��get������ʱ�����Ǵ��ݵ�key��һ�����󣬶���������ӣ����Ǿ�����new
		//��һ������Ȼ��ͨ������¶�����з��ʵģ���Ȼ���¶��󣬵�������equalsֵ����ͬ�ĺ�ԭ���Ķ��������ǿ���
		//��Ϊ�������Keyֵ����ʹ�õ�
		
		
		System.out.println(value);
		//�����и���̬��Ӧ�ã����Ƿ��ص���һ��Object���󣬵���ʵ������һ��String�������������������ʱ��������
		//��String��tostring��������û��ʹ��object��tostring����
		
	}
}
