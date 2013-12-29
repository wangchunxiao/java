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
		String sInput="而“李毅吧”亦有“百度贴吧卢浮宫”之称，因为很多在网络流传甚广的内涵文都出自“李毅吧”，包括那篇红极一时的《李毅大帝本纪》。而这一次，“屌丝”爆红网络，则是又一次体现出“李毅吧”对网络文化的影响。"+
"丑穷无能但善良：“屌丝”代表了最广大年轻人的面貌";
		
		NLPIR testNLPIR = new NLPIR();
		String argu = "";
		if (testNLPIR.NLPIR_Init(argu.getBytes("GB2312"), 1) == false)
		{
			System.out.println("Init Fail!");
			return;
		}

		
		byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(sInput.getBytes("GB2312"), 0);
		String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "utf-8");

		System.out.println("分词结果为： " + nativeStr);

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
		//初始化分词组件
		String argu1 = "摘要test2.txt";
	
		
		nativeBytes  =testNLPIR.NLPIR_GetFileKeyWords(argu1.getBytes("GB2312"),10,true);
		//如果是处理内存，可以调用testNLPIR.NLPIR_GetKeyWords
		nativeStr = new String(nativeBytes, 0, nativeBytes.length, "utf-8");
		System.out.println("关键词识别结果为： " + nativeStr);
		
		
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
		
		/*测试空的set的size方法返回的是否为0
		Set<String> set=new TreeSet<String>();
		System.out.println(set.size());
		*/
	}

}





