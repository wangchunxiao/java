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
		t_obj2=t_obj1;//这里不同类型可以赋值的原因
		//t_obj2.setFoo("liran");//这里报错的原因
		
		
		
		GenericT2<LinkedList> t2_list=new GenericT2<LinkedList>();
		//GenericT2<HashMap> t2_hashmap=new GenericT2<HashMap>();//注意这里报错的原因
	}
}

//下面的这两个是不一样的，使用的时候要注意
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
//这是对T进行 了约定，约定只能使用一些固定的类型(虽然List是接口，但是这里我们要用extends)
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