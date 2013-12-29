/*
 * �������˵������ļ���
 * 1.����jvm��һ��Ԥ������ƣ����ǲ�һ�������������õ�ʱ��ż�������ൽ�ڴ棬�������ʱ����Ǽ�����������ִ���Ҳ������
 *   ���ǵȵ�������������õ�ʱ���ٱ���
 * 2.�����������һ�������ַ�ʽ�������ַ�ʽ��ʹ����г�ʼ������һ����ʹ����ص��ڴ�
 * 3.��Ĵ���˳���ǣ�1)���ص��ڴ�.2)��֤����������ʼ��
 * 4.�����������õķ�ʽ�У�ʵ�������󣬵�����ľ�̬������������ľ�̬��Ա����(���縳ֵ)��������������ʱҲҪ�ȳ�ʼ�����࣬
 *   ���䣬��������(��main��������)��
 * 
 * 5.ǰһ�����Ͽ�û��⣬11.1�ſ��������jvm֮����������ʵ�������ȼ���������ص�һ�����裬���ظ��������£�������Ķ������ֽ���������̬�洢�ṹת��
 * Ϊ����������ʱ���ݽṹ�����ڴ����������class������֤����˵��׼���׶αȽ���Ҫ��׼���׶η��������(static)���ڴ棬�ڴ����ڷ���ȥ�ڽ��з��䣬
 * ��ζ����������(static)��0ֵ(��ͬ���͵�0ֵ��ͬ,��bool����false��reference����null)����ʼ���׶β�����ִ��java���룬���ҵ�staitc������ʼ����
 * static�����Ȳ��������ճ���˳��ִ�С�
 * 6.��������о���׼���׶η����ڴ�Ȼ������static������0ֵ��Ȼ���ʼ���׶�ִ��java���룬����count2�Ĵ��뿿�����ֱ����³�ʼ��Ϊ0
 * */

package com.classloader;

public class Mytest
{
	public static void main(String[] args)
	{
		SingleTon singleton=SingleTon.getInstance();
		System.out.println(singleton.count1);
		System.out.println(singleton.count2);
	}
}
class SingleTon
{
	private static SingleTon singleton=new SingleTon();
	public static int count1;
	public static int count2=0;
	
	public SingleTon()
	{
		count1++;
		count2++;
	}
	
	public static SingleTon getInstance()
	{
		return singleton;
	}
}