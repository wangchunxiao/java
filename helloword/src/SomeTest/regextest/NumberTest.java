package SomeTest.regextest;

public class NumberTest
{
	public static void main(String[] args)
	{
		String str="13A";
		System.out.println(str.matches("[0-9]+"));
		System.out.println(str.matches("\\d+"));
	}
}
