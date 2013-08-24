package com.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

public class FrameTest1
{
	private Frame frame;//
	private Button button1;
	private Button button2;
	
	public void displayFrame()
	{
		frame=new Frame("这是一个简单的frame");
		button1=new Button("button1");
		button2=new Button("button2");
		
		frame.add(button1);
		frame.add(button2,BorderLayout.NORTH);
		
		//frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		frame.pack();
		
	}
	
	public static void main(String[] args)
	{
		FrameTest1 frameobj=new FrameTest1();
		frameobj.displayFrame();
	}
}
