/*
 * һЩ����hashcode��equals��˵��
 * 1.������дequals������ʱ��Ҫ��дhashcode��������������ȵĶ�������ͬ��hashcode����hashcode��equals����������һ�㶼��ͬʱ��д
 * 2.equals�������true,��hashcodeһ��Ӧ������ͬ,�������equals���ؼ٣���hashcode������ͬҲ���Բ�ͬ��
 * 3.�������ж��Ƿ��Ѿ���ӵ�ʱ�����ж���������hashcode�Ƿ�ͼ����е�һ���������һ������룬���һ����������ж�equals�Ƿ���ȣ������������룬�������򲻼���
 * 4.����ʹ�õ�������C++��̫һ����C++�ǵ�����ֱ��ָ�������е�Ԫ�أ�����ĵ���������ָ��Ԫ��ǰ������λ��(such as:����ǵ�һ��Ԫ�أ���ָ���һ��Ԫ��ǰ��),�������������C++��һ��
 * 5.��4  ���������������iter.next() ���ʱ����������Ѿ��Զ�����ƶ��ˣ�����C++����Ҫfor(iterator pa=XX.begin();pa!=xx.end();pa++)pa��Ҫ++
 *   java��forѭ��  for(Iterator iter=xx.iterator();iter.hasnext(); )
 *    */
package com.collection;

import java.util.HashSet;
import java.util.Iterator;

public class HashSetTest
{
	public static void main(String[] args)
	{
		HashSet set=new HashSet();
		set.add(new People("liran"));
		set.add(new People("liran"));
		//System.out.println(set.size());
		set.add(new People("zhangsan"));
		set.add(new People("lisi"));
		set.add(new People("wangwu"));
		set.add(new Integer(2));
		Iterator iter=set.iterator();
		while(iter.hasNext())
		{
			System.out.println(iter.next());
		}
		
	}
}

class People
{
	
	String name;
	@Override
	public String toString()
	{
		// TODO �Զ����ɵķ������
		return name;
		//return super.toString();
	}
	People(String temp)
	{
		name=temp;
	}
	@Override
	public int hashCode()
	{
		// TODO �Զ����ɵķ������
		return name.hashCode();
		//return super.hashCode();
	}
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true;
		else if(null!=obj && obj instanceof People)
		{
			People p=(People)obj;
			return name.equals(p.name);
		}
		else
			return false;
	}
}