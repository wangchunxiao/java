/*
 * 1.ִ�л���ִ��󣬷��������ԭ�򣬵������̵߳�ʱ�����ӵ��߳���wait��ʱ��ֻ���ܵȵ������̵߳�notify���������
 *   ��ȷִ�еģ����ǵ��̱߳��4�����߸����ʱ�����ӵ��߳����ж�һ��֮�����wait�������ܵȵ��Ļ������ӵ�notify������
 *   �ͻ����ִ�д��� ע����������˵�����ж�һ�Σ�Ҳ������if,Ҫ�����ж���Ȼ��Ҫ��ѭ���ģ�Ҫ�����˼��
 * */

package com.multithread.waitandnotifytest;

public class Sample
{
	private int number;

	public synchronized void increase()
	{
		try
		{
			Thread.sleep((long) (Math.random() * 200));
		} catch (InterruptedException e1)
		{
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		//if (0 != number)//����˵���ж�һ�Σ���ָ��������if������ֻ�ж�һ��
		while(0!=number)
		{
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		number++;
		System.out.println(Thread.currentThread() + " " + number);
		notify();
	}

	public synchronized void decrease()
	{
		try
		{
			Thread.sleep((long) (Math.random() * 200));
		} catch (InterruptedException e1)
		{
			// TODO �Զ����ɵ� catch ��
			e1.printStackTrace();
		}
		//if (1 != number)//����˵���ж�һ�Σ���ָ��������if������ֻ�ж�һ��
		while(1!=number)
		{
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
		number--;
		System.out.println(Thread.currentThread() + " " + number);
		notify();
	}
}