package com.generic;

public class GenericInGenericTest<T>
{
	GenericFoo<Integer> g1;
	GenericFoo<T> g2;
	public GenericFoo<Integer> getG1()
	{
		return g1;
	}
	public void setG1(GenericFoo<Integer> g1)
	{
		this.g1 = g1;
	}
	public GenericFoo<T> getG2()
	{
		return g2;
	}
	public void setG2(GenericFoo<T> g2)
	{
		this.g2 = g2;
	}
	@Override
	public String toString()
	{
		return g1 + " "+ g2;
	}
	
	public static void main(String[] args)
	{
		GenericFoo<Integer> foo1=new GenericFoo<Integer>();
		GenericFoo<String> foo2=new GenericFoo<String>();
		foo1.setFoo(3);
		foo2.setFoo("liran");
		
		GenericInGenericTest<String> generictest=new GenericInGenericTest<String>();
		generictest.setG1(foo1);
		generictest.setG2(foo2);
		
		System.out.println(generictest);
	}
}

class GenericFoo<T>
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
	public String toString()
	{
		return foo.toString();
	}
}
