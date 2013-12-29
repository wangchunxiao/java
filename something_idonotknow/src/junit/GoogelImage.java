package junit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;


public class GoogelImage
{
	@Test
	public void test() throws Exception
	{
		//URL url=new URL("https://www.googleapis.com/customsearch/v1?key=AIzaSyD_ka3b0ualCAE45QoWFvUo5FWwJ9jMj0g&cx=013036536707430787589:_pqjad5hr1a&q=macbook&alt=json");
		
		URL url=new URL("https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=macbook");	
		
		URLConnection conn=url.openConnection();
		
		InputStream in=conn.getInputStream();
		
		byte[] array=new byte[1000];
		
		StringBuilder builder=new StringBuilder();
		
		while(-1!=(in.read(array,0,1000)))
		{
			String line=new String(array,"utf-8");
			builder.append(line);
		}
		
		//FileOutputStream fos=new FileOutputStream(new File("utf-8.txt"));
		FileWriter fw=new FileWriter(new File("utf-8.txt"));
		fw.write(builder.toString());
		
		fw.close();
		
		System.out.println(builder);
		
	}
	
}
