/*适配器模式 Adapter Pattern
 *1.适配器模式就是在我现在新设计的接口和以前的接口不相容了,但是之前的接口工作还是正常和良好的,虽然接口不相容,但是我还是想用之前的
 *  良好的接口的实现的代码,那么我只需要将源角色的接口和目标角色的接口之间弄过转接器就好了,这就是适配器模式
 * */
package com.adapter;

public class Clinet
{
	public static void main(String[] args)
	{
		
		Target target2 =new Adapter();
		target2.request();
	}
}
