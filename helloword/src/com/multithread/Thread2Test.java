/*
 * 1.����ֱ��stop�̣߳�ͨ������ķ�ʽ����ֹͣ�̣߳�����ע�������д��stop�����Ŀɲ�ֱ�����̶߳��󣬶��ǹ����̵߳�ʱ���Ҵ��ݵ�
 *   �Ǹ�ʵ����Runnable�ӿڵĶ���(��ʵ�߳����и�target����ָ��ľ����������˽���Ҳ���ֱ����)��
 * 2.�߳�ִ�еĴ�������run�����еĴ��룬Ҳ��������main�����е��õ�sleep����sleep���̣߳�����run�е��õ�sleep����sleep��Ӧ
 *   ���߳�
 * 3.���ǽ����󴫵ݸ����еĳ�Ա��������JAVA�д��ݵ��൱�ڶ���ָ�룬����C++�����������������ֱ����������һ����Ķ�����Ϊ��Ա������
 *   ��һ���Ƿ�����һƬ�µ��ڴ������൱�ڴ��ݾ��Ǹ��������������Ӧ��ֵ��������ݵĲ������������еĳ�Ա�����ڴ���֮���û�й�ϵ
 *   �ˣ�C++Ҫ����ָ����һ�����ָ������Ա�������򴫵�һ����ַ����������ͳ�Ա������ָ�������ַ����Ӧ���Ǹ�����
 * */
package com.multithread;

public class Thread2Test
{
	public static void main(String[] args) throws InterruptedException
	{
		MyThread m1=new MyThread();
		Thread t1=new Thread(m1);
		//t1.run();//����ֱ����run,һ��Ҫ��start
		t1.start();
		Thread.sleep(2000);
		
		m1.stopRunning();
		
	}
}

class MyThread implements Runnable
{

	private boolean flag=false;
	@Override
	public void run()
	{
		// TODO �Զ����ɵķ������
		flag=true;
		while(true==flag)
		{		
			System.out.println("running ");
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}
	public void stopRunning()
	{
		flag=false;
		System.out.println("stoping");
	}
	
}