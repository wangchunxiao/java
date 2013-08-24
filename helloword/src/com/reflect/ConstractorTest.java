/*
 * 1����ע�������Public�Ĺ��췽���ſ���ʹ��Constructor���ַ���Ļ���  �������ע�� ֮ǰ����һֱ����
 * 2.Long���Զ�װ��������һ�û�����ף�֮ǰid��Long���� Ȼ����ֱ��������2����װ�䱨����New Long(2)�Ͳ��ᱨ��
 *   ������������ ����ֱ�ӱ���װ��Integer ��1 ��������1L�Ϳ��԰�װ��Long��
 * 3.�������ǿ���ͨ��ʹ��Field����field��getType����ȡ���������Class����    ��������ע��
 * 4.ע�����getFields��getDeclaredFields��Ҫ�ô���ȡ�����õ��Ǻ���ķ���
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
		
		//������ͨ�����䴴����������ַ��������в����Ĺ��캯�����ɶ���ֻ���õ����ַ���
		Object obj3=classType.newInstance();
		
		
		Constructor con=classType.getConstructor(new Class[]{});
		Object obj1=con.newInstance(new Object[]{});
		
		Constructor con1=classType.getConstructor(new Class[]{Integer.class,String.class,int.class});
		Object obj2=con1.newInstance(new Object[]{2,"liran",25});
		
		System.out.println(obj1+" "+ obj2+" " + obj3);
		
		System.out.println("----------------------------------------");
		
		Field[] field_array1=classType.getDeclaredFields();//ע�����getFields��getDeclaredFields��Ҫ�ô�
		for(Field field:field_array1)
		{
			System.out.println(field);
		}
		System.out.println("----------------------------------------");
		
		//��������ʵ�ָ���Object�ķ���  Ҳ����copy�ķ��������ǴӴ���һ������ʼ��ʹ�������һ��������������,ע�������
		//������������޹���  ֻ��ʹ����classType
		
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
			//�������ǿ���ͨ��ʹ��Field����field��getType����ȡ���������Class����
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
	
	public Customer(){}//����ע�������Public�Ĺ��췽���ſ���ʹ��Constructor���ַ���Ļ���
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