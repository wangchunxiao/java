/*
 * 1这里注意必须是Public的构造方法才可以使用Constructor这种反射的机制  看下面的注释 之前这里一直报错
 * 2.Long的自动装箱与拆箱我还没搞明白，之前id是Long类型 然后我直接用数字2进行装箱报错，用New Long(2)就不会报错
 *   解决了上面这个 数字直接被包装成Integer 如1 而我们用1L就可以包装成Long了
 * 3.这里我们可以通过使用Field对象field的getType来获取这个东西的Class属性    详见下面的注释
 * 4.注意这个getFields和getDeclaredFields不要用错，获取属性用的是后面的方法
 * */
package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ConstractorTest
{
	public Object copy(Object obj) throws Exception
	{
		Class<?> classType=obj.getClass();
		
		System.out.println(classType);
		
		//以下是通过反射创建对象的三种方法，用有参数的构造函数生成对象只能用第三种方法
		Object obj3=classType.newInstance();
		
		
		Constructor con=classType.getConstructor(new Class[]{});
		Object obj1=con.newInstance(new Object[]{});
		
		Constructor con1=classType.getConstructor(new Class[]{Integer.class,String.class,int.class});
		Object obj2=con1.newInstance(new Object[]{2,"liran",25});
		
		System.out.println(obj1+" "+ obj2+" " + obj3);
		
		System.out.println("----------------------------------------");
		
		Field[] field_array1=classType.getDeclaredFields();//注意这个getFields和getDeclaredFields不要用错
		for(Field field:field_array1)
		{
			System.out.println(field);
		}
		System.out.println("----------------------------------------");
		
		//下面我们实现复制Object的方法  也就是copy的方法，我们从创建一个对象开始，使用上面的一个方法创建对象,注意下面的
		//程序与上面的无关了  只是使用是classType
		
		Constructor con2=classType.getConstructor(new Class[]{});
		Object object_copy=con2.newInstance(new Object[]{});
		Field[] field_array=classType.getDeclaredFields();
		for(Field field:field_array)
		{
			String fieldname=field.getName();
			String firstLettor=fieldname.substring(0,1).toUpperCase();
			String getMethodName="get"+firstLettor+fieldname.substring(1);
			String setMethodName="set"+firstLettor+fieldname.substring(1);
			Method getMethod=classType.getDeclaredMethod(getMethodName,new Class[]{});
			Object value=getMethod.invoke(obj,new Object[]{});
			Method setMethod=classType.getDeclaredMethod(setMethodName,new Class[]{field.getType()});
			//这里我们可以通过使用Field对象field的getType来获取这个东西的Class属性
			setMethod.invoke(object_copy,new Object[]{value});			
		}
		Customer  cus_obj=(Customer)obj;
		Customer  cus_obj_copy=(Customer)object_copy;
		System.out.println(cus_obj.getName()+cus_obj.getId()+cus_obj.getAge());
		System.out.println(cus_obj_copy.getName()+cus_obj_copy.getId()+cus_obj_copy.getAge());
		System.out.println("----------------------------------------");
		
		
		return object_copy;
	}
	public static void main(String[] args) throws Exception
	{
		ConstractorTest test=new ConstractorTest();
		Customer customer1=new Customer(1,"liran",25);
		test.copy(customer1);
	}
}

class Customer
{
	private Integer id;
	private String name;
	private int age;
	
	public Customer(){}//这里注意必须是Public的构造方法才可以使用Constructor这种反射的机制
	public Customer(Integer id,String name,int age)
	{
		this.id=id;
		this.name=name;
		this.age=age;
	}
	
	public Integer getId()
	{
		return id;
	}
	public void setId(Integer id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
}