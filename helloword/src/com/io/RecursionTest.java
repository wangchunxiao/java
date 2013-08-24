/*
 * 1.System.out.println(file.delete());//这个打印false再上面的那个是注释的情况下，说明只能删除不包含文件的文件夹
   2.程序是用来删除某个文件夹的
 * */
package com.io;

import java.io.File;
import java.io.FilenameFilter;

public class RecursionTest
{
	public static void main(String[] args)
	{
		File file = new File("E:/java project/递归删除文件夹测试");
		
		if (file.isDirectory())
		{
			System.out.println("判断路径成功");
			DeleteFileTest.deleteFile(file);
		}
		
		//System.out.println(file.delete());//这个打印false再上面的那个是注释的情况下，说明只能删除不包含文件的文件夹

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
		// 删除文件夹以外的文件
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

		// 删除子文件夹
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
		
		//删除文件夹
		file.delete();
	}
}