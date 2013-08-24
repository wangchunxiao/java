package com.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class ReadLineTest
{
	public static void main(String[] args) throws Exception
	{
		InputStreamReader isr=new InputStreamReader(new FileInputStream("google.txt"));
		BufferedReader br=new BufferedReader(isr);
		
		String str;
		//String strmatch="[\u4e00-\u9fa5]";
		HashSet<String> set=new HashSet<String>();
		int large_length=0;
		while(null!=(str=br.readLine()))
		{
			//System.out.println(str);
			//String[] result=str.split("\t");\\�����ѹ���������,��\t��Ϊ�ָ���
			String[] result=str.split(" ");
			set.add(result[0]);
			large_length=(result[0].length()>large_length)? (result[0].length()):large_length;
		}
		br.close();
       
		GetInputSentence getobject=new GetInputSentence();
		System.out.println("������ӣ�");
		getobject.getSentence();
		
		while(null!=getobject.sentence)
		{
			String[] sentence_array=getobject.segmentSentenceIntoString(getobject.sentence);
			String result=new String();
			for(int i=0;i<sentence_array.length;i++)
			{
				if(sentence_array[i]!=null)
				{
					//if(i==0)
						result=result+TheForwordTest.ictclas(set,sentence_array[i],large_length);
					//else
						//result=result+ "," + TheForwordTest.ictclas(set,sentence_array[i],large_length);
				}
				else
					break;
			}
			System.out.println(result);
			System.out.println("������ӣ��Կս���");
			getobject.getSentence();
		}
		

		
		
		
       //����ĳ����������ж������Ƿ��Ѿ������ϰ���ָ���ĸ�ʽ�����ڴ棬�жϽ��ΪΪ��
//	   for(Iterator<String> iter=set.iterator();iter.hasNext();)
//       {
//    	   System.out.println(iter.next());
//       }
//       System.out.println(set.size());
//       
//       
//       InputStreamReader isr1=new InputStreamReader(System.in);
//       BufferedReader br1=new BufferedReader(isr1);
//       String input;
//       while(null!=(input=br1.readLine()))
//       {
//    	   System.out.println(set.contains(input));
//       }
//       br1.close();
	}
}

