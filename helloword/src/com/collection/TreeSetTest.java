/*1.����java��c++�Ĺ��ڲ��뵽����������������ͼ����е�����
 *a.����C++��������Map��set�е�ʱ���Զ������ͱ���Ҫ����<���ţ����ұ�ѡ���ϸ������򣬼�������ȣ����ؼ�
 *b������JAVA��TreeSet����˳��ģ���hashsetû��˳���Զ������Ͳ��뵽TreeSet������ʹ�õĹ��췽���Ǵ���һ��ʵ��Comparator���������Compartor��Ҫ��implements Comparator,��
 *  ����compare������ע�������и�������ǣ�Ҫ�ֱ�Ƚ�<,>,=����Ϊ���Ƿ���ֵ�ǲ�һ����
 *c.���Կ���C++��0�����棬��JAVA�����������ղŵ�compare���������Ƿ���a>b ����1  a=b����0  a<b����-1 
 * 
 * */
package com.collection;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetTest
{
	public static void main(String[] args)
	{
		TreeSet set=new TreeSet(new MyComparator1());
		//set.add(new Integer(2));
		set.add(new Person("liran",100));
		set.add(new Person("zhangssan",80));
		
		for(Iterator iter=set.iterator();iter.hasNext(); )
		{
			System.out.println(iter.next());
		}
	}
}

class Person
{
	int socre;
	String name;
	Person(String temp,int i)
	{
		name=temp;
		socre=i;
	}
	@Override
	public String toString()
	{
		// TODO �Զ����ɵķ������
		//return super.toString();
		return (name+socre);
	}
}
class MyComparator1 implements Comparator
{
	@Override
	public int compare(Object o1, Object o2)
	{
		// TODO �Զ����ɵķ������
		Person p1=(Person)o1;
		Person p2=(Person)o2;
		if(p1.socre<p2.socre)
		{
			return -1;
		}
		else if(p1.socre>p2.socre)
		{
			return 1;
		}
		else
			return 0;
	//return (p1.socre-p2.socre);
	}
}