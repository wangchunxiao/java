/*
 * 1.首先反射一定是先获得特定类Class对象，然后通过刚才那个对象获得Method对象，然后再调用这个Method对象的方法获得结果，
 *   这里需要注意，首先前一步的参数一定是参数的Class对象，是可变参数类型 ，后者是具体的对象参数，也是可变参数的类型
 * 2.int型和Integer类型的Class方法还不能通用，是哪种类型作为参数，就在反射调用方法的时候用哪个
 * */
package com.reflect;

import java.lang.reflect.Method;

public class MethodTest
{
	//public int add(int a,int b)
	public int add(Integer a,Integer b)
	{
		return a+b;
	}
	public String echo(String message)
	{
		return ("hello " + message);
	}
	
	public static void main(String[] args) throws Exception
	{
		Class<?> classType=MethodTest.class;
		Object methodtest_object=classType.newInstance();
		
		//Method addmethod=classType.getDeclaredMethod("add",new Class[]{int.class,int.class});
		//Integer.class就出现执行错误
		Method addmethod=classType.getDeclaredMethod("add",new Class[]{Integer.class,Integer.class});
		//Object addresult=addmethod.invoke(methodtest_object,3,5);也可以下面这么写，这里写法就自动装箱了
		Object addresult=addmethod.invoke(methodtest_object,new Object[]{2,4});//这里就是参数是Object类型，所以也声明Object类型，没有用装箱
		System.out.println(addresult);
	}
}
