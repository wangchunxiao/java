package net163;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;


public class Test
{
	public static void main(String[] args) throws Exception
	{
		FileInputStream fis=new FileInputStream(new File("11.txt"));
		
//		byte[] buffer=new byte[100];
//		while(-1!=fis.read(buffer))
//			System.out.println(new String(buffer));
		
		byte[] buffer=IOUtils.toByteArray(fis);
		
		String temp=new String(buffer);
		System.out.println(temp);
		JSONObject json=new JSONObject(temp);
		
		
		fis.close();
	}
}
