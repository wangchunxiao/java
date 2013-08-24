/*
 * 1.����URL��С����
 * 2.�ڿ���̨����Ķ������͵�toString����������������Ǹ����byte[] buffer��buffer��ʱ�򣬾��������get.class()+"@"+hashcode
 *   Ҫ��byte[]ת��Ϊstring������String�Ĺ��캯��
 * 3.��URL������    url.openStream()=url.openConnection().getstreamInputstream()
 * */

package com.network;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Urltest
{
	public static void main(String[] args)
	{
			Thread1 t1=new Thread1();
			t1.start();
	}
}

class Thread1 extends Thread
{
	@Override
	public void run()
	{
		URL url;
		try
		{
			url = new URL("http://www.sina.com.cn");
			URLConnection conn=url.openConnection();
			InputStream is=conn.getInputStream();
			
			FileOutputStream os=new FileOutputStream("URL-SINATEST.txt");
			byte[] buffer=new byte[2048];
			int length;
			while(-1!=(length=is.read(buffer,0,2048)))
			{
				//System.out.println(buffer);//ע�����￴�����ע��
				System.out.println( new String(buffer));
				os.write(buffer);
			}
			
			is.close();
			os.close();
			
		} catch (MalformedURLException e)
		{
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
}