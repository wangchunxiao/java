package auto_aubstract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import kevin.zhang.NLPIR;

public class SentenceNew extends Sentence
{
	public HashSet<String> stopWord;
	public double averageCharacterInSentence=0.0;
	public HashMap<String,Double> wtkOfSent4Word;//统计最后的wtk
	public int T;//篇章方程中的系数
	
	
	public SentenceNew(String sentence,String stopWordFilename) throws IOException
	{
		super(sentence);
		StopWord sw=new StopWord(stopWordFilename);
		this.stopWord=sw.getStopWord();
		this.wtkOfSent4Word=new HashMap<String,Double>();
	}
	
	
	@Override
	public void setSet() throws UnsupportedEncodingException
	{
		if ("".equals(sentence)) { return; }

		if (strArray == null)
		{
			setStringArray();

			if (strArray == null)
				return;
			else
				process();
		
		}
		else
		{
			process();
		}
	}
	
	//判断词语结果是否在停用词表中
	public boolean isInWordStop(String word)
	{
		if(this.stopWord.contains(word))
			return true;
		else 
			return false;
	}
	
	//完成向统计的map中添加词语并技术的
	public void addInMap(String str,HashMap<String,Integer>map)
	{
		if(map.containsKey(str))
		{
			int i=map.get(str).intValue();
			i++;
			map.put(str,i);
		}
		else
			map.put(str,1);
	}
	
	//输出map中的内容，用于测试
	public <E> void output(HashMap<String,E>map)
	{
		for(Map.Entry<String,E> entry:map.entrySet())
		{
			System.out.println(entry.getKey()+"        "+entry.getValue());
		}
	}
	
	
	
	
	//将重复代码提出去来，完成统计分词，去停用词，计算自信息功能
	public void process() throws UnsupportedEncodingException
	{
		int length = strArray.length;
		int times = 0;
		TreeSet<Map.Entry<String, Double>> set = new TreeSet<Map.Entry<String, Double>>(
				new Comparator<Map.Entry<String, Double>>()
				{
					@Override
					public int compare(Entry<String, Double> o1,
							Entry<String, Double> o2)
					{
						return o2.getValue().compareTo(o1.getValue());
					}
				});// 直接是句子，句子权重的map
		HashMap<String, Integer> mapint = new HashMap<String, Integer>();// 所有的词和次数的map
		HashMap<String, Double> mapdouble = new HashMap<String, Double>();// 词项的频率(log 自信息使用)
		HashMap<String, Double> wordFre=new HashMap<String,Double>();
 
		HashMap<String, Double> map = new HashMap<String, Double>();// 这是用句子做key，信息熵的和做value的map,准备转化为TreeSet
		String[] splitArray = new String[length];

		HashMap<String,Integer> numOfSent4Word=new HashMap<String,Integer>();//统计词语在多个少句子中出现
		HashMap<String,Double> preOfSent4Word=new HashMap<String, Double>();//统计每个词语在全文中出现的句子数量与总体句子数量的比重
		HashMap<String,Double> tempWtkOfSent4Word=new HashMap<String, Double>();//计算未归一化的wtkOfSent4Word
		
		
		NLPIR testNLPIR = new NLPIR();
		String argu = "";
		if (testNLPIR.NLPIR_Init(argu.getBytes("GB2312"), 1) == false)
		{
			System.out.println("Init Fail!");
			return;
		}

		for (int i = 0; i < strArray.length; i++)
		{
			String str = strArray[i];
			byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(
					str.getBytes("GB2312"), 0);
			String temp = new String(nativeBytes, 0,
					nativeBytes.length, "UTF-8");

			splitArray[i] = temp;// 将分词的结果放入到一个数组中，方便之后计算每句话的信息熵

			String[] result = temp.split(" ");

			HashSet<String> setTemp=new HashSet<String>();//一句话中若出现了某个词语，则只做一次处理
			
			for (String s1 : result)
			{
				if(!isInWordStop(s1))
				{
					times++;
					addInMap(s1,mapint);
					if(!setTemp.contains(s1))
					{
						addInMap(s1,numOfSent4Word);
						setTemp.add(s1);
					}
				}
			}
		}
		testNLPIR.NLPIR_Exit();

		// 取得每个词语的词项频率(自信息用的和出现概率成反比)
		for (Map.Entry<String, Integer> map_entry : mapint.entrySet())
		{
			String word = map_entry.getKey();
			int wordtimes = map_entry.getValue().intValue();
			double frequency = (double) wordtimes / times;
			
			wordFre.put(word,frequency);
			
			double logFrequency = (double) Math.log10(frequency);
			double selfInformation = (-1) * logFrequency;
			mapdouble.put(word, selfInformation);
		}
		
		//计算词语的出现句子的频率
		for(Map.Entry<String,Integer>map_entry:numOfSent4Word.entrySet())
		{
			String word=map_entry.getKey();
			int senTimes=map_entry.getValue().intValue();
			double frequency=(double)senTimes/length;
			preOfSent4Word.put(word,frequency);
		}
		

		// 计算自信息并且添加到TreeMap中
		for (int i = 0; i < length; i++)
		{
			String splitstr = splitArray[i];
			double sumSelfInfomation = 0.0;
			String[] result = splitstr.split(" ");
			for (String s2 : result)
			{
				if(!isInWordStop(s2))
				{
					sumSelfInfomation += mapdouble.get(s2);
				}
			}
			map.put(strArray[i], sumSelfInfomation);
		}

		set.addAll(map.entrySet());
		this.set = set;
		
		
		/****************计算文档中句子的词语数量的平均值 去停用词之后*********/
		this.averageCharacterInSentence=(double)times/length;
		
		
		
		/*********************计算wtk*********************/
		//计算未归一化的wtk
		double wtkNomorlize=0.0;
		for(Entry<String, Double> map_entry:preOfSent4Word.entrySet())
		{
			String word=map_entry.getKey();
			double pre=map_entry.getValue();//   pre=nk/N
			double tfk=wordFre.get(word);
			double wtk=tfk*Math.log(pre+Math.E);
			tempWtkOfSent4Word.put(word,wtk);
			wtkNomorlize+=pre*pre*wtk*wtk;
		}
		wtkNomorlize=Math.sqrt(wtkNomorlize);
		//归一化wtk
		for(Entry<String, Double> map_entry:tempWtkOfSent4Word.entrySet())
		{
			String word=map_entry.getKey();
			double wtk_temp=map_entry.getValue();
			double wtk=wtk_temp/wtkNomorlize;
			wtkOfSent4Word.put(word,wtk);
		}
		
		
		
		
		output(wordFre);
		System.out.println("---------------------------------------------------");
		output(numOfSent4Word);
		System.out.println("---------------------------------------------------");
		output(preOfSent4Word);
		System.out.println("---------------------------------------------------");
		System.out.println(wtkNomorlize);
		System.out.println("---------------------------------------------------");
		output(wtkOfSent4Word);
		System.out.println("---------------------------------------------------");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException
	{
		/*
		File file=new File("摘要test2.txt");
		Long length=file.length();
		
		FileInputStream fis=new FileInputStream(file);
		
	    byte[] contentByte=new byte[length.intValue()];
		
	    fis.read(contentByte,0,length.intValue());
	    
	    String input=new String(contentByte);
	 
	    fis.close();
	    
	    */
		String input="这一周真是繁忙的一周。我们都必须不停的努力才行！一周的工作终于结束了";
	    SentenceNew sentenceNew=new SentenceNew(input,"中文停用词表.txt");
	    
	    sentenceNew.setSet();
	    
	    System.out.println(sentenceNew.averageCharacterInSentence);
	}
}
