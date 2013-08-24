/*这个是我们自己写的一个输入字节流，继承了inputStream，
 * 1.在java中  byte占一个字节，shor，char=2个字节，int,float=4个字节，long,double=8个字节,当我们将一个int赋值给一个byte的时候如果这个
 * int在-128至127之间，它是可以强制类型转换而不会发生值的变化的，但是要是不在这个范围内，也是可以赋值的，但是值就发生了变化，高位
 * 就舍弃了
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