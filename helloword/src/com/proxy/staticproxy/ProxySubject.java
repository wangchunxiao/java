package com.proxy.staticproxy;

public class ProxySubject extends Subject
{
	private RealSubject realsubject;
	
	@Override
	public void doSomething()
	{
		System.out.println("���Ǵ����ڵ�����ʵ��ɫ�ķ���֮ǰ�����õķ������൱�ڰ�װ����ʵ�����������н��");
		if(realsubject==null)
			realsubject=new RealSubject();
		realsubject.doSomething();	
		System.out.println("���Ǵ����ڵ�����ʵ��ɫ�ķ���֮�󣬵��õķ������൱�ڰ�װ����ʵ�����������н��");
	}
}
