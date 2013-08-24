/*
 * 1.“hello”.equals(str)  把常量放在前面，就不会抛出空指针异常了
 * */
package com.Exception;

public class MyExceptionTest
{
	public void method(String str) throws MyException, MyException2
	{
		if(null==str)
		{
			System.out.println("进入method，抛出MyException异常");
			throw new MyException("不能为空！");
		}
		else if(str.equals("hello"))//这里注意 如果str是null，有会有空指针访问的异常，我们可以这样写
			                        //“hello”.equals(str)  把常量放在前面，就不会抛出空指针异常了
		{
			System.out.println("进入method，抛出MyException2异常");
			throw new MyException2("和hello相等");
		}
		else
		{
			System.out.println("method执行完毕，没有抛出异常！");
		}
	}
	
	public static void main(String[] args) throws MyException
	{
		MyExceptionTest myExceptionTest=new MyExceptionTest();
		//myExceptionTest.method(null);用了继续抛的方法
		
		try{
			myExceptionTest.method(null);//("hello");
		}//这里有个需要注意的，就是我既继续抛了又接受了抛出的异常，会出现什么状况
		catch(MyException e1){
			System.out.println("进入catch MyException 的块");
			e1.printStackTrace();
		}
		catch(MyException2 e2){
			System.out.println("进入catch MyException2 的块");
			e2.printStackTrace();
		}
		finally
		{
			System.out.println("执行结束");
		}
	}
}
