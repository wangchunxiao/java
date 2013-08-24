package com.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputOutputTest
{
	public static void main(String[] args) throws Exception
	{
		InputStreamReader is=new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(is);

		String str;
		while(null!=(str=br.readLine()))
		{
				System.out.println(str);
		}
		br.close();
	}
}
