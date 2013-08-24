/*
 * 1.线程启动不能是直接调用run,但一定要重写run方法，直接调用就与调用普通的成员方法没区别，调用start方法启动线程
 * 2.可以用实现Runnable接口的对象做参数调用Thread的一个构造方法(传递这个实现Runnable接口对象的那个构造方法)来创建一个thread对象
 *   可以用匿名的内部类来实现，因为这个实现Runaable接口的类我们也不需要用很多次，只用一次就好了，就用匿名的内部类这个方法
 * 3.两种方式在执行run方法的时候是不一样的，也就是说为什么我们继承Thread类必须要重写run方法，而直接用实现了Runnable接口的的对象
 *   的时候我们实现run方法就行了(换句话说既然Thread实现了Runnable接口，也就实现了run方法，为什么我们需要重写)，这个不一样的根源
 *   就在Thread自己实现的run方法的代码中
 * 4.两种方式，第一种用某个类直接继承Thread类，然后生成这个某个类的对象，第二种就是定义一个实现了Runnable接口的类，然后声明一个
 *   Thread对象(调用构造方法，将刚才的那个类对象传进去)，注意这第二种归根结底是Thread对象可不是那个实现了Runnable接口的类对象
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
				// TODO 自动生成的方法存根
				for(int i=0;i<100;i++)
				{
					System.out.println("这是匿名内部类实现的用Thread的带一个实现Runnable接口的对象的构造函数的方法"+i);
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
		// TODO 自动生成的方法存根
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
		// TODO 自动生成的方法存根
		for (int i = 0; i < 100; i++)
		{
			System.out.println("thread2  " + i);
		}
		super.run();
	}
}
