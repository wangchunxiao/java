/*
 * 1.RandomAccessFile这个文件也是有指针的，可读的同时可写，一定要记得控制指针
 *   像我这个就需要file.seek(0)
 * */

package com.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest
{
	public static void main(String[] args) throws Exception
	{
		RandomAccessFile file=new RandomAccessFile("RandomAccessFileTest.txt","rw");
		Person p1=new Person(25,"liran",2120121111);
		Person p2=new Person();
		
		p1.outputRandomAccessFile(file);
		file.seek(0);
		p2.inputRandomAccessFile(file);
		
		System.out.println(p2.age + " "+p2.name+" "+p2.id);
	}
}

class Person
{
	int age;
	String name;
	long id;
	
	public Person(){}
	public Person(int age,String name,long id)
	{
		this.age=age;
		this.name=name;
		this.id=id;
	}
	
	public void outputRandomAccessFile(RandomAccessFile file) throws IOException
	{
		file.write(age);
		file.writeUTF(name);
		file.writeLong(id);		
	}
	public void inputRandomAccessFile(RandomAccessFile file) throws IOException
	{
		age=file.read();
		name=file.readUTF();
		id=file.readLong();
	}
	
}