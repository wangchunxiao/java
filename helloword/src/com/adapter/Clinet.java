/*������ģʽ Adapter Pattern
 *1.������ģʽ����������������ƵĽӿں���ǰ�Ľӿڲ�������,����֮ǰ�Ľӿڹ����������������õ�,��Ȼ�ӿڲ�����,�����һ�������֮ǰ��
 *  ���õĽӿڵ�ʵ�ֵĴ���,��ô��ֻ��Ҫ��Դ��ɫ�Ľӿں�Ŀ���ɫ�Ľӿ�֮��Ū��ת�����ͺ���,�����������ģʽ
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
