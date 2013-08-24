/*
 * 1.���ȷ���һ�����Ȼ���ض���Class����Ȼ��ͨ���ղ��Ǹ�������Method����Ȼ���ٵ������Method����ķ�����ý����
 *   ������Ҫע�⣬����ǰһ���Ĳ���һ���ǲ�����Class�����ǿɱ�������� �������Ǿ���Ķ��������Ҳ�ǿɱ����������
 * 2.int�ͺ�Integer���͵�Class����������ͨ�ã�������������Ϊ���������ڷ�����÷�����ʱ�����ĸ�
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
		//Integer.class�ͳ���ִ�д���
		Method addmethod=classType.getDeclaredMethod("add",new Class[]{Integer.class,Integer.class});
		//Object addresult=addmethod.invoke(methodtest_object,3,5);Ҳ����������ôд������д�����Զ�װ����
		Object addresult=addmethod.invoke(methodtest_object,new Object[]{2,4});//������ǲ�����Object���ͣ�����Ҳ����Object���ͣ�û����װ��
		System.out.println(addresult);
	}
}
