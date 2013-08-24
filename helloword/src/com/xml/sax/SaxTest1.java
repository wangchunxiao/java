/*
 * 使用SAX的方法来处理XML文件
 * */

package com.xml.sax;



import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxTest1
{
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
	{
		//step1 生成SAX解析器工厂
		SAXParserFactory factory=SAXParserFactory.newInstance();
		System.out.println(factory.getClass().getName());
		
		//step2 SAX解析器工厂生产产品
		SAXParser parser = factory.newSAXParser();
		System.out.println(parser.getClass().getName());
		
		//step3 SAX解析器解析文件
		parser.parse(new File("student.xml"),new MyHandler());
	}
	
}

class MyHandler extends DefaultHandler
{
	@Override
	public void startDocument() throws SAXException
	{
		System.out.println("start document");
	}
	
	@Override
	public void endDocument() throws SAXException
	{
		System.out.println("end document");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException
	{
		System.out.println("start element");
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException
	{
		System.out.println("character");
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException
	{
		System.out.println("end element");
	}
	
}	