import java.nio.charset.Charset;

import kevin.zhang.NLPIR;

public class TestNLPIR {

	public static void main(String[] args) throws Exception
	{
		try
		{
			String sInput = "张华平推出的NLPIR分词系统，又名ICTCLAS2013，新增新词识别、关键词提取、微博分词功能。";

			//自适应分词
			test(sInput);		
			
		}
		catch (Exception ex)
		{
		} 
	}

	public static void test(String sInput)
	{
		try
		{
			System.out.println(Charset.defaultCharset());
			
			NLPIR testNLPIR = new NLPIR();
	
			String argu = "";
			System.out.println("NLPIR_Init");
			if (testNLPIR.NLPIR_Init(argu.getBytes("GB2312"),1) == false)
			{
				System.out.println("Init Fail!");
				return;
			}
			
			/*
			byte[] ttt=sInput.getBytes("GB2312");
			String tttt=new String(ttt,"GBK");
			*/
			
			System.out.println(sInput);
			byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(sInput.getBytes("GB2312"), 0);
			
			String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "UTF-8");
	
			
			String t=new String(nativeBytes,"GB2312");

			System.out.println("分词结果为： " + t);
			System.out.println("--------------------------------");
			System.out.println("分词结果为： " + nativeStr);

	
			testNLPIR.NLPIR_Exit();
		}
		catch (Exception ex)
		{
		} 
}
}
 
