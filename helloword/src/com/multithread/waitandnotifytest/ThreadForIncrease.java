package com.multithread.waitandnotifytest;

public class ThreadForIncrease extends Thread
{
	private Sample sample;
	public ThreadForIncrease(Sample sample)
	{
		this.sample=sample;
	}
	@Override
	public void run()
	{
		// TODO 自动生成的方法存根
		for(int i=0;i<3;i++)
		{
			sample.increase();
		}
	}
}
