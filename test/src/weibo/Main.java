package weibo;

import java.io.File;
import java.io.IOException;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		IctAndComputeEntorpy test=new IctAndComputeEntorpy(new File("1.txt"));
		test.IctData();
		System.out.println(test.getWordNums());
		
		test.computeFrequencyAndEntorpy();
		test.outputReuslt(new File("entropy.xls"));
		
		
		
		
		
		IctAndComputeSelfInformation test1=new IctAndComputeSelfInformation(new File("1.txt"));
		test1.IctData();
		System.out.println(test1.getWordNums());
		
		test1.computeFrequencyAndSelfInformation();
		test1.outputReuslt(new File("selfinformation.xls"));
		
	}
}
