/*
 * 1.测试URL的小程序
 * 2.在控制台输出的都是类型的toString方法，我们下面的那个输出byte[] buffer的buffer的时候，就是输出了get.class()+"@"+hashcode
 *   要将byte[]转化为string，调用String的构造函数
 * 3.在URL对象中    url.openStream()=url.openConnection().getstreamInputstream()
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
				//System.out.println(buffer);//注意这里看上面的注释
				System.out.println( new String(buffer));
				os.write(buffer);
			}
			
			is.close();
			os.close();
			
		} catch (MalformedURLException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}