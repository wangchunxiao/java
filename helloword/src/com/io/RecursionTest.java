/*
 * 1.System.out.println(file.delete());//�����ӡfalse��������Ǹ���ע�͵�����£�˵��ֻ��ɾ���������ļ����ļ���
   2.����������ɾ��ĳ���ļ��е�
 * */
package com.io;

import java.io.File;
import java.io.FilenameFilter;

public class RecursionTest
{
	public static void main(String[] args)
	{
		File file = new File("E:/java project/�ݹ�ɾ���ļ��в���");
		
		if (file.isDirectory())
		{
			System.out.println("�ж�·���ɹ�");
			DeleteFileTest.deleteFile(file);
		}
		
		//System.out.println(file.delete());//�����ӡfalse��������Ǹ���ע�͵�����£�˵��ֻ��ɾ���������ļ����ļ���

	}
}

class DeleteFileTest
{
	private File file;

	public DeleteFileTest(File file)
	{
		this.file = file;
	}

	public static void deleteFile(File file)
	{
		// ɾ���ļ���������ļ�
		File filenames[] = file.listFiles(new FilenameFilter()
		{

			@Override
			public boolean accept(File dir, String name)
			{
				if (name.matches(".+\\..+"))
					return true;
				else
					return false;
			}

		});
		for (File filetemp : filenames)
		{
			filetemp.delete();
		}

		// ɾ�����ļ���
		File foldfilenames[] = file.listFiles(new FilenameFilter()
		{

			@Override
			public boolean accept(File dir, String name)
			{
				if (!name.matches(".+\\..+"))
					return true;
				else
					return false;
			}
			
		});
		for (File filetemp : foldfilenames)
		{
			DeleteFileTest.deleteFile(filetemp);
		}
		
		//ɾ���ļ���
		file.delete();
	}
}