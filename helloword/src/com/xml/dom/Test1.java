/*
 * 1.我们用DOM来处理XML，这个是基于简单工厂设计模式的，
 * 2.发现我们获得了解析器对象开始解析一个XML并获得Document对象的时候，不用逐层解析，比如这个例子中我们先是获得person的
 *   NodeList,这个是教程来的，后来我们直接用了person中的某个子对象作为document的getElementsByTagName方法的参数，同样
 *   的获得了都是NAME这个Tag的NodeList,看我们下面的那个例子
 * 3.Document和Element都是实现了Node这个接口,而它们都有一个方法是getElementsByTagName,而Node有个方法就是getNodeValue
 * 4.getNodeValue直接获取元素的值都是返回的null,即使是<name>liran</name>也不行，一定要是先getFirstChild().getNodeValue()
 * */
package com.xml.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test1
{
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
	{
		//step1 我们来获得DOM解析器工厂(工厂的作用是创建具体的解析器)
		DocumentBuilderFactory factor=DocumentBuilderFactory.newInstance();
		
		//step2 获得具体的DOM解析器
		DocumentBuilder builder=factor.newDocumentBuilder();
		
		//step3 解析一个XML,获得Document对象(根节点)
		Document document=builder.parse(new File("candidate.xml"));
		
		
		NodeList list=document.getElementsByTagName("PERSON");
	
		for(int i=0;i<list.getLength();i++)
		{
			Element element=(Element)list.item(i);//这里需要有向下类型转换
			
			//下面我们输出每一个person的内容
			
			String content=element.getElementsByTagName("NAME").item(0).getFirstChild().getNodeValue();
			System.out.println("name: " +content);
			
			content=element.getElementsByTagName("ADDRESS").item(0).getFirstChild().getNodeValue();
			System.out.println("address: "+content);
			
			System.out.println("----------------------------------------");
		}
		
		NodeList list1=document.getElementsByTagName("NAME");
		for(int i=0;i<list1.getLength();i++)
		{
			System.out.println(list1.item(i).getFirstChild().getNodeValue());
		}
	}
}
