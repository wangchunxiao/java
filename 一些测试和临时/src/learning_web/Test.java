/*测试类中我们不用this调用自己的方法*/
package learning_web;

public class Test
{	
	public void do1()
	{
		System.out.println("do1");
	}
	public void do2()
	{
		do1();
		System.out.println("do2");
	}

	public static void main(String[] args)
	{
		Test t1=new Test();
		t1.do2();
	}
}
