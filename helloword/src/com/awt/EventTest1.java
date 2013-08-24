package com.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class EventTest1
{
	public static void main(String[] args)
	{
		Frame frame=new Frame();
		Button b1=new Button("B1");
		
		frame.add(b1,BorderLayout.CENTER);
		frame.setVisible(true);
		
		b1.addActionListener(new ButtonAction());
		b1.addMouseMotionListener(new ButtonAction());
	}
}

class ButtonAction implements ActionListener,MouseMotionListener
{

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO �Զ����ɵķ������
		System.out.println(e);
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		// TODO �Զ����ɵķ������
		System.out.println("������϶�");
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		// TODO �Զ����ɵķ������
		System.out.println("������ƶ�");
	}
	
}
