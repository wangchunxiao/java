package junit;

import java.util.ArrayList;

import org.junit.Test;

public class testInneric
{

	@Test
	public void test()
	{
		ArrayList<? extends Object> list1=new ArrayList<Object>();
		ArrayList<String> list2=new ArrayList<String>();
		
		System.out.println(list1.getClass());
		System.out.println(list2.getClass());
	}

}
