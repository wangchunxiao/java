/*这个用来测试中我用了transient的方法的序列化输出值
 * 通过事例可以看出输出的是null
 * 
 * */
package SomeTest.searizabletest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Test1
{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		Person p1=new Person(12,"男","张三");
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ObjectOutputStream  oos=new ObjectOutputStream(baos);
		oos.writeObject(p1);
		byte[] buffer=baos.toByteArray();
		baos.close();
		oos.close();
		
		ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(buffer,0,buffer.length));
		Person p2=(Person)ois.readObject();
		System.out.println(p2.age+"  "+p2.name+"   "+p2.sex);
		ois.close();
	}
}

class Person implements Serializable
{
	public int age;
	public transient String sex;
	public String name;
	public Person(int age,String sex,String name)
	{
		this.age=age;
		this.sex=sex;
		this.name=name;
	}
}