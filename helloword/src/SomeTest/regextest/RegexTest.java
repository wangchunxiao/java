package SomeTest.regextest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest
{
	public static void main(String[] args)
	{
		String sentence="��Щ��,��а�����һ���߹�@����ʲô��/��";
		Pattern test1=Pattern.compile("[\u4e00-\u9fa5]+");
		Matcher matchtest1=test1.matcher(sentence);
		while(matchtest1.find())
		{
			System.out.println(matchtest1.group());
		}
	}
}
