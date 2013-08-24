package n_program2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DataProcess implements Serializable
{
	private File file;
	private HashSet<String> wordSet;
	private HashMap<String,Integer> map;
	private HashMap<String,Double> mapWeight;//记录每一个二元的值和概率
	
	private HashMap<String,Integer> mapOneGram;//我们重新评估二元的权值，需要统计一元的概率
	private HashMap<String,Double> mapOneGramWeight;
	

	private double largeWeight;//记录最大的二元权重，(概率最低的二元概率，做平滑使用)
	private int times;//记录词语的个数
	private int largeLengthOfWord;//记录最长的词的长度，为建词图做准备
	
	public DataProcess(File file) throws Exception
	{
		if(null==file)
			throw new Exception("file is null in construct");
		else
		{
				this.file=file;
				wordSet=new HashSet<String>();
				map=new HashMap<String,Integer>();
				mapWeight=new HashMap<String,Double>();
				mapOneGram=new HashMap<String,Integer>();
				mapOneGramWeight=new HashMap<String,Double>();
				largeWeight=0.0;
				largeLengthOfWord=0;
		}
	
	}
	
	public boolean doStatistic() throws Exception
	{
		if(null==file)
		{
			throw new Exception("file is null");
		}
		else
		{
			BufferedReader br=new BufferedReader(new FileReader(file));
			String readSentence;
			while(null!=(readSentence=br.readLine()))
			{
				String[] wordArray=readSentence.split(" ");//我们的语料，我们用一个空格
				//String[] wordArray=readSentence.split("  ");//微软的语料，用两个空格
				String forwordString=new String();
				String temp;
				for(int i=0;i<wordArray.length;i++)
				{
					times++;//更新出现次数
					
					//先加入到一元的词库
					if(!wordSet.contains(wordArray[i]))
					{
						wordSet.add(wordArray[i]);
						largeLengthOfWord=(largeLengthOfWord<wordArray[i].length()) ? (wordArray[i].length()) : largeLengthOfWord;
					}
					
					
					//这里是我们补充的统计一元概率的地方
					if(!mapOneGram.containsKey(wordArray[i]))
					{
						mapOneGram.put(wordArray[i],1);						
					}
					else if(mapOneGram.containsKey(wordArray[i]))
					{
						int num=mapOneGram.get(wordArray[i]).intValue();
						mapOneGram.put(wordArray[i],++num);
					}
					
					
					
					
					
					//加入二元的map，并要统计概率了
					if(i==0)
					{
						temp=wordArray[i]+"|B";
						if(map.containsKey(temp))
						{
							int t=map.get(temp).intValue();
							map.put(temp,t+1);
						}
						else if(!map.containsKey(temp))
						{
							map.put(temp,1);
						}
						
						forwordString=wordArray[i];//保存前项的词语
                         
					}
					else if(i==(wordArray.length-1))
					{
						temp="E|"+wordArray[i];
						if(map.containsKey(temp))
						{
							int t=map.get(temp).intValue();
							map.put(temp,t+1);
						}
						else if(!map.containsKey(temp))
						{
							map.put(temp,1);
						}
						
						//这里不要忘记了,最后一个字的时候我们要做两个工作，一个是统计 最后一个|倒数第二个 ,另一个是统计 E|最后一个
						temp=wordArray[i]+ "|" +forwordString;
						if(map.containsKey(temp))
						{
							int t=map.get(temp).intValue();
							map.put(temp,t+1);
						}
						else if(!map.containsKey(temp))
						{
							map.put(temp,1);
						}
						
						forwordString=wordArray[i];//保存前项的词语
						
					}
					else if(i>0 && i<wordArray.length-1)
					{
						temp=wordArray[i]+ "|" +forwordString;
						if(map.containsKey(temp))
						{
							int t=map.get(temp).intValue();
							map.put(temp,t+1);
						}
						else if(!map.containsKey(temp))
						{
							map.put(temp,1);
						}
						
						forwordString=wordArray[i];//保存前项的词语
					}
				}//for(int i=0;i<wordArray.length;i++)
			}//while(null!=(readSentence=br.readLine()))
			
			br.close();
			return true;
		}//else
	}//public boolean doStatistic() throws Exception

	public void doStatisticWeight() throws Exception
	{
		if(times==0)
		{
			throw new Exception("times=0,must doStatistic before doStatisticWeight");
		}
		else
		{
			Set<Map.Entry<String,Integer>> set=map.entrySet();
			for(Iterator<Map.Entry<String,Integer>> iter=set.iterator();iter.hasNext();)
			{
				Map.Entry<String,Integer> map_entry=iter.next();
				double weight=0.0;
				
				weight=((double)(map_entry.getValue()))/times;
				weight=Math.log(weight)*(-1);
				String tempstring=map_entry.getKey();
				if(!mapWeight.containsKey(tempstring))
				{
					mapWeight.put(tempstring,weight);
				}
				
				largeWeight=(weight>largeWeight)? weight:largeWeight;
			}
			
			Set<Map.Entry<String,Integer>> setOneGram=mapOneGram.entrySet();
			for(Iterator<Map.Entry<String,Integer>> iter=setOneGram.iterator(); iter.hasNext(); )
			{
				Map.Entry<String,Integer>map_entry=iter.next();
				double weight=0.0;
				weight=(double)(map_entry.getValue())/times;
				weight=Math.log(weight)*(-1);
				
				if(!mapOneGramWeight.containsKey(map_entry.getKey()))
				{
					mapOneGramWeight.put(map_entry.getKey(),weight);
				}
			}
		}
		
	}
	

	
	
	public static void main(String[] args) throws Exception
	{
		//写个测试程序测试下doStatistic和doStatisticWeight让语料的行数少一点
		DataProcess test1=new DataProcess(new File("test_doStatistic.txt"));
		test1.doStatistic();
		test1.doStatisticWeight();
		
		System.out.println(test1.getLargeLengthOfWord());
		System.out.println(test1.getTimes());
		System.out.println(test1.getLargeWeight());
		
		System.out.println("-------------------------------------");
		
		HashSet<String> wordSet=test1.getWordSet();
		for(Iterator<String> iter=wordSet.iterator();iter.hasNext();)
		{
			System.out.println(iter.next());
		}
		
		System.out.println("-------------------------------------");
		
		HashMap<String,Integer> mapOneGram=test1.getMapOneGram();
		Set<Map.Entry<String,Integer>> setOneGram=mapOneGram.entrySet();
		for(Iterator<Map.Entry<String,Integer>> iter=setOneGram.iterator();iter.hasNext();)
		{
			Map.Entry map_entry=iter.next();
			System.out.println(map_entry.getKey()+"   "+map_entry.getValue());
		}

		System.out.println("-------------------------------------");
		
		
		HashMap<String, Integer> map =test1.getMap();
		Set<Map.Entry<String,Integer>> set=map.entrySet();
		for(Iterator<Map.Entry<String,Integer>> iter=set.iterator();iter.hasNext();)
		{
			Map.Entry map_entry=iter.next();
			System.out.println(map_entry.getKey()+"   "+map_entry.getValue());
		}
		
		
		System.out.println("-------------------------------------");
		HashMap<String,Double> mapWeight=test1.getMapWeight();
		Set<Map.Entry<String,Double>> set2=mapWeight.entrySet();
		for(Iterator<Map.Entry<String,Double>> iter=set2.iterator();iter.hasNext();)
		{
			Map.Entry map_entry=iter.next();
			System.out.println(map_entry.getKey()+"   "+map_entry.getValue());
		}
		
		System.out.println("-------------------------------------");
		
		HashMap<String,Double> mapOneGramWeight=test1.getMapOneGramWeight();
		Set<Map.Entry<String,Double>> set3=mapOneGramWeight.entrySet();
		for(Map.Entry map_entry:set3)
		{
			System.out.println(map_entry.getKey()+"   "+map_entry.getValue());
			
		}
		
		
		
		
		System.out.println("-------------------------------------");
		System.out.println("finish");
	    //测试doStatistic和doStatisticWeight成功
		
		
	
	}

	public HashMap<String, Double> getMapWeight()
	{
		return mapWeight;
	}

	public HashSet<String> getWordSet()
	{
		return wordSet;
	}

	public HashMap<String, Integer> getMap()
	{
		return map;
	}

	public double getLargeWeight()
	{
		return largeWeight;
	}

	public int getTimes()
	{
		return times;
	}

	public int getLargeLengthOfWord()
	{
		return largeLengthOfWord;
	}

	public HashMap<String, Integer> getMapOneGram()
	{
		return mapOneGram;
	}

	public void setMapOneGram(HashMap<String, Integer> mapOneGram)
	{
		this.mapOneGram = mapOneGram;
	}

	public HashMap<String, Double> getMapOneGramWeight()
	{
		return mapOneGramWeight;
	}

	public void setMapOneGramWeight(HashMap<String, Double> mapOneGramWeight)
	{
		this.mapOneGramWeight = mapOneGramWeight;
	}
	
}

