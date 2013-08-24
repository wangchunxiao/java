/*
 * 1.这个程序是用来测试我们用字节流是否可以读写中文
 * */
package com.io;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class SomeTest1
{
	public static void main(String[] args) throws IOException
	{
		FileInputStream fos=new FileInputStream("SomeTest1.txt");
		byte[] buffer=new byte[20];
		
		fos.read(buffer,0,20);
		System.out.println(buffer.toString());
		System.out.println("--------------------------");
		
		for(byte b:buffer)
		{
			System.out.println(b);
			System.out.println((char)b);
		}
		fos.close();

		System.out.println("--------------------------");
		FileReader fr=new FileReader("SomeTest1.txt");
		System.out.println(fr.getEncoding());
		char[] buffer2=new char[20];
		fr.read(buffer2,0,20);
		for(char c:buffer2)
		{
			System.out.println(c);
		}
		
	}
}
