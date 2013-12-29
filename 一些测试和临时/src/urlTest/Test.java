package urlTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test
{
	public static void main(String[] args) throws IOException
	{
		
		URL myurl=new URL("http://news.sina.com.cn/c/2013-03-26/085626641525.shtml");
		HttpURLConnection huc=(HttpURLConnection) myurl.openConnection();
		
		BufferedReader br=new BufferedReader(new InputStreamReader(huc.getInputStream()));
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("zhaoxiaobao.txt")));
		String line;
		while(null!=(line=br.readLine()))
		{
			String result=new String(line.getBytes(),"gb2312");
			bw.write(result+"\n");
		}
		
		
		br.close();
		bw.close();
		System.out.println("finish");
	}
}
