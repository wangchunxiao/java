/*
 * 1.java的字符流是指以Unicode为编码方式，每次读取一个字符(两个字节)，java在处理字符流的时候，就拿读文件举例
 *   有两个包装，第一次是用InputStreamReader包装InputStream的一个子类对象(FileInputStream对象)，第二次是用
 *   BufferedReader类对象包装InputStreamReader对象
 *   
 * */

package com.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CharacterStreamTest 
{
	public static void main(String[] args) throws Exception
	{
		OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream("OutputStreamWriterTest.txt"));
		BufferedWriter bw=new BufferedWriter(osw);
		bw.write("这是我的一个测试，测试的是看看Java是将字符流包装在字节流里里面！\n");
		bw.write("english and chinese are in the same file!\n");
		bw.close();
		
		
		InputStreamReader isr=new InputStreamReader(new FileInputStream("OutputStreamWriterTest.txt"));
		BufferedReader br=new BufferedReader(isr);
		String result;
		while(null!=(result=br.readLine()))
		{
			System.out.println(result);
		}
		br.close();
	}
}
