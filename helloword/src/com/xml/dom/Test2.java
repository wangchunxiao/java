/*
 * 1.ͨ�����������Ǹ�getChildNodes()���ǿ��Կ�������Ȼֻ�������ڵ㣬���Ǹ�����7��Ҳ����˵�Ǹ��ո�Ҳ�������Ǻ�����
 * 2.֮ǰ���Ǹ�����ֱ�Ӷ�element����getNodeName()��������õ�����null,���ǿ���ͨ���Ǹ���jdk���ĵ���������ֵ����Դ
 *   ��Node���棬����������������ǵ�getTagName��getNodeName�õ�һ���Ľ��Ҳ���������õ�ԭ��(elementԪ�������涨)
 * 3.Node�е�getNodeType�������Ƿ��ص�Type��shortֵ�����ǿ��Բ鿴�ĵ���ȷ�����ֵ�������ĸ�����
 * 4.����Attr����������Ǵ�Node��������,����NodeName�������Ե����֣�����NodeValue�������Ե�ֵ
 *   ����Element��������NodeName����TagName,����value����null,����Ҫͨ�������ķ������element��Ԫ�ص�ֵ  getFirstChile().getNodeValue
 *   ��Щ������Node���Ǹ�������˵���ģ����Բ鿴�ĵ�
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
		
		//��ø�Ԫ�ؽڵ�
		Element root=doc.getDocumentElement();
		
		System.out.println(root.getTagName());
		System.out.println(root.getNodeName());//������һ�����Կ������ע��
		
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
		
		//ע��list��list2�ɲ���һ��NodeList ǰ��������ͨ��root��getChildNodes()��������root��getElementsByTagName()����
		NodeList list2=root.getElementsByTagName("ѧ��");
		for(int i=0;i<list2.getLength();i++)
		{
			//NamedNodeMap nnm=list.item(i).getAttributes();
			NamedNodeMap nnm=list2.item(i).getAttributes();
			//����һ��ʼ����list.iter(i).getAttrbutes()������Node���Ǹ�������ǿ��Կ���ֻ��Element��
			//getAttrbutes()�������ܷ���NamedNodeMap������������null,��Ҳ��Ϊʲôһ��ʼ�����ڴ�շ��ʵ�ԭ��
			String attrName=nnm.item(0).getNodeName();
			String attrValue=nnm.item(0).getNodeValue();
			System.out.println(attrName+ "=" +attrValue);
		}
		System.out.println("----------------------------------------------");
	}
}
