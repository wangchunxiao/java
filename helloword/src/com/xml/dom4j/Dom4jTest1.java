/*
 * ʹ��Dom4j������һ��XML�ļ�
 * 1.ע��jdom��dom4j��һ������jdom������ʲô��newһ�����󣬶�dom4j������DocumentHelper���������������element��document
 * 2.jdom��dom4j��������������Ƕ���dom4j��˵����ֻ���ַ�����ʱ����Ҫˢ�º͹ر��ַ���
 * 3.��io���ֽ������ַ�����ÿһ������buffereed��Ӧ������Ҳ���Ǵ����������ֻ���ֽ�������ȷ���Ǹ�������������OutputStream
 *   ����InputStream�����ַ�����û��˵�ǹ�����������Ҳ������writer��reader��
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
		
		//��һ�ַ�ʽ����һ��document
//		Document document=DocumentHelper.createDocument();
//		Element root=DocumentHelper.createElement("student");
//		document.setRootElement(root);
		
		//�ڶ��ַ�ʽ����һ��document
		Element root=DocumentHelper.createElement("student");
		Document document=DocumentHelper.createDocument(root);
		
		root.addAttribute("attrName1","attrValue1");
		
		//���add�ȿ���ֱ�����һ��element��ĳ��Ԫ�ػ��������Ԫ�ص����ã����ڼ�������������ʹ����
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
		writer3.close();//�ⲽ�����а�������flush����close��Ҫ��Ȼ�ǿհ׵�
	}
}
