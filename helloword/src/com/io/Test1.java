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
			//length=fin.read(buffer,0,200);�����������жϾͲ��� һ����Ҫ���ж�ѭ��������ʱ������ж�������ֵ�ĸ���
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
