package com.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SomeTest
{
	public static void main(String[] args)
	{
		GenericT<? extends List> t_list=new GenericT<ArrayList>();
		t_list=new GenericT<LinkedList>();
		
		GenericT<String> t_obj1=new GenericT<String>();
		GenericT<? extends Object> t_obj2;
		t_obj2=t_obj1;//���ﲻͬ���Ϳ��Ը�ֵ��ԭ��
		//t_obj2.setFoo("liran");//���ﱨ���ԭ��
		
		
		
		GenericT2<LinkedList> t2_list=new GenericT2<LinkedList>();
		//GenericT2<HashMap> t2_hashmap=new GenericT2<HashMap>();//ע�����ﱨ���ԭ��
	}
}

//������������ǲ�һ���ģ�ʹ�õ�ʱ��Ҫע��
class GenericT<T>
{
	private T foo;

	public T getFoo()
	{
		return foo;
	}

	public void setFoo(T foo)
	{
		this.foo = foo;
	}
}
//���Ƕ�T���� ��Լ����Լ��ֻ��ʹ��һЩ�̶�������(��ȻList�ǽӿڣ�������������Ҫ��extends)
class GenericT2<T extends List>
{
	private T foo;

	public T getFoo()
	{
		return foo;
	}

	public void setFoo(T foo)
	{
		this.foo = foo;
	}
	
}