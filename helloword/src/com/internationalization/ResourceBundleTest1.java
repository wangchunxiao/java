/*这个事例用来测试我们的ResourceBundle抽象类的使用，
 * 1.注意basename就是我们在properties文件中，_en_US前面的字符串
 * 2.如果我们定义一个basename.properties文件，那么就是只要找不到我们的选取的locale，就会找这个，相当于是默认的
 * */
package com.internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleTest1
{
	public static void main(String[] args)
	{
		ResourceBundle resourceBundle=ResourceBundle.getBundle("liran");
		//System.out.println(resourceBundle);
		
		String result=resourceBundle.getString("hello");
		System.out.println(result);
		
		System.out.println("-----------------------------------------------");
		
		resourceBundle=ResourceBundle.getBundle("liran",Locale.US);
		result=resourceBundle.getString("hello");
		System.out.println(result);
	}
}
