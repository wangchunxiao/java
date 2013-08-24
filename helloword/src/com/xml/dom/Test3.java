/*
 * ʹ�õݹ���������XML�ĵ�
 * 1.ע���������������ÿ��element��Ȼ���������getChildNodes(),�����еĺ��ӽ����жϣ���text���������element�͵ݹ����
 * 2.�����������Ĺ����У�������Щ�ո��Լ�����Ҳ���Ǻ��ӣ����һ���text���������ǿ���ֱ�������Щ��value�����Ǿ��൱����
 *   ���������Ŀո�ͻ����ˣ�������������Ҫ�����Լ����\n
 * 3.ԭ���������ͱȽ���==������������Ҫ��equals����
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
