/*
 * 1.执行会出现错误，分析错误的原因，当两个线程的时候，增加的线程在wait的时候只可能等到减少线程的notify，则程序是
 *   正确执行的，但是当线程变成4个或者更多的时候，增加的线程在判断一次之后进入wait，它可能等到的还是增加的notify，所以
 *   就会出现执行错误。 注意我们这里说的是判断一次，也就是用if,要想多次判断显然是要用循环的，要有这个思想
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
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		//if (0 != number)//上面说的判断一次，是指这里用了if，所以只判断一次
		while(0!=number)
		{
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO 自动生成的 catch 块
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
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		//if (1 != number)//上面说的判断一次，是指这里用了if，所以只判断一次
		while(1!=number)
		{
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		number--;
		System.out.println(Thread.currentThread() + " " + number);
		notify();
	}
}
