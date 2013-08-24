/*
 * 1.������DOM������XML������ǻ��ڼ򵥹������ģʽ�ģ�
 * 2.�������ǻ���˽���������ʼ����һ��XML�����Document�����ʱ�򣬲�������������������������������ǻ��person��
 *   NodeList,����ǽ̳����ģ���������ֱ������person�е�ĳ���Ӷ�����Ϊdocument��getElementsByTagName�����Ĳ�����ͬ��
 *   �Ļ���˶���NAME���Tag��NodeList,������������Ǹ�����
 * 3.Document��Element����ʵ����Node����ӿ�,�����Ƕ���һ��������getElementsByTagName,��Node�и���������getNodeValue
 * 4.getNodeValueֱ�ӻ�ȡԪ�ص�ֵ���Ƿ��ص�null,��ʹ��<name>liran</name>Ҳ���У�һ��Ҫ����getFirstChild().getNodeValue()
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
		//step1 ���������DOM����������(�����������Ǵ�������Ľ�����)
		DocumentBuilderFactory factor=DocumentBuilderFactory.newInstance();
		
		//step2 ��þ����DOM������
		DocumentBuilder builder=factor.newDocumentBuilder();
		
		//step3 ����һ��XML,���Document����(���ڵ�)
		Document document=builder.parse(new File("candidate.xml"));
		
		
		NodeList list=document.getElementsByTagName("PERSON");
	
		for(int i=0;i<list.getLength();i++)
		{
			Element element=(Element)list.item(i);//������Ҫ����������ת��
			
			//�����������ÿһ��person������
			
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
