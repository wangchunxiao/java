/*
 * ����������������һ���Ǹ���������ģ�Ҳ�����������Ϊnull��������������ֱַ�����չ��������ϵͳ������(Ӧ�ü�����)
 * �û��Զ�����඼��java.lang.Classloader������
 * 
 * */

package com.classloader;

public class ClassLoaderType 
{
	 public static void main(String[] args) throws Exception
	 {
		 Class<?> classType1=Class.forName("java.lang.String");
		 Class<?> classType2=Class.forName("com.classloader.C");
		 
		 System.out.println(classType1.getName()+"    "+classType1.getClassLoader());
		 System.out.println(classType2.getName()+"    "+classType2.getClassLoader());
	 }
}
class C
{
	
}