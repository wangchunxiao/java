/*
 * 1�ڲ����Ϊ���֣����õ��������ڲ��࣬�������������������Ǹ�AddActionListener���洫���Ǹ�������ActionListener
 *  �Ǹ��ӿڣ���Ȼ����ʵ����ActionListener������new��һ���̳л�ʵ����ActionListener������Ķ��������������ڲ���
 * 2.��̬�ڲ��࣬�ͳ�Ա�ڲ���
 * 3.��̬��ֻ�ܷ��ʾ�̬�ģ��Ǿ�̬��ʲô�����Է���
 * */

package com.innerclass;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AnnoymousInner
{
	private JFrame jframe;
	private JButton jbutton;

	public void display()
	{
		jframe = new JFrame("����һ�������ڲ����swing�Ĳ���");
		jbutton = new JButton("click");
		jframe.getContentPane().add(jbutton, BorderLayout.NORTH);

		jframe.setVisible(true);
		jframe.setSize(200, 200);

		jbutton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				System.out
						.println("����������¶������ˣ�ֻ��Ҫ���Ƕ�һ�������ڲ�����ʵ��actionPerformed");
			}
		});
		jframe.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosed(WindowEvent e)
			{
				System.exit(0);
				//super.windowClosed(e);
			}
		});
	}
	public  static void main(String args[])
	{
		AnnoymousInner annoymousTest =new AnnoymousInner();
		annoymousTest.display();
	}
}
