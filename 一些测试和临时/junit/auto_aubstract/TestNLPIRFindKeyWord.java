package auto_aubstract;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kevin.zhang.NLPIR;

import org.junit.Test;

public class TestNLPIRFindKeyWord
{

	@Test
	public void test() throws IOException
	{
		String sInput="��������ɡ����С��ٶ�����¬������֮�ƣ���Ϊ�ܶ�����������������ں��Ķ����ԡ�����ɡ���������ƪ�켫һʱ�ġ������۱��͡�������һ�Σ�����˿���������磬������һ�����ֳ�������ɡ��������Ļ���Ӱ�졣"+
"�������ܵ�����������˿�����������������˵���ò";
		
		NLPIR testNLPIR = new NLPIR();
		String argu = "";
		if (testNLPIR.NLPIR_Init(argu.getBytes("GB2312"), 1) == false)
		{
			System.out.println("Init Fail!");
			return;
		}

		
		byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(sInput.getBytes("GB2312"), 0);
		String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "utf-8");

		System.out.println("�ִʽ��Ϊ�� " + nativeStr);

		/*
		OutputStream out=new BufferedOutputStream(new FileOutputStream(new File("text.TXT")));
		out.write(sInput.getBytes());
		out.close();
		*/
		/*
		Writer out=new BufferedWriter(new FileWriter(new File("text.TXT")));
		out.write(sInput);
		out.close();
		*/
		//��ʼ���ִ����
		String argu1 = "ժҪtest2.txt";
	
		
		nativeBytes  =testNLPIR.NLPIR_GetFileKeyWords(argu1.getBytes("GB2312"),10,true);
		//����Ǵ����ڴ棬���Ե���testNLPIR.NLPIR_GetKeyWords
		nativeStr = new String(nativeBytes, 0, nativeBytes.length, "utf-8");
		System.out.println("�ؼ���ʶ����Ϊ�� " + nativeStr);
		
		
		System.out.println("--------------------------------------------------------");
		
		
		Pattern pattern=Pattern.compile("([^/\\s]+)/(\\d+\\.\\d+)");
		Matcher matcher=pattern.matcher(nativeStr);
		while(matcher.find())
		{
			System.out.println(matcher.group(0));
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println("-----");
		}
		
		/*���Կյ�set��size�������ص��Ƿ�Ϊ0
		Set<String> set=new TreeSet<String>();
		System.out.println(set.size());
		*/
	}

}





