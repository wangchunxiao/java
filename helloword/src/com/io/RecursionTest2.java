/*
 * 1.程序是用来显示某个文件夹的结构
 * */
package com.io;

import java.io.File;
import java.io.FilenameFilter;

public class RecursionTest2
{
	public static void display(File file,int n)
	{
		if(!file.isDirectory() && !file.isFile())
			return;
		
		String space=new String();
		for(int i=0;i<n;i++)
		{
			space+=" ";
		}
		
		
		
		File[] filenames = file.listFiles(new FilenameFilter()
		{

			@Override
			public boolean accept(File dir, String name)
			{
				if (name.matches(".*\\..*"))
					return true;
				else
					return false;
			}
		});
		for (File f : filenames)
		{
			System.out.println(space +f.getName());
			/*
			if (f.isFile())
			{
				System.out.println(" " + f.getName());
			}
			else
			{
				System.out.println(f.getName());
				display(f);
			}
			*/
		}
		
		File[] filefoldernames=file.listFiles(new FilenameFilter()
		{
			
			@Override
			public boolean accept(File dir, String name)
			{
				if (name.matches(".*\\..*"))
					return false;
				else
					return true;
			}
		});
		for(File f:filefoldernames)
		{
			System.out.println(space+f.getName());
			RecursionTest2.display(f,n+2);
		}
		
	}

	public static void main(String[] args)
	{
		File file = new File("E:/java project/递归删除文件夹测试");
		RecursionTest2.display(file,1);
	}
}
