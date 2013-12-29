import java.nio.charset.Charset;

import kevin.zhang.NLPIR;

public class TestNLPIR {

	public static void main(String[] args) throws Exception
	{
		try
		{
			String sInput = "�Ż�ƽ�Ƴ���NLPIR�ִ�ϵͳ������ICTCLAS2013�������´�ʶ�𡢹ؼ�����ȡ��΢���ִʹ��ܡ�";

			//����Ӧ�ִ�
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

			System.out.println("�ִʽ��Ϊ�� " + t);
			System.out.println("--------------------------------");
			System.out.println("�ִʽ��Ϊ�� " + nativeStr);

	
			testNLPIR.NLPIR_Exit();
		}
		catch (Exception ex)
		{
		} 
}
}
 
