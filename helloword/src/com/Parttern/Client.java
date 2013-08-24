/*
 * 1.策略类是多态以及自己命名的接口编程一个很好的应用，TreeSet就是一个策略类的实现 分为四部分
 *   a.首先是一个接口类，声明了Comparator这个比较器有哪些函数
 *     这个叫抽象策略角色
 *   b.然后是在TreeSet中有一个成员变量 就是这个Comparator接口类型，注意一定是接口类型，这样传递进来的时候就可以根据具体的
 *     比较器类对象实现多态，即父亲作为参数，儿子传进来，执行儿子自己的方法
 *     注意：这个叫环境角色
 *   c.就是抽象的那个接口类我们的具体实现，实现自己的功能
 *     这个叫具体策略角色
 *   d.就是客户端main函数了，在这里我们定义环境类的对象，然后把我们自己的定义的具体的具体策略角色对象传递给环境类对象，这时候
 *     调用，就可以实现策略模式了
 * */


package com.Parttern;
 
public class Client
{
	public static void main(String[] args)
	{
		StrategyPattern2 s1=new StrategyPattern2();
		StrategyEnvironment s_en=new StrategyEnvironment(s1);
		System.out.println(s_en.calculate(3,5));
		
		System.out.println("------------------");
		s_en.setStrategy(new StrategyPattern3());
		System.out.println(s_en.calculate(3,5));
		
	}
}
