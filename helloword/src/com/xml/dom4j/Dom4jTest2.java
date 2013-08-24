/*
 * 1.jdom��ȡ���е���Ԫ����getchildren(),��DOM4j����elements()
 * 2.jdom��ȡXMLʹ��SAX��������SAXBublider,dom4j����SAXReader,Ȼ���Ƿ��ظ��ڵ�Document
 * 3.jdom��ȡXMLʹ��DOM����Ҳ����DOMBulider,dom4j����DOMReader,���Ƕ��Ǵ���ԭ��JAXP������Document�� 
 *   ת��Ϊjdom����dom4j��Document
 * 4.ע������Documentʱ��һ��Ҫ����������ñ�����֪�����ĸ�Document
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
		Document document=reader.read(new File("dom4jTest1.xml"));//��ȡ���ڵ�
		
		Element root=document.getRootElement();//��ȡ��Ԫ�ؽڵ�
		
		System.out.println(root.getName());
		
		//һ�ֻ����Ԫ�صķ���
		List elementsList=root.elements();
		System.out.println(elementsList.size());
		
		Element first=root.element("hello");
		Attribute attr=first.attribute("attrHello");
		System.out.println(attr.getName()+" = "+attr.getValue());
		
		//��һ�л����Ԫ�ر����ķ���
		for(Iterator iter=root.elementIterator();iter.hasNext();)
		{
			Element e=(Element)iter.next();
			System.out.println(e.getName());
		}
		
		System.out.println("-------------------------------------------------");
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		
		//ע������������document�����Ǳ���Ҫ������һ�����ϰ���
		org.w3c.dom.Document documentJAXP=db.parse(new File("dom4jTest1.xml"));
		
		DOMReader reader2=new DOMReader();
		Document documentDOM4J=reader2.read(documentJAXP);
		
		Element root2=documentDOM4J.getRootElement();
		System.out.println(root2.getName());
		
	}
}
