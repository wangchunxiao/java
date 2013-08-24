/*
 * 使用Dom4j来生成一个XML文件
 * 1.注意jdom和dom4j的一个区别，jdom都是用什么就new一个对象，而dom4j则是用DocumentHelper这个类来帮助生成element和document
 * 2.jdom和dom4j都可以输出，但是对于dom4j来说，当只用字符流的时候，需要刷新和关闭字符流
 * 3.看io的字节流和字符流，每一个都有buffereed对应的流，也就是带缓冲的流，只是字节流是明确了是个过滤流，修饰OutputStream
 *   或者InputStream，而字符流的没有说是过滤流，但是也是修饰writer和reader的
 * */

package com.xml.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Dom4jTest1
{
	public static void main(String[] args) throws IOException
	{
		
		//第一种方式生成一个document
//		Document document=DocumentHelper.createDocument();
//		Element root=DocumentHelper.createElement("student");
//		document.setRootElement(root);
		
		//第二种方式生成一个document
		Element root=DocumentHelper.createElement("student");
		Document document=DocumentHelper.createDocument(root);
		
		root.addAttribute("attrName1","attrValue1");
		
		//这个add既可以直接添加一个element到某个元素还返回这个元素的引用，等于即声明定义了又使用了
		Element hello=root.addElement("hello");
		Element world=root.addElement("world");
		
		hello.addAttribute("attrHello","attrWorld");
		hello.setText("hello");
		world.setText("world");
		
		XMLWriter writer=new XMLWriter();
		writer.write(document);
		
		OutputFormat format=new OutputFormat("   ",true);
		
		XMLWriter writer2=new XMLWriter(new FileOutputStream(new File("dom4jTest1.xml")),format);
		writer2.write(document);
		
		XMLWriter writer3=new XMLWriter(new FileWriter(new File("dom4jTest2.xml")),format);
		writer3.write(document);
		writer3.close();//这步必须有啊，或者flush或者close，要不然是空白的
	}
}
