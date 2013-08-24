/*
 * 1.Serializable序列化是个借口，允许将这个对象的内容写到文件中，以二进制的形式，对象中的静态不可以写，transient属性
 *   不可以写入。
 * 2.Serializable有传递性，如果它里面的属性是Serializable的,而属性的属性又是Serializable的，则可以传递写入
 * 3.对于不可以Serializable的属性，要想将这个对象写入，必须声明这个属性是transient的，否则报错
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
