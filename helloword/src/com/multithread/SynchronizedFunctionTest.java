/*
 * 1.����Synchronized���Ǹ����������ˣ�ֻ�е�ĳ���̶߳��������Ĳ�������ˣ����ܽ⿪����߳��ϵ��������������̼߳���ִ��
 * 2.Synchronized�Ǹ�һ������������������ĳ������,���һ�������Synchronized������ĳ���߳�ִ�У���������������synchronized�ķ���Ҳ��һ������������
 *   Synchronized����û���������������ע�ͺʹ���
 * 3.����̵߳��õ���synchronized��static��������ʵ������������������class���������synchronized������(�������������������һ���������static�ģ�
 *   ��������ģ����ܶ���synchronized�ķ�������Ϊ����ʵ���Ͼ�����������������������class����)
 * 4.��������Ǹ�����������������static�ĺ�synchronized�ģ������˵class�����������ʵ�ʱ��˳��ִ�У���һ����һ�������
 *   һ��ֻ��static�ģ���һ����static�ĺ�synchronized�ģ��ͺ�ע��2˵��һ������synchronized��û�б���������������ִ��
 *   ������synchronized�Ĺ���(ע��2)���Ǹ�Ҳ������class����
 * */

package com.multithread;

public class SynchronizedFunctionTest
{
	public static void main(String[] args)
	{
			Account a1=new Account();
			Thread t1=new Thread3(a1);
			Thread t2=new Thread3(a1);
			//t1.start();
			//t2.start();
			
			//����Synchronized��������֤�Ǹ�����������������ķ�Synchronized������������
			LockObjectTest loc=new LockObjectTest();
			Thread t3=new Thread4(loc);
			Thread t4=new Thread5(loc);
			t3.start();
			t4.start();
			
	}
}

class Account
{
	public int money = 1000;

	public  synchronized int getMoney(int number)
	{
		if (number < 0)
			return -1;
		else if (money < 0)
			return -2;
		else if (number > money)
			return -3;
		else
		{
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			money -= number;
			System.out.println(money);
			return number;
		}
	}
}

class Thread3 extends Thread
{
	public Account account;

	public Thread3(Account account)
	{
		this.account = account;
	}
	@Override
	public void run()
	{
		// TODO �Զ����ɵķ������
		
//		try
//		{
//			Thread.sleep(1000);
//		} catch (InterruptedException e)
//		{
//			// TODO �Զ����ɵ� catch ��
//			e.printStackTrace();
//		}
//		int temp=account.getMoney(800);
//		System.out.println(temp);
//		System.out.println(account.money);
		
		int temp=account.getMoney(800);	
		System.out.println(temp);
	}
}

class LockObjectTest
{
	public   synchronized  void execute()//���ǻ���������static����
	{
		for(int i=0;i<10;i++)
		{
			try
			{
				Thread.sleep(200);
			} catch (InterruptedException e)
			{
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
			System.out.println("thread "+i);
		}
	}
	public   synchronized void execute1()//  ����ע��Ҫ�Ƿ�Synchronized����ĳ���̷߳���Synchronized��execute()��ʱ����������Ͳ��ᱻͬʱ����
	{
		for(int i=0;i<10;i++)
		{
			try
			{
				Thread.sleep(500);
			} catch (InterruptedException e)
			{
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
			System.out.println("thread "+i);
		}
	}
}
class Thread4 extends Thread
{
	private LockObjectTest loc;
	public Thread4(LockObjectTest loc)
	{
		this.loc=loc;
	}
	@Override
	public void run()
	{
		loc.execute();
	}
}
class Thread5 extends Thread
{
	private LockObjectTest loc;
	public Thread5(LockObjectTest loc)
	{
		this.loc=loc;
	}
	@Override
	public void run()
	{
		loc.execute1();
	}
}