package com.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Iterator;

public class GoogleDateTest
{
	public static void main(String[] args) throws Exception
	{
		BufferedReader br=new BufferedReader(new FileReader("google.txt"));
		String line;
		HashSet<String> set=new HashSet<String>();
		while(null!=(line=br.readLine()))
		{
			String[] result;
			result=line.split(" ");
			set.add(result[0]);
		}
		for(Iterator<String> iter=set.iterator();iter.hasNext();)
		{
			System.out.println(iter.next());
		}
	}
}
