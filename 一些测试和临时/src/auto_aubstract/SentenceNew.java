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
	public HashMap<String,Double> wtkOfSent4Word;//ͳ������wtk
	public int T;//ƪ�·����е�ϵ��
	
	
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
	
	//�жϴ������Ƿ���ͣ�ôʱ���
	public boolean isInWordStop(String word)
	{
		if(this.stopWord.contains(word))
			return true;
		else 
			return false;
	}
	
	//�����ͳ�Ƶ�map����Ӵ��ﲢ������
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
	
	//���map�е����ݣ����ڲ���
	public <E> void output(HashMap<String,E>map)
	{
		for(Map.Entry<String,E> entry:map.entrySet())
		{
			System.out.println(entry.getKey()+"        "+entry.getValue());
		}
	}
	
	
	
	
	//���ظ��������ȥ�������ͳ�Ʒִʣ�ȥͣ�ôʣ���������Ϣ����
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
				});// ֱ���Ǿ��ӣ�����Ȩ�ص�map
		HashMap<String, Integer> mapint = new HashMap<String, Integer>();// ���еĴʺʹ�����map
		HashMap<String, Double> mapdouble = new HashMap<String, Double>();// �����Ƶ��(log ����Ϣʹ��)
		HashMap<String, Double> wordFre=new HashMap<String,Double>();
 
		HashMap<String, Double> map = new HashMap<String, Double>();// �����þ�����key����Ϣ�صĺ���value��map,׼��ת��ΪTreeSet
		String[] splitArray = new String[length];

		HashMap<String,Integer> numOfSent4Word=new HashMap<String,Integer>();//ͳ�ƴ����ڶ���پ����г���
		HashMap<String,Double> preOfSent4Word=new HashMap<String, Double>();//ͳ��ÿ��������ȫ���г��ֵľ���������������������ı���
		HashMap<String,Double> tempWtkOfSent4Word=new HashMap<String, Double>();//����δ��һ����wtkOfSent4Word
		
		
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

			splitArray[i] = temp;// ���ִʵĽ�����뵽һ�������У�����֮�����ÿ�仰����Ϣ��

			String[] result = temp.split(" ");

			HashSet<String> setTemp=new HashSet<String>();//һ�仰����������ĳ�������ֻ��һ�δ���
			
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

		// ȡ��ÿ������Ĵ���Ƶ��(����Ϣ�õĺͳ��ָ��ʳɷ���)
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
		
		//�������ĳ��־��ӵ�Ƶ��
		for(Map.Entry<String,Integer>map_entry:numOfSent4Word.entrySet())
		{
			String word=map_entry.getKey();
			int senTimes=map_entry.getValue().intValue();
			double frequency=(double)senTimes/length;
			preOfSent4Word.put(word,frequency);
		}
		

		// ��������Ϣ������ӵ�TreeMap��
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
		
		
		/****************�����ĵ��о��ӵĴ���������ƽ��ֵ ȥͣ�ô�֮��*********/
		this.averageCharacterInSentence=(double)times/length;
		
		
		
		/*********************����wtk*********************/
		//����δ��һ����wtk
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
		//��һ��wtk
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
		File file=new File("ժҪtest2.txt");
		Long length=file.length();
		
		FileInputStream fis=new FileInputStream(file);
		
	    byte[] contentByte=new byte[length.intValue()];
		
	    fis.read(contentByte,0,length.intValue());
	    
	    String input=new String(contentByte);
	 
	    fis.close();
	    
	    */
		String input="��һ�����Ƿ�æ��һ�ܡ����Ƕ����벻ͣ��Ŭ�����У�һ�ܵĹ������ڽ�����";
	    SentenceNew sentenceNew=new SentenceNew(input,"����ͣ�ôʱ�.txt");
	    
	    sentenceNew.setSet();
	    
	    System.out.println(sentenceNew.averageCharacterInSentence);
	}
}
