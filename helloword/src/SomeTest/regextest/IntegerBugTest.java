/*
 * 1.Integer in=new Integer(100)  这个是不适用缓存的那个，生成了新的对象
 *   Integer in=100;              这个是适用的缓存的，没有生成新的对象，使用的缓存的对象
 *   缓存范围(-128)~(127)
 * */

package SomeTest.regextest;

public class IntegerBugTest
{
	public static void main(String[] args)
	{
		 Integer in1=100;
		 //public static final int 里面是这个，不能想着修改了把缓冲的那个给冲掉了
	}
}
