/*
 * 1.jdom��ȡ���е���Ԫ����getchildren(),��DOM4j����elements()
 * 2.jdom��ȡXMLʹ��SAX��������SAXBublider,dom4j����SAXReader,Ȼ���Ƿ��ظ��ڵ�Document
 * 3.jdom��ȡXMLʹ��DOM����Ҳ����DOMBulider,dom4j����DOMReader,���Ƕ��Ǵ���ԭ��JAXP������Document�� 
 *   ת��Ϊjdom����dom4j��Document
 * 4.ע������Documentʱ��һ��Ҫ����������ñ�����֪�����ĸ�Document
 * */

package com.xml.jdom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.xml.sax.SAXException;



public class JDomTest2
{
	public static void main(String[] args) throws JDOMException, IOException, ParserConfigurationException, SAXException
	{
		SAXBuilder bulider=new SAXBuilder();
		Document document=bulider.build(new File("jdomTest1.xml"));
		
		Element root=document.getRootElement();
		System.out.print(root.getName() + root.getText());

		
		List list=root.getChild("root�µ�һ��").getAttributes();
		for(int i=0;i<list.size();i++)
		{
			Attribute attr=(Attribute)list.get(i);
			System.out.println(attr.getName()+"="+attr.getValue());
		}
		
		Element element=root.getChild("root�µ�һ��");
		if(null!=element)
		{
			element.removeChild("��ʽtest");
		}
		
		XMLOutputter out=new XMLOutputter();
		out.output(document,new FileOutputStream("jdomTest2.xml"));
		System.out.println("-------------------------------------------------");
		
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		DocumentBuilder db=dbf.newDocumentBuilder();
		org.w3c.dom.Document documentJAXP=db.parse(new File("jdomTest1.xml"));
		
		
		DOMBuilder builder2=new DOMBuilder();
		Document documentJDOM=builder2.build(documentJAXP);
		System.out.println(documentJDOM.getRootElement().getName());
		
		
		
	}
}