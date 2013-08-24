/*
 * 1.java���ַ�����ָ��UnicodeΪ���뷽ʽ��ÿ�ζ�ȡһ���ַ�(�����ֽ�)��java�ڴ����ַ�����ʱ�򣬾��ö��ļ�����
 *   ��������װ����һ������InputStreamReader��װInputStream��һ���������(FileInputStream����)���ڶ�������
 *   BufferedReader������װInputStreamReader����
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
		bw.write("�����ҵ�һ�����ԣ����Ե��ǿ���Java�ǽ��ַ�����װ���ֽ��������棡\n");
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
