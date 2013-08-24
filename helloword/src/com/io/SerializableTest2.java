/*
 * 1.在我们自己定义的类中，如果它实现了serializable我们可以自己定义要如果写这个类对象，只需要将serializable文档中的
 *   那两个方法完全一致的加到我们的类定义中，然后serializable的时候会自动调用我们的方法，至于这个原理，我认为是多态
 *   但是一定要注意，序列化写人的一定是二进制的，就是以元素类型那种写入的(DataOutPutStream)
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
