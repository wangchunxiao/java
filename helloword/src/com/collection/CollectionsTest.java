/*1.collections�Ƕ�����һ�龲̬������ʵ�ֶ�collection�Ĳ��� ;Arrays������һ�����array�ķ���  ��������Ƶ�
 *2.collections��reverseorder�������������أ�û�в������ǶԿ�����Ȼ��������᷵��������һ�����ؾ��ǽ���һ��comparator����Ĳ��������Լ��������Ҫ�Լ�����Ƚ���
 *
 * */


package com.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class CollectionsTest
{
	public static void main(String[] args)
	{
		LinkedList list=new LinkedList();
		list.add(new Dog(1));
		list.add(new Dog(2));
		
		Comparator r=Collections.reverseOrder(new MyComparator2());
		Collections.sort(list,r);
		System.out.println(list);
	}
}

class Dog
{
	int age;
	Dog(int temp)
	{
		this.age=temp;
	}
	@Override
	public String toString()
	{
		// TODO �Զ����ɵķ������
		//return super.toString();
		Integer int_object=new Integer(age);
		return int_object.toString();
	}
}
class MyComparator2 implements Comparator
{
	@Override
	public int compare(Object o1, Object o2)
	{
		// TODO �Զ����ɵķ������
		Dog d1=(Dog)o1;
		Dog d2=(Dog)o2;
		return (d1.age-d2.age);
	}
}