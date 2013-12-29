package auto_aubstract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class StopWord
{
	private String filename;
	
	public StopWord(String filename)
	{
		this.filename=filename;
	}
	
	HashSet<String>  getStopWord() throws IOException
	{
		HashSet<String> set=new HashSet<String>();
		
		BufferedReader br=new BufferedReader(new FileReader(new File(filename)));
		String input;
		while(null!=(input=br.readLine()))
		{
			set.add(input);
		}
		br.close();
		return set;
	}
	
	public static void main(String[] args) throws IOException
	{
		StopWord sw=new StopWord("中文停用词表.txt");
		HashSet<String> set=sw.getStopWord();
		for(String str:set)
			System.out.println(str);
		System.out.println("----------------------------------------------");
		System.out.println(set.size());
	}
}
