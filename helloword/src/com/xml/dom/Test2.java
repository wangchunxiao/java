/*
 * 1.通过下面我们那个getChildNodes()我们可以看出，虽然只有三个节点，但是个数是7，也就是说那个空格也被算作是孩子了
 * 2.之前的那个我们直接对element进行getNodeName()操作，则得到的是null,我们可以通过那个看jdk的文档来获得这个值的来源
 *   在Node下面，下面这个例子中我们的getTagName和getNodeName得到一样的结果也可以这样得到原因(element元素这样规定)
 * 3.Node中的getNodeType方法，是返回的Type的short值，我们可以查看文档来确定这个值到底是哪个东西
 * 4.对于Attr这个对象，它是从Node派生来的,它的NodeName就是属性的名字，它的NodeValue就是属性的值
 *   对于Element对象，它的NodeName就是TagName,它的value就是null,我们要通过其他的方法获得element的元素的值  getFirstChile().getNodeValue
 *   这些都是在Node的那个表中有说明的，可以查看文档
 * */

package com.xml.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test2
{
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document doc=db.parse(new File("student.xml"));
		
		System.out.println(doc.getXmlEncoding());
		System.out.println(doc.getXmlVersion());
		System.out.println(doc.getXmlStandalone());
		System.out.println("----------------------------------------------");
		
		//获得根元素节点
		Element root=doc.getDocumentElement();
		
		System.out.println(root.getTagName());
		System.out.println(root.getNodeName());//这两个一样可以看上面的注释
		
		NodeList list=root.getChildNodes();
		System.out.println(list.getLength());
		for(int i=0;i<list.getLength();i++)
		{
			System.out.println(list.item(i).getNodeName());
		}
		System.out.println("----------------------------------------------");
		
		for(int i=0;i<list.getLength();i++)
		{
			Node node=list.item(i);
			System.out.println(node.getNodeType()+"   "+node.getNodeValue());
		}
		System.out.println("----------------------------------------------");
		for(int i=0;i<list.getLength();i++)
		{
			Node node=list.item(i);
			System.out.println(node.getTextContent());
		}
		System.out.println("----------------------------------------------");
		
		//注意list和list2可不是一个NodeList 前者是我们通过root做getChildNodes()，后者是root做getElementsByTagName()操作
		NodeList list2=root.getElementsByTagName("学生");
		for(int i=0;i<list2.getLength();i++)
		{
			//NamedNodeMap nnm=list.item(i).getAttributes();
			NamedNodeMap nnm=list2.item(i).getAttributes();
			//这里一开始我是list.iter(i).getAttrbutes()，则由Node的那个表格我们可以看出只有Element用
			//getAttrbutes()方法才能返回NamedNodeMap，其他都返回null,这也是为什么一开始出现内存空访问的原因
			String attrName=nnm.item(0).getNodeName();
			String attrValue=nnm.item(0).getNodeValue();
			System.out.println(attrName+ "=" +attrValue);
		}
		System.out.println("----------------------------------------------");
	}
}
