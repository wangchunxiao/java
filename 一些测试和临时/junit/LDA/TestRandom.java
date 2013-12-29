package LDA;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRandom
{

	public void test()
	{
		for(int i=0;i<100;i++)
			System.out.println((int)(Math.random()*50));
	}
	
	@Test
	public void test1()
	{
		int[][] array=new int[3][3];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.print(array[i][j]+"   ");
			}
			System.out.print("\n");
		}
		System.err.println("Error: the number of iterations should be larger than ");
	}

}
