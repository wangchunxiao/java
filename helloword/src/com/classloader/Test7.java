/*
 * 1.�����������classloader�����ķ�����ͬʱ��֤����ĳ���ಢ����������ʹ��ĳ���࣬Ҳ����˵����಻�����Ӻͳ�ʼ��
 * 2.��ϰ�£���ֵ����̬����Ĭ��ֵ���������ӽ׶�
 * 
 * */

package com.classloader;


class C2
{
	static
	{
		System.out.println("��ʼ��c2");
	}
}
public class Test7
{
	public static void main(String[] args) throws ClassNotFoundException
	{
		ClassLoader loader=ClassLoader.getSystemClassLoader();
		Class<?> classType=loader.loadClass("com.classloader.C2");
		
		System.out.println("---------------------------");
		classType=Class.forName("com.classloader.C2");
	}
}
