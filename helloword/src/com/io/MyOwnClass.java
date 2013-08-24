/*����������Լ�д��һ�������ֽ������̳���inputStream��
 * 1.��java��  byteռһ���ֽڣ�shor��char=2���ֽڣ�int,float=4���ֽڣ�long,double=8���ֽ�,�����ǽ�һ��int��ֵ��һ��byte��ʱ��������
 * int��-128��127֮�䣬���ǿ���ǿ������ת�������ᷢ��ֵ�ı仯�ģ�����Ҫ�ǲ��������Χ�ڣ�Ҳ�ǿ��Ը�ֵ�ģ�����ֵ�ͷ����˱仯����λ
 * ��������
 * */
package com.io;

import java.io.IOException;
import java.io.InputStream;

public class MyOwnClass 
{
	public static void main(String[] args)
	{
		byte[] bArray=new byte[18];
		
		for(int i=0;i<bArray.length;i++)
		{
			bArray[i]=(byte)(i*10);
		}
		
		for(byte b:bArray)
		{
			System.out.println(b);
		}
	}
}

class MyInputStream extends InputStream
{
	
	private byte[] data;
	private int ptr;
	
	public MyInputStream(byte[] data)
	{
		this.data=data;
		this.ptr=0;
	}
	
	@Override
	public int read() throws IOException
	{
		return (ptr<data.length)? data[ptr++]:-1;
	}
}