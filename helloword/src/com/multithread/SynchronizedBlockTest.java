/*
 * 1.47行代码处，如果我是synchronized方法，则之前的synchronized的代码块就会将execute2方法一起锁住，即和之前的那个
 *   类似,在一个对象中一个synchronized被锁住，其他的suychronized的也会被锁住。
 *   47行代码处，如果是非synchronized的，则之前的synchronized不会将同一对象的非synchronized锁住，是乱序执行
 * 2.块虽然粒度小，看来锁的规矩都是一样的
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
	public void execute()// 我们还可以试验static方法
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
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}

				System.out.println("thread execute " + i);
			}
		}
	}

	public void execute1()// 这里注意要是非Synchronized，在某个线程访问Synchronized的execute()的时候，这个方法就不会被同时上锁
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
					// TODO 自动生成的 catch 块
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