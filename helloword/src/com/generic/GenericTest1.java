/*
 * 泛型就是类型参数化
 * */
package com.generic;

public class GenericTest1
{
	public static void main(String[] args)
	{
		ForGeneric<Boolean> g1=new ForGeneric<Boolean>();
		g1.setFoo(new Boolean(false));
		
		ForGeneric<Integer> g2=new ForGeneric<Integer>();
		g2.setFoo(new Integer(2));
		
		System.out.println(g1);
		System.out.println(g2);
		
	}
}

class ForGeneric<T>
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
	@Override
	public String toString()
	{
		return foo.toString();
	}
	
}