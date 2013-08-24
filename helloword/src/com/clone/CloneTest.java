/*
 * 1.��ƺ�ǳ����.ǳ���ƶ��ڶ�������ã�ֻ�Ǹ������������(ֻ������ָ�룬��û����������һ���ڴ����������)�������
 *   �ǽ�ΪҪ���ƵĶ�������һ���µ��ڴ�����Ȼ�󽫶����һЩֵ���ƹ�ȥ�������ʱ��Ķ�������þͲ�����ǰҪ���Ƶ��Ǹ�����
 *   ��������
 * 2.protected���εķ���һ�㶼��ϣ��������д�ģ�����ֿ������飬����Ȩ�޵ļ����С����,private-default-protected-public
 *   ��νprotected���Ǳ�default����������Կ��������Ա���������������д�������(��ΪPublic),�������İ����ǿ��������������
 * 3.java��������������Է���Ȩ�޺�C++��������һ����C++Ҫ������̺Ͷ������Իῼ�Ǽ̳е�Ȩ�ޣ���javaҪ�Ƿ���������г�Ա
 *   Ҳ������һ������ʵ�ֵģ����ԾͲ���˵�̳еļ���ֱ�Ӿ�������һ�����п��ĵ��Ϳ�����������
 * 4.����clone()������Ҫ�븴�Ƶ���һ��Ҫʵ��cloneable����ӿڣ�����ӿ��ǿյĺ�Serializable��һ����,ֻ�Ǹ��߱�����Ҫ
 *   ��ô�ɣ���ô���롣һ��Ҫ��д�����clone�������ҵ�һ�����һ���ǵ���super.clone()������jdk�ĵ�˵�ģ����õ�Ч������
 *   ������object���clone�����������ڴ�ȵȹ���
 * 5.object���clone������ǳ����
 * */
package com.clone;

import java.io.Serializable;

public class CloneTest implements Serializable
{
	public static void main(String[] args) throws CloneNotSupportedException
	{
		Student s1=new Student(20,"liran");
		Student s2=(Student)(s1.clone());
		
		System.out.println(s1.name+" "+s1.age);
		System.out.println(s1==s2);
	}
}

class Student implements Cloneable,Serializable
{
	public int age;
	public String name;
	public Student(){};
	public Student(int age,String name){
		this.age=age;
		this.name=name;
	}
	
	@Override
	public  Object clone() throws CloneNotSupportedException
	{
		// TODO �Զ����ɵķ������
		return super.clone();
	}
}