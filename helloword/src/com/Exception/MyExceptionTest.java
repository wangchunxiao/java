/*
 * 1.��hello��.equals(str)  �ѳ�������ǰ�棬�Ͳ����׳���ָ���쳣��
 * */
package com.Exception;

public class MyExceptionTest
{
	public void method(String str) throws MyException, MyException2
	{
		if(null==str)
		{
			System.out.println("����method���׳�MyException�쳣");
			throw new MyException("����Ϊ�գ�");
		}
		else if(str.equals("hello"))//����ע�� ���str��null���л��п�ָ����ʵ��쳣�����ǿ�������д
			                        //��hello��.equals(str)  �ѳ�������ǰ�棬�Ͳ����׳���ָ���쳣��
		{
			System.out.println("����method���׳�MyException2�쳣");
			throw new MyException2("��hello���");
		}
		else
		{
			System.out.println("methodִ����ϣ�û���׳��쳣��");
		}
	}
	
	public static void main(String[] args) throws MyException
	{
		MyExceptionTest myExceptionTest=new MyExceptionTest();
		//myExceptionTest.method(null);���˼����׵ķ���
		
		try{
			myExceptionTest.method(null);//("hello");
		}//�����и���Ҫע��ģ������Ҽȼ��������ֽ������׳����쳣�������ʲô״��
		catch(MyException e1){
			System.out.println("����catch MyException �Ŀ�");
			e1.printStackTrace();
		}
		catch(MyException2 e2){
			System.out.println("����catch MyException2 �Ŀ�");
			e2.printStackTrace();
		}
		finally
		{
			System.out.println("ִ�н���");
		}
	}
}
