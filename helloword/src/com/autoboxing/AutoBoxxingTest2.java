/*
 * 1.������������Զ������װ�䣬װ�� �� intֱ�ӷŵ����������Ӽ�����ȡ����Integerֱ�ӽ����� +1����
 * 2.��Ҫ��ͼ��null�Ž�������
 * */
package com.autoboxing;

import java.util.HashMap;

public class AutoBoxxingTest2
{
	public static void main(String[] args)
	{
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		for(String word :args)
		{
			Integer in=map.get(word);
			map.put(word,((in==null)? 1 : in+1));//�����ҷ���һ�����󣬰�null��ӽ������У���Ȼ�ǲ��е�
		}
		System.out.println(map);
	}
}
