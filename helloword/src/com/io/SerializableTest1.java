/*
 * 1.Serializable���л��Ǹ���ڣ�����������������д���ļ��У��Զ����Ƶ���ʽ�������еľ�̬������д��transient����
 *   ������д�롣
 * 2.Serializable�д����ԣ�����������������Serializable��,�����Ե���������Serializable�ģ�����Դ���д��
 * 3.���ڲ�����Serializable�����ԣ�Ҫ�뽫�������д�룬�����������������transient�ģ����򱨴�
 * 
 * */

package com.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableTest1
{
	public static void main(String[] args) throws Exception
	{
		People p1=new People(25,"liran","man",2120121111);
		People p2=new People(24,"zhangsan","man",2123123313);
		People p3=new People(21,"wangwu","women",2120122345);
		
		FileOutputStream fos=new FileOutputStream("SerializableTest.txt");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		
		oos.writeObject(p1);
		oos.writeObject(p2);
		oos.writeObject(p3);
		
		oos.close();
		FileInputStream fis=new FileInputStream("SerializableTest.txt");
		ObjectInputStream ois=new ObjectInputStream(fis);
		
		People p=null;
		for(int i=0;i<3;i++)
		{
			p=(People)ois.readObject();
			System.out.println(p.age+","+p.name+","+p.sex+","+p.id);
		}
		
	}
}

class People implements Serializable
{
	int age;
	String name;
	transient String sex;
	long id;
	
	People(){}
	People(int age,String name,String sex,long id)
	{
		this.age=age;
		this.name=name;
		this.sex=sex;
		this.id=id;
	}
	
}
