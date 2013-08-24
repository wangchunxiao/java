/*
 * 1.通过序列化，将objecet写入流中，然后再读出，实现深复制
 * 
 * */
package com.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class CloneTest3
{
	public static void main(String[] args) throws ClassNotFoundException, IOException
	{
		Teacher1 t1=new Teacher1(40,"liuwei");
		Teacher1 t2;
		t2=(Teacher1)(t1.deepClone());
		System.out.println(t1.age+", "+t1.name);
		System.out.println(t2.age+", "+t2.name);
		System.out.println("----------------------");
		
		t2.name="zhangdi";
		System.out.println(t1.age+", "+t1.name);
		System.out.println(t2.age+", "+t2.name);
	}
}

class Teacher1 implements Serializable
{
	public int age;
	public String name;
	public Teacher1(int age,String name)
	{
		this.age=age;
		this.name=name;
	}
	
	public Object deepClone() throws IOException, ClassNotFoundException
	{
		Object object;// =new Object();
		
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(bos);
		oos.writeObject(this);
		
		ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois=new ObjectInputStream(bis);
		object=ois.readObject();
		
		return object;
	}
}
