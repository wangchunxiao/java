/*
 * 1.frame和window默认的都是BorderLayout,panel默认的是FlowLayout,还有之中网格的GridLayout，常见的三种页面布局器
 * 2.BorderLayout是分东南西北中的，拉伸的话想对位置不变，而FlowLayout是默认的选取最佳的流方式，拉伸会变
 * 3.panel不会自己显示，必须加到其他的容器中，它也是容器
 * 4.panel加到其他的容器中，可以继续在这个区域内用自己的布局管理器
 * */

package com.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;

public class LayoutTest
{

	private Frame frame;
	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private Button button5;
	private Button button6;
	private Panel panel;
	
	public void display()
	{
		frame=new Frame("这是一个多种布局测试");
		button1=new Button("B1");
		button2=new Button("B2");
		button3=new Button("B3");
		button4=new Button("B4");
		button5=new Button("B5");
		button6=new Button("B6");
		
		frame.setLayout(new BorderLayout());
		frame.add(button1,BorderLayout.NORTH);
		frame.add(button2,BorderLayout.WEST);
		frame.add(button3,BorderLayout.EAST);
		
		panel=new Panel(new FlowLayout());
		panel.add(button4,BorderLayout.NORTH);//这个不起作用,因为设置的是FlowLayout，而且panel默认也是FlowLayout
		panel.add(button5);
		panel.add(button6);
		frame.add(panel,BorderLayout.CENTER);
		
		frame.setSize(200,200);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args)
	{
		LayoutTest layouttest=new LayoutTest();
		layouttest.display();
	}
}
