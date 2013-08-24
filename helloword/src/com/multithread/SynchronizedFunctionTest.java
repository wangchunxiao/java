/*
 * 1.加了Synchronized就是给对象上锁了，只有当某个线程对这个对象的操作完成了，才能解开这个线程上的锁，进而其他线程继续执行
 * 2.Synchronized是给一个对象上锁，而不是某个方法,如果一个对象的Synchronized方法被某个线程执行，则这个对象的其他synchronized的方法也被一道上锁，而非
 *   Synchronized方法没有上锁，看下面的注释和代码
 * 3.如果线程调用的是synchronized的static方法，则实际上锁的是这个对象的class对象的所有synchronized方法，(就是我下面的例子其中一个方法变成static的，
 *   则还是乱序的，尽管都是synchronized的方法，因为锁的实际上就是这个类事例对象和这个类的class对象)
 * 4.如果下面那个例子两个方法都是static的和synchronized的，则就是说class对象被锁，访问的时候顺序执行，先一个后一个，如果
 *   一个只是static的，另一个是static的和synchronized的，就和注解2说的一样，非synchronized的没有被锁定，还是乱序执行
 *   即，锁synchronized的规则(注解2)的那个也适用于class对象
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
			
			//测试Synchronized上锁，验证是给对象上锁，但对象的非Synchronized方法不会上锁
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
				// TODO 自动生成的 catch 块
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
		// TODO 自动生成的方法存根
		
//		try
//		{
//			Thread.sleep(1000);
//		} catch (InterruptedException e)
//		{
//			// TODO 自动生成的 catch 块
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
	public   synchronized  void execute()//我们还可以试验static方法
	{
		for(int i=0;i<10;i++)
		{
			try
			{
				Thread.sleep(200);
			} catch (InterruptedException e)
			{
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			System.out.println("thread "+i);
		}
	}
	public   synchronized void execute1()//  这里注意要是非Synchronized，在某个线程访问Synchronized的execute()的时候，这个方法就不会被同时上锁
	{
		for(int i=0;i<10;i++)
		{
			try
			{
				Thread.sleep(500);
			} catch (InterruptedException e)
			{
				// TODO 自动生成的 catch 块
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