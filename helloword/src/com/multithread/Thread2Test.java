/*
 * 1.不用直接stop线程，通过下面的方式可以停止线程，但是注意调用我写的stop方法的可不直接是线程对象，而是构造线程的时候我传递的
 *   那个实现了Runnable接口的对象(其实线程里有个target属相指向的就是这个对象，私有我不能直接用)，
 * 2.线程执行的代码是在run方法中的代码，也就是我在main方法中调用的sleep就是sleep主线程，而在run中调用的sleep就是sleep相应
 *   的线程
 * 3.我们将对象传递给类中的成员变量，在JAVA中传递的相当于都是指针，而在C++中我们如果是在类中直接声明的另一个类的对象作为成员变量，
 *   则一定是分配了一片新的内存区域，相当于传递就是复制这个对象中相应的值，这个传递的参数和我们类中的成员变量在传递之后就没有关系
 *   了，C++要是用指向另一对象的指针做成员变量，则传递一个地址后，这个参数和成员变量都指向这个地址所对应的那个东东
 * */
package com.multithread;

public class Thread2Test
{
	public static void main(String[] args) throws InterruptedException
	{
		MyThread m1=new MyThread();
		Thread t1=new Thread(m1);
		//t1.run();//不能直接用run,一定要用start
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
		// TODO 自动生成的方法存根
		flag=true;
		while(true==flag)
		{		
			System.out.println("running ");
			try
			{
				Thread.sleep(1000);
			} catch (InterruptedException e)
			{
				// TODO 自动生成的 catch 块
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