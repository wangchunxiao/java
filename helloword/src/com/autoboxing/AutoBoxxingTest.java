/*
 * 1.�Զ�װ��Ͳ���ֻ���8��ԭ���������ͣ�������Ҫʹ�ö������͵�����£����ǿ���ֱ�Ӱ����Ǹ���ȥ��ע����ʱ��
 *   ������˵����ԭ�����ͣ������õĻ��Ƕ���ֻ�Ǳ�����������������Щ����
 * 2.������ֻ���Ƕ��󣬶��ҷ���Ҳֻ���Ƕ���
 * 3.������ӻ�������ǿ��forѭ��
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
