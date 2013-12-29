package com.liran.network;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestHttpClient
{
	public static void main(String[] args) throws IOException
	{
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://www.sohu.com.cn/");
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		
		//System.out.println(response.getClass());
		
		if (entity != null)
		{
			InputStream instream = entity.getContent();
			try
			{
				byte[] array=new byte[1000];
				ArrayList<String> urlList=new ArrayList<String>();
				Pattern p=Pattern.compile("href\\s*=\\s*(\"[^\"]*\"|[^\\s>])\\s*");
				while(-1!=instream.read(array,0,1000))
				{
					String str=new String(array);
					Matcher m=p.matcher(str);
					while(m.find())
					{
						urlList.add(m.group(1));
					}
				}
				for(String s:urlList)
				{
					System.out.println(s);
				}
				
			} finally
			{
				instream.close();
			}
			
			
		}
	}
	
}