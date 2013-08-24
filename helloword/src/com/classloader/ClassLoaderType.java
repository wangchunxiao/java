/*
 * 有三种类加载器类别，一种是根类加载器的，也就是我们输出为null的这个，还有两种分别是扩展加载器和系统加载器(应用加载器)
 * 用户自定义的类都是java.lang.Classloader的子类
 * 
 * */

package com.classloader;

public class ClassLoaderType 
{
	 public static void main(String[] args) throws Exception
	 {
		 Class<?> classType1=Class.forName("java.lang.String");
		 Class<?> classType2=Class.forName("com.classloader.C");
		 
		 System.out.println(classType1.getName()+"    "+classType1.getClassLoader());
		 System.out.println(classType2.getName()+"    "+classType2.getClassLoader());
	 }
}
class C
{
	
}