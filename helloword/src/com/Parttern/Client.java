/*
 * 1.�������Ƕ�̬�Լ��Լ������Ľӿڱ��һ���ܺõ�Ӧ�ã�TreeSet����һ���������ʵ�� ��Ϊ�Ĳ���
 *   a.������һ���ӿ��࣬������Comparator����Ƚ�������Щ����
 *     ����г�����Խ�ɫ
 *   b.Ȼ������TreeSet����һ����Ա���� �������Comparator�ӿ����ͣ�ע��һ���ǽӿ����ͣ��������ݽ�����ʱ��Ϳ��Ը��ݾ����
 *     �Ƚ��������ʵ�ֶ�̬����������Ϊ���������Ӵ�������ִ�ж����Լ��ķ���
 *     ע�⣺����л�����ɫ
 *   c.���ǳ�����Ǹ��ӿ������ǵľ���ʵ�֣�ʵ���Լ��Ĺ���
 *     ����о�����Խ�ɫ
 *   d.���ǿͻ���main�����ˣ����������Ƕ��廷����Ķ���Ȼ��������Լ��Ķ���ľ���ľ�����Խ�ɫ���󴫵ݸ������������ʱ��
 *     ���ã��Ϳ���ʵ�ֲ���ģʽ��
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
