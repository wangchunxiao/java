/*
 * 1.�����ڴ����쳣��ʱ��,��catch(Exception e)����������e�������쳣������ʱ���׳����쳣����Ȼ���Ƹ�e������˵������
 *   �ܶ��catch������ִ�о�һ����
 * 2.��try����Ҫ�Ƿ������쳣����������Ͳ�����ִ���ˣ�ת��ִ��catch��finally��finall������û���쳣����ִ��
   3.���쳣��ʱ�����ǿ���ѡ��catchҲ����ѡ��һ��һ�������ף��������ҵ�test()����,�����׳����쳣��Ȼ�����ڵ��õ�ʱ��˭
            ����˭��������ķ�ʽ�����Ǽ����ף�������������main���������׳��쳣�������Զ��׸������
*/
package com.Exception;

public class ExceptionTest1
{
	public void test() throws Exception//�����׳��쳣���������Ҫ��
	{
		System.out.println("hello world");
		throw new Exception();
	}
	public static void main(String[] args)
	{
		ExceptionTest1 ex=new ExceptionTest1();
		//ex.test();//���ﱨ�� ��������ѡ��һ���������ף�һ�����Լ�дcatch
		try
		{
			ex.test();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("finnally out put ");
		}
	}
}
