package com.xml.sax;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXTest2
{
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
	{
		SAXParserFactory factory=SAXParserFactory.newInstance();
		SAXParser parser=factory.newSAXParser();
		parser.parse(new File("student.xml"),new MyHandler2());
	}
}
class MyHandler2 extends DefaultHandler
{
	private String name;
	private String age;
	private String gender;
	private Stack<String> stack=new Stack<String>();
	
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException
	{
		stack.push(qName);
		
		for(int i=0;i<attributes.getLength();i++)
		{
			String attrName=attributes.getQName(i);
			String attrValue=attributes.getValue(i);
			System.out.println(attrName+"=\""+attrValue+"\" ");
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException
	{
		if("姓名".equals(stack.peek()))
		{
			name=new String(ch,start,length);
		}
		else if("性别".equals(stack.peek()))
		{
			gender=new String(ch,start,length);
		}
		else if("年龄".equals(stack.peek()))
		{
			age=new String(ch,start,length);
		}
		
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException
	{
		stack.pop();
		
		if("学生".equals(qName))
		{
			System.out.println("姓名=" + name);
			System.out.println("性别=" + gender);
			System.out.println("年龄=" + age);
			System.out.println();
		}
		
	}
}