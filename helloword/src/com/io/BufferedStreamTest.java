/*
 * 1.ʹ��BufferedOutputStream��ʱ��Ҫע��������Ƚ��ֽڴ����ڴ滺�����������close�򲻻Ὣ�������Ķ���������ļ���
 *   ���Ե���flush������ʾ��ˢ�£�������������ֽڵ��ļ�
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
		bos.close();//�������ע��
		
	}
	
}
