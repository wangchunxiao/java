package com.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetInputSentence
{
	String sentence;
	static final int LENGTH_ARRAY=10;//默认不超过10个子句
	public void getSentence() throws Exception
	{
		InputStreamReader isr=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(isr);
		sentence=br.readLine();
	}
	public String[] segmentSentenceIntoString(String sen)
	{
		 String[] array=new String[LENGTH_ARRAY];
		 Pattern p=Pattern.compile("([\u4e00-\u9fa5]+)([^\u4e00-\u9fa5]*)");
		 Matcher m=p.matcher(sen);
		 int i=0;
		 while(m.find())
		 {
			 array[i++]=m.group(1);
			 array[i++]=m.group(2);
		 }
		 return array;
	}
}
