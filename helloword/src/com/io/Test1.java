package com.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Test1
{
	public static void main(String[] args) throws Exception
	{
		InputStream fin=new FileInputStream("hello.txt");
		byte[] buffer=new byte[200];
		int length=0;
		while(-1!=(length=fin.read(buffer,0,200)))
		{	
			//length=fin.read(buffer,0,200);这里在里面判断就不行 一定是要在判断循环条件的时候进行判断条件数值的更新
			String str=new String(buffer,0,length);
			System.out.println(str);			
		}
		fin.close();
		
		OutputStream fout=new FileOutputStream("hellojava.txt");
		String output="this is a text!";
		byte[] buffer1=output.getBytes();
		fout.write(buffer1);
		fout.close();
	}
	
}
