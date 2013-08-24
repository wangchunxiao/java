/*
 * 1.JAVA�����ļ���·������FILE����
 * 2.FIle�Ĺ��캯����ֻҪƴ�������ܹ���һ��·��+�ļ��Ϳ��Դ���������·���������Ѿ����ڵ�
 * 3.·�����ļ�����һ����һ��ʼ��ʼ����ʱ��ûû�д��������ǵ�����Ӧ�ķ������еĴ�����createNewFile()
 *   ��mkdir(),mkdirs()��mkdir(),mkdirs()��������������ǰ��ֻ����һ��û�д�����·�������������������ж��δ������·��
 * 4.������ʽ[.]�����Ǳ�ʾ��.����ַ�
 * 5.���һ���������˲���ģʽ�������ڲ����������ʽ���úÿ���
 * */
package com.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileTest
{
	public static void main(String[] args) throws IOException
	{
		File file1 = new File("F:/java ����/helloword/src/com/io");
		File file2 = new File(file1, "fileTestCreate.txt");// �������ﻹû�д�����
															// File���и���ʽ�Ǵ���

		File file3 = new File("F:/java ����/helloword/src/com/io/mkdir create");
		File file4 = new File(
				"F:/java ����/helloword/src/com/io/mkdirs create/abc");
		System.out.println(file2.createNewFile());
		System.out.println("-------------------------");
		System.out.println(file3.mkdir());
		System.out.println(file4.mkdirs());
		System.out.println("-------------------------");

		File file5 = new File("F:/java ����/helloword/src/com/io");
		String[] names = file5.list();
		for (String name : names)
		{
			System.out.println(name);
		}
		System.out.println("-------------------------");

		for (String name : names)
		{
			if (name.matches(".+\\.java"))
				System.out.println(name);
		}
		System.out.println("-------------------------");

		String[] namesFilter = file5.list(new FilenameFilter()
		{

			@Override
			public boolean accept(File dir, String name)
			{
				if(name.matches(".+\\.java$"))
					return true;
				else
					return false;
			}
			
		});//�����������˲���ģʽ�������ڲ��࣬��������ʽ
		for(String name:namesFilter)
		{
			System.out.println(name);
		}

		/*
		
		
		*/
	}
}
