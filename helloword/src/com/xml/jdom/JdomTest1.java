/*
 * 1.������Ӹ����������ʹ���ⲿ��jar�ļ���JDom�����������ļ������������ļ���buildpath->����ⲿjar�ļ�
 * 2.alt+shift+r���޸ı������ƣ�һ���޸��ˣ����еĶ��޸���
 * 3.���setText��addContent����˳��Ĺ�ϵ�����濴��
 * */

package com.xml.jdom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Attribute;
import org.jdom2.Comment;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;



public class JdomTest1
{
	public static void main(String[] args) throws FileNotFoundException, IOException
	{
		Document document=new Document();
		Element root=new Element("root");
		document.addContent(root);
		
		Comment comment=new Comment("����һ��ע��");
		document.addContent(comment);
		
		Element e1=new Element("root�µ�һ��");
		
		//��һ��������Եķ���
		e1.setAttribute("attrName1","attrValue1");
		
		//�ڶ���������Եķ���
		Attribute attr=new Attribute("attrName2","attrValue2");
		e1.setAttribute(attr);
		
		//root.addContent(e1);
		root.setText("�����������");
		root.addContent(e1);
		
		//һ�ַ������ı�̷��
		e1.addContent(new Element("��ʽtest").setAttribute("hello","hello").setAttribute(new Attribute("world","world")).setText("���element����"));
		
		//���XML�������ļ���ע�������õ���XMLOutputter������DOMOutputter
	    //ͬʱ���ǿ����Լ�����xml����ĸ�ʽ
		Format format=Format.getPrettyFormat();
		format.setIndent("    ");//indent����������˼
		XMLOutputter out=new XMLOutputter(format);
	    out.output(document,new FileOutputStream("jdomTest1.xml"));
	}
}
