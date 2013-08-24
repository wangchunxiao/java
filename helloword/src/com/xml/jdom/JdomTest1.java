/*
 * 1.这个例子告诉我们如何使用外部的jar文件，JDom就是这样的文件，第三方的文件，buildpath->添加外部jar文件
 * 2.alt+shift+r来修改变量名称，一个修改了，所有的都修改了
 * 3.这个setText和addContent还有顺序的关系，下面看看
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
		
		Comment comment=new Comment("这是一个注释");
		document.addContent(comment);
		
		Element e1=new Element("root下第一层");
		
		//第一种添加属性的方法
		e1.setAttribute("attrName1","attrValue1");
		
		//第二种添加属性的方法
		Attribute attr=new Attribute("attrName2","attrValue2");
		e1.setAttribute(attr);
		
		//root.addContent(e1);
		root.setText("我们添加内容");
		root.addContent(e1);
		
		//一种方法链的编程风格
		e1.addContent(new Element("链式test").setAttribute("hello","hello").setAttribute(new Attribute("world","world")).setText("添加element内容"));
		
		//输出XML到本地文件，注意这里用的是XMLOutputter而不是DOMOutputter
	    //同时我们可以自己设置xml输出的格式
		Format format=Format.getPrettyFormat();
		format.setIndent("    ");//indent是缩进的意思
		XMLOutputter out=new XMLOutputter(format);
	    out.output(document,new FileOutputStream("jdomTest1.xml"));
	}
}
