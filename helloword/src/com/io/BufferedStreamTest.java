/*
 * 1.使用BufferedOutputStream的时候，要注意这个是先将字节存入内存缓冲区，如果不close则不会将缓冲区的东西输出到文件中
 *   可以调用flush进行显示的刷新，输出缓冲区的字节到文件
 * */
package com.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class BufferedStreamTest
{
	public static void main(String[] args) throws Exception
	{
		OutputStream os=new FileOutputStream("bufferedOutputStreamtest.txt");
		BufferedOutputStream bos=new BufferedOutputStream(os);
		
		String str="hello world this is a bufferedOutputStream test!";
		bos.write(str.getBytes());
		
		//bos.flush();	
		bos.close();//看上面的注释
		
	}
	
}
