package com.multithread.waitandnotifytest;

public class ThreadForDecrease extends Thread
{
	private Sample sample;
	public ThreadForDecrease(Sample sample)
	{
		this.sample=sample;
	}
	
	@Override
	public void run()
	{
		// TODO 自动生成的方法存根
		for(int i=0;i<10;i++)
		{
			sample.decrease();
		}
	}
}
