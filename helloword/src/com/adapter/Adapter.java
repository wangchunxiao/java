/*
 * ����Adapter�ĺ��Ľ�ɫ,������������Դ��ɫת����ΪĿ���ɫ
 * */

package com.adapter;

public class Adapter extends Adaptee implements Target
{
	@Override
	public void request()
	{
		//����Ŀ���ɫ�ķ���ʵ����Դ��ɫҪʵ�ֵĹ���
		doSomething();
	}
	
}