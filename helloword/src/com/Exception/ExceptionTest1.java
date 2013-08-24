/*
 * 1.我们在处理异常的时候,在catch(Exception e)这种申明的e都是在异常发生的时候抛出的异常对象，然后复制给e，所以说可以有
 *   很多的catch，但是执行就一个。
 * 2.在try里面要是发生了异常，后面的语句就不继续执行了，转而执行catch和finally，finall无论有没有异常都会执行
   3.有异常的时候我们可以选择catch也可以选择一级一级向上抛，就是像我的test()方法,里面抛出个异常，然后我在调用的时候，谁
            调用谁处理，处理的方式可以是继续抛，这个例子里就是main函数继续抛出异常，最后可以都抛给虚拟机
*/
package com.Exception;

public class ExceptionTest1
{
	public void test() throws Exception//里面抛出异常，这里必须要加
	{
		System.out.println("hello world");
		throw new Exception();
	}
	public static void main(String[] args)
	{
		ExceptionTest1 ex=new ExceptionTest1();
		//ex.test();//这里报错 我有两个选择，一个是网上抛，一个是自己写catch
		try
		{
			ex.test();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("finnally out put ");
		}
	}
}
