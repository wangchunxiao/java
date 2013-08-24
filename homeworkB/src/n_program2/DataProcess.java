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
	private HashMap<String,Double> mapWeight;//��¼ÿһ����Ԫ��ֵ�͸���
	
	private HashMap<String,Integer> mapOneGram;//��������������Ԫ��Ȩֵ����Ҫͳ��һԪ�ĸ���
	private HashMap<String,Double> mapOneGramWeight;
	

	private double largeWeight;//��¼���Ķ�ԪȨ�أ�(������͵Ķ�Ԫ���ʣ���ƽ��ʹ��)
	private int times;//��¼����ĸ���
	private int largeLengthOfWord;//��¼��Ĵʵĳ��ȣ�Ϊ����ͼ��׼��
	
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
				String[] wordArray=readSentence.split(" ");//���ǵ����ϣ�������һ���ո�
				//String[] wordArray=readSentence.split("  ");//΢������ϣ��������ո�
				String forwordString=new String();
				String temp;
				for(int i=0;i<wordArray.length;i++)
				{
					times++;//���³��ִ���
					
					//�ȼ��뵽һԪ�Ĵʿ�
					if(!wordSet.contains(wordArray[i]))
					{
						wordSet.add(wordArray[i]);
						largeLengthOfWord=(largeLengthOfWord<wordArray[i].length()) ? (wordArray[i].length()) : largeLengthOfWord;
					}
					
					
					//���������ǲ����ͳ��һԪ���ʵĵط�
					if(!mapOneGram.containsKey(wordArray[i]))
					{
						mapOneGram.put(wordArray[i],1);						
					}
					else if(mapOneGram.containsKey(wordArray[i]))
					{
						int num=mapOneGram.get(wordArray[i]).intValue();
						mapOneGram.put(wordArray[i],++num);
					}
					
					
					
					
					
					//�����Ԫ��map����Ҫͳ�Ƹ�����
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
						
						forwordString=wordArray[i];//����ǰ��Ĵ���
                         
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
						
						//���ﲻҪ������,���һ���ֵ�ʱ������Ҫ������������һ����ͳ�� ���һ��|�����ڶ��� ,��һ����ͳ�� E|���һ��
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
						
						forwordString=wordArray[i];//����ǰ��Ĵ���
						
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
						
						forwordString=wordArray[i];//����ǰ��Ĵ���
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
		//д�����Գ��������doStatistic��doStatisticWeight�����ϵ�������һ��
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
	    //����doStatistic��doStatisticWeight�ɹ�
		
		
	
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

