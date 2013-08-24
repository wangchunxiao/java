/*
 * 1.�߳�����������ֱ�ӵ���run,��һ��Ҫ��дrun������ֱ�ӵ��þ��������ͨ�ĳ�Ա����û���𣬵���start���������߳�
 * 2.������ʵ��Runnable�ӿڵĶ�������������Thread��һ�����췽��(�������ʵ��Runnable�ӿڶ�����Ǹ����췽��)������һ��thread����
 *   �������������ڲ�����ʵ�֣���Ϊ���ʵ��Runaable�ӿڵ�������Ҳ����Ҫ�úܶ�Σ�ֻ��һ�ξͺ��ˣ������������ڲ����������
 * 3.���ַ�ʽ��ִ��run������ʱ���ǲ�һ���ģ�Ҳ����˵Ϊʲô���Ǽ̳�Thread�����Ҫ��дrun��������ֱ����ʵ����Runnable�ӿڵĵĶ���
 *   ��ʱ������ʵ��run����������(���仰˵��ȻThreadʵ����Runnable�ӿڣ�Ҳ��ʵ����run������Ϊʲô������Ҫ��д)�������һ���ĸ�Դ
 *   ����Thread�Լ�ʵ�ֵ�run�����Ĵ�����
 * 4.���ַ�ʽ����һ����ĳ����ֱ�Ӽ̳�Thread�࣬Ȼ���������ĳ����Ķ��󣬵ڶ��־��Ƕ���һ��ʵ����Runnable�ӿڵ��࣬Ȼ������һ��
 *   Thread����(���ù��췽�������ղŵ��Ǹ�����󴫽�ȥ)��ע����ڶ��ֹ�������Thread����ɲ����Ǹ�ʵ����Runnable�ӿڵ������
 * */

package com.multithread;

public class Thread1Test
{
	public static void main(String[] args)
	{
		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();
		Thread t3 = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				// TODO �Զ����ɵķ������
				for(int i=0;i<100;i++)
				{
					System.out.println("���������ڲ���ʵ�ֵ���Thread�Ĵ�һ��ʵ��Runnable�ӿڵĶ���Ĺ��캯���ķ���"+i);
				}
			}
			
		});
		t1.start();
		t2.start();
		t3.start();
	}
}

class Thread1 extends Thread
{
	@Override
	public void run()
	{
		// TODO �Զ����ɵķ������
		for (int i = 0; i < 100; i++)
		{
			System.out.println("thread1  " + i);
		}
		super.run();
	}

}

class Thread2 extends Thread
{
	@Override
	public void run()
	{
		// TODO �Զ����ɵķ������
		for (int i = 0; i < 100; i++)
		{
			System.out.println("thread2  " + i);
		}
		super.run();
	}
}
