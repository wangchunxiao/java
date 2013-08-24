/*
 * 1内部类分为四种，常用的是匿名内部类，就是我们下面声明的那个AddActionListener里面传的那个参数，ActionListener
 *  是个接口，显然不是实例化ActionListener，而是new了一个继承或实现了ActionListener的子类的对象，这个子类就是内部类
 * 2.静态内部类，和成员内部类
 * 3.静态的只能访问静态的，非静态的什么都可以访问
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
		jframe = new JFrame("这是一个匿名内部类和swing的测试");
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
						.println("这个不用重新定义类了，只需要我们顶一个匿名内部类来实现actionPerformed");
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
