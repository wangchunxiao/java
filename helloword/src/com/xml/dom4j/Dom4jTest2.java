/*
 * 1.jdom获取所有的子元素是getchildren(),而DOM4j是用elements()
 * 2.jdom读取XML使用SAX方法叫做SAXBublider,dom4j叫做SAXReader,然后都是返回根节点Document
 * 3.jdom读取XML使用DOM方法也叫所DOMBulider,dom4j叫做DOMReader,它们都是传入原来JAXP方法的Document并 
 *   转化为jdom或者dom4j的Document
 * 4.注意两个Document时候，一个要加入包名已让编译器知道是哪个Document
 * */
package com.xml.dom4j;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

public class Dom4jTest2
{
	public static void main(String[] args) throws DocumentException, ParserConfigurationException, SAXException, IOException
	{
		SAXReader reader=new SAXReader();
		Document document=reader.read(new File("dom4jTest1.xml"));//获取根节点
		
		Element root=document.getRootElement();//获取根元素节点
		
		System.out.println(root.getName());
		
		//一种获得子元素的方法
		List elementsList=root.elements();
		System.out.println(elementsList.size());
		
		Element first=root.element("hello");
		Attribute attr=first.attribute("attrHello");
		System.out.println(attr.getName()+" = "+attr.getValue());
		
		//另一中获得子元素遍历的方法
		for(Iterator iter=root.elementIterator();iter.hasNext();)
		{
			Element e=(Element)iter.next();
			System.out.println(e.getName());
		}
		
		System.out.println("-------------------------------------------------");
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		
		//注意这里有两个document，我们必须要给其中一个加上包名
		org.w3c.dom.Document documentJAXP=db.parse(new File("dom4jTest1.xml"));
		
		DOMReader reader2=new DOMReader();
		Document documentDOM4J=reader2.read(documentJAXP);
		
		Element root2=documentDOM4J.getRootElement();
		System.out.println(root2.getName());
		
	}
}
