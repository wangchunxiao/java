package com.collection;
/*
 1.ctrl+shift+o   alt+/ 快捷键  前者是自动导入程序所需的包，后者是在输出的的时候自动填出匹配提示，如main函数只需mai就可以了 
 */
import java.util.ArrayList;  

public class ArrayListTest
{
	public static void main(String[] args)
	{
		ArrayList arraylist=new ArrayList();
		arraylist.add("liran ");
		arraylist.add("want ");
		arraylist.add("to learn JAVA!");
		System.out.println(arraylist.get(0));
	}
	
}
