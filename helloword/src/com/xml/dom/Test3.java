/*
 * 使用递归解析任意的XML文档
 * 1.注意我们做处理的是每个element，然后对它进行getChildNodes(),对所有的孩子进行判断，是text就输出，是element就递归调用
 * 2.在整个解析的过程中，由于那些空格以及换行也算是孩子，而且还是text，所以我们可以直接输出这些的value，它们就相当于是
 *   控制缩进的空格和换行了，整个解析不需要我们自己输出\n
 * 3.原生数据类型比较用==方法，而对象要用equals方法
 * */
package com.xml.dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test3
{
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException
	{
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		Document document=db.parse(new File("student.xml"));
		
		Element root=document.getDocumentElement();
		readXML(root);
	}
	
	public static void readXML(Element element)
	{
		if(Node.ELEMENT_NODE==element.getNodeType())
		{
			System.out.print("<"+element.getNodeName());
			NamedNodeMap nnm=element.getAttributes();
			if(null!=nnm)
			{
				for (int i = 0; i < nnm.getLength(); i++)
				{
					System.out.print(" "+nnm.item(i).getNodeName() + "=\""
							+ nnm.item(i).getNodeValue() + "\" ");
				}
			}
			System.out.print(">");
			
			NodeList list=element.getChildNodes();
			for(int i=0;i<list.getLength();i++)
			{
				Node node=list.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE)
				{
					readXML((Element)node);
				}
				else if(node.getNodeType()==Node.TEXT_NODE)
				{
					System.out.print(node.getNodeValue());
				}
				else if(node.getNodeType()==Node.COMMENT_NODE)
				{
					Comment comment=(Comment)node;
					System.out.print("<!--"+comment.getData()+"-->");
				}
			}
			System.out.print("</"+element.getNodeName()+">");
		}
	}
}
