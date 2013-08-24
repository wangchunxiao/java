/*
 * 1.JAVA里面文件和路径都是FILE对象
 * 2.FIle的构造函数，只要拼接起来能构成一个路径+文件就可以创建，但是路径必须是已经存在的
 * 3.路径和文件都是一样，一开始初始化的时候都没没有创建，都是调用相应的方法进行的创建，createNewFile()
 *   和mkdir(),mkdirs()。mkdir(),mkdirs()这两个的区别是前者只能有一个没有创建的路径，而后面的这个可以有多层未创建的路径
 * 4.正则表达式[.]好像是表示了.这个字符
 * 5.最后一个例子用了策略模式，匿名内部类和正则表达式，好好看看
 * */
package com.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileTest
{
	public static void main(String[] args) throws IOException
	{
		File file1 = new File("F:/java 工程/helloword/src/com/io");
		File file2 = new File(file1, "fileTestCreate.txt");// 这里这里还没有创建呢
															// File类有个方式是创建

		File file3 = new File("F:/java 工程/helloword/src/com/io/mkdir create");
		File file4 = new File(
				"F:/java 工程/helloword/src/com/io/mkdirs create/abc");
		System.out.println(file2.createNewFile());
		System.out.println("-------------------------");
		System.out.println(file3.mkdir());
		System.out.println(file4.mkdirs());
		System.out.println("-------------------------");

		File file5 = new File("F:/java 工程/helloword/src/com/io");
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
			
		});//这里例子用了策略模式，匿名内部类，和正则表达式
		for(String name:namesFilter)
		{
			System.out.println(name);
		}

		/*
		
		
		*/
	}
}
