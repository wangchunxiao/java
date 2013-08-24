/*
 * 1.�������Լ���������У������ʵ����serializable���ǿ����Լ�����Ҫ���д��������ֻ��Ҫ��serializable�ĵ��е�
 *   ������������ȫһ�µļӵ����ǵ��ඨ���У�Ȼ��serializable��ʱ����Զ��������ǵķ������������ԭ������Ϊ�Ƕ�̬
 *   ����һ��Ҫע�⣬���л�д�˵�һ���Ƕ����Ƶģ�������Ԫ����������д���(DataOutPutStream)
 * */

package com.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableTest2
{
	public static void main(String[] args) throws Exception
	{
		People2 p1=new People2(25,"liran","man",2120121111);
		People2 p2=new People2(24,"zhangsan","man",2123123313);
		People2 p3=new People2(21,"wangwu","women",2120122345);
		
		FileOutputStream fos=new FileOutputStream("SerializableTest2.txt");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		
		oos.writeObject(p1);
		oos.writeObject(p2);
		oos.writeObject(p3);
		
		oos.close();
		FileInputStream fis=new FileInputStream("SerializableTest2.txt");
		ObjectInputStream ois=new ObjectInputStream(fis);
		
		People2 p=null;
		for(int i=0;i<3;i++)
		{
			p=(People2)ois.readObject();
			System.out.println(p.age+","+p.name+","+p.sex+","+p.id);
		}
		
	}
}

class People2 implements Serializable
{
	int age;
	String name;
	transient String sex;
	long id;
	
	People2(){}
	People2(int age,String name,String sex,long id)
	{
		this.age=age;
		this.name=name;
		this.sex=sex;
		this.id=id;
	}
	
	 private void writeObject(java.io.ObjectOutputStream out)	throws IOException
	 {
		System.out.println("writeObject");
		out.writeInt(age);
		out.writeUTF(name);
	 }
	 private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	 {
		 System.out.println("readObject");
		 age=in.readInt();
		 name=in.readUTF();
	 }

	
}
