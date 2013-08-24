package com.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class EventTest2 implements MouseListener,MouseMotionListener
{

	private Frame frame;
	private Button b1;
	private TextField textField;
	
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		// TODO �Զ����ɵķ������
		String str="x=" + e.getX() + ",y=" + e.getY();
		this.textField.setText(str);
		System.out.println(str);
	}


	@Override
	public void mouseMoved(MouseEvent e)
	{
		// TODO �Զ����ɵķ������
		String str="������ƶ�";
		this.textField.setText(str);
		System.out.println(str);
	}


	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO �Զ����ɵķ������
		
	}


	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO �Զ����ɵķ������
		
	}


	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO �Զ����ɵķ������
		
	}


	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO �Զ����ɵķ������
		
	}


	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO �Զ����ɵķ������
		String str="����Ƴ���";
		System.out.println(str);
		this.textField.setText(str);
	}


	public void display()
	{
		frame =new Frame("test");
		textField=new TextField(40);
		b1=new Button("b1");
		frame.add(textField,BorderLayout.SOUTH);
		frame.add(new Label("click"),BorderLayout.CENTER);
		frame.add(b1,BorderLayout.NORTH);
		
		frame.setSize(400,400);
		frame.pack();
		frame.setVisible(true);
		frame.addMouseMotionListener(this);//�����this����ָ��������������¼���Ӧ
		frame.addMouseListener(this);
		frame.addMouseMotionListener(new MotinoTest());
	}
	
	public static void main(String[] args)
	{
		EventTest2 eventTest=new EventTest2();
		eventTest.display();
	}
}


class MotinoTest implements MouseMotionListener
{

	@Override
	public void mouseDragged(MouseEvent e)
	{
		// TODO �Զ����ɵķ������
		String str="x=" + e.getX() + ",y=" + e.getY();
		System.out.println(str+"new object");
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		// TODO �Զ����ɵķ������
		
	}

}
