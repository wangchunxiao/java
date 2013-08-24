/*
 * 1.frame��windowĬ�ϵĶ���BorderLayout,panelĬ�ϵ���FlowLayout,����֮�������GridLayout������������ҳ�沼����
 * 2.BorderLayout�Ƿֶ��������еģ�����Ļ����λ�ò��䣬��FlowLayout��Ĭ�ϵ�ѡȡ��ѵ�����ʽ��������
 * 3.panel�����Լ���ʾ������ӵ������������У���Ҳ������
 * 4.panel�ӵ������������У����Լ�����������������Լ��Ĳ��ֹ�����
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
		frame=new Frame("����һ�����ֲ��ֲ���");
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
		panel.add(button4,BorderLayout.NORTH);//�����������,��Ϊ���õ���FlowLayout������panelĬ��Ҳ��FlowLayout
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
