/*
 * 1.47�д��봦���������synchronized��������֮ǰ��synchronized�Ĵ����ͻὫexecute2����һ����ס������֮ǰ���Ǹ�
 *   ����,��һ��������һ��synchronized����ס��������suychronized��Ҳ�ᱻ��ס��
 *   47�д��봦������Ƿ�synchronized�ģ���֮ǰ��synchronized���Ὣͬһ����ķ�synchronized��ס��������ִ��
 * 2.����Ȼ����С���������Ĺ�ض���һ����
 * */

package com.multithread;

public class SynchronizedBlockTest
{
	public static void main(String[] args)
	{
		BlockTest b1 = new BlockTest();

		Thread t3 = new ThreadBlock1(b1);
		Thread t2 = new ThreadBlock1(b1);
		// t2.start();

		Thread t4 = new ThreadBlock2(b1);
		t3.start();
		t4.start();
	}
}

class BlockTest
{
	public void execute()// ���ǻ���������static����
	{
		synchronized (this)
		{
			for (int i = 0; i < 10; i++)
			{
				try
				{
					Thread.sleep((long) (500 * Math.random()));
				} catch (InterruptedException e)
				{
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}

				System.out.println("thread execute " + i);
			}
		}
	}

	public void execute1()// ����ע��Ҫ�Ƿ�Synchronized����ĳ���̷߳���Synchronized��execute()��ʱ����������Ͳ��ᱻͬʱ����
	{
		//synchronized (this)
		{
			for (int i = 0; i < 10; i++)
			{
				try
				{
					Thread.sleep((long) (500 * Math.random()));
				} catch (InterruptedException e)
				{
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}

				System.out.println("thread execute1 " + i);
			}
		}
	}
}

class ThreadBlock1 extends Thread
{
	private BlockTest loc;

	public ThreadBlock1(BlockTest loc)
	{
		this.loc = loc;
	}

	@Override
	public void run()
	{
		loc.execute();
	}
}

class ThreadBlock2 extends Thread
{
	private BlockTest loc;

	public ThreadBlock2(BlockTest loc)
	{
		this.loc = loc;
	}

	@Override
	public void run()
	{
		loc.execute1();
	}
}