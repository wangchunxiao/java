package auto_aubstract;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import kevin.zhang.NLPIR;

public class Temp
{
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		String temp = "我盟阿斯顿发斯蒂芬啊，刚啊刮大风的够。阿三了多个骄傲估计.阿迪噶的噶？               阿哥的拉萨高科技啊g?       sadadlgj;alsdgjlasdjg；     dfasdfalsdgjaldg  ";
		String[] strArray = null;
		if (!"".equals(temp))
		{
			strArray = temp.split("[.|!|?|;|；|？|。|！]");
			for (String str : strArray)
			{
				System.out.println(str);
			}
		}
		else
		{
			System.out.println("string is empty");
		}

		System.out.println("---------------------------------------------");
		String[] strArray2 = new String[strArray.length];
		for (int i = 0; i < strArray.length; i++)
		{
			strArray2[i] = strArray[i].trim();
		}
		for (String str2 : strArray2)
		{
			System.out.println(str2);
		}
		System.out.println("---------------------------------------------");

		String[] test = null;
		System.out.println(test == null);
		System.out.println("---------------------------------------------");

		NLPIR testNLPIR = new NLPIR();
		String argu = "";

		if (testNLPIR.NLPIR_Init(argu.getBytes("GB2312"), 1) == false)
		{
			System.out.println("Init Fail!");
			return;
		}

		for (String str : strArray2)
		{
			byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(
					str.getBytes("GB2312"), 0);
			String temp1 = new String(nativeBytes, 0, nativeBytes.length,
					"UTF-8");
			String[] tempArray = temp1.split(" ");
			for (String a : tempArray)
			{
				System.out.println(a);
			}
			System.out.println("----");

		}

		testNLPIR.NLPIR_Exit();

	}
}

class Sentence
{
	public String sentence = "";
	public String[] strArray = null;
	public TreeSet<Map.Entry<String, Double>> set;
	public TreeSet<Map.Entry<String, Double>> normalizedSet;

	public Sentence(String sentence)
	{
		String afterSentence = sentence.replaceAll("(\\r\\n)", ".");
		afterSentence = afterSentence.replaceAll("　", "");
		this.sentence = afterSentence;
	}

	public void setStringArray()
	{
		if (!"".equals(sentence))
		{
			String[] strArrayRow = sentence.split("[.|!|?|;|；|？|。|！]");
			String[] strArray = new String[strArrayRow.length];
			for (int i = 0; i < strArrayRow.length; i++)
			{
				strArray[i] = strArrayRow[i].trim();
			}
			this.strArray = strArray;
		}
		else
			return;
	}

	public void setSet() throws UnsupportedEncodingException
	{
		if ("".equals(sentence)) { return; }

		if (strArray == null)
		{
			setStringArray();

			if (strArray == null)
				return;
			else
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
				HashMap<String, Double> mapdouble = new HashMap<String, Double>();// 词项的频率

				HashMap<String, Double> map = new HashMap<String, Double>();// 这是用句子做key，信息熵的和做value的map,准备转化为TreeSet
				String[] splitArray = new String[length];

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

					for (String s1 : result)
					{
						times++;
						if (mapint.containsKey(s1))
						{
							int j = mapint.get(s1).intValue();
							mapint.put(s1, j + 1);
						}
						else
						{
							mapint.put(s1, 1);
						}
					}
				}
				testNLPIR.NLPIR_Exit();

				// 取得每个词语的词项频率
				for (Map.Entry<String, Integer> map_entry : mapint.entrySet())
				{
					String word = map_entry.getKey();
					int wordtimes = map_entry.getValue().intValue();
					double frequency = (double) wordtimes / times;
					double logFrequency = (double) Math.log10(frequency);
					double selfInformation = (-1) * logFrequency;
					mapdouble.put(word, selfInformation);
				}

				// 计算自信息并且添加到TreeMap中
				for (int i = 0; i < length; i++)
				{
					String splitstr = splitArray[i];
					double sumSelfInfomation = 0.0;
					String[] result = splitstr.split(" ");
					for (String s2 : result)
					{
						sumSelfInfomation += mapdouble.get(s2);
					}
					map.put(strArray[i], sumSelfInfomation);
				}

				set.addAll(map.entrySet());
				this.set = set;
			}
		}// if (strArray == null)
		else
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
			HashMap<String, Double> mapdouble = new HashMap<String, Double>();// 词项的频率

			HashMap<String, Double> map = new HashMap<String, Double>();// 这是用句子做key，信息熵的和做value的map,准备转化为TreeSet
			String[] splitArray = new String[length];

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
				String temp = new String(nativeBytes, 0, nativeBytes.length,
						"UTF-8");

				splitArray[i] = temp;// 将分词的结果放入到一个数组中，方便之后计算每句话的信息熵

				String[] result = temp.split(" ");

				for (String s1 : result)
				{
					times++;
					if (mapint.containsKey(s1))
					{
						int j = mapint.get(s1).intValue();
						mapint.put(s1, j + 1);
					}
					else
					{
						mapint.put(s1, 1);
					}
				}
			}
			testNLPIR.NLPIR_Exit();

			// 取得每个词语的词项频率
			for (Map.Entry<String, Integer> map_entry : mapint.entrySet())
			{
				String word = map_entry.getKey();
				int wordtimes = map_entry.getValue().intValue();
				double frequency = (double) wordtimes / times;
				double logFrequency = (double) Math.log10(frequency);
				double selfInformation = (-1)* logFrequency;
				mapdouble.put(word, selfInformation);
			}

			// 计算自信息并且添加到TreeMap中
			for (int i = 0; i < length; i++)
			{
				String splitstr = splitArray[i];
				double sumInfomation = 0.0;
				String[] result = splitstr.split(" ");
				for (String s2 : result)
				{
					sumInfomation += mapdouble.get(s2);
				}

				map.put(strArray[i], sumInfomation);
			}

			set.addAll(map.entrySet());
			this.set = set;
		}// if (strArray == null)
	}

	public String getAllResult() throws UnsupportedEncodingException
	{
		String result = new String();
		int words = 0;
		if (set == null)
		{
			setSet();

			if (set == null)
				return null;
		}

		for (Map.Entry<String, Double> map_entry : set)
		{
			result += map_entry.getKey() + "    " + map_entry.getValue() + "\n";
		}
		return result;
	}

	public String getNormalizedResult() throws UnsupportedEncodingException
	{
		String result=new String();
		if (normalizedSet == null)
		{
			allResultNormalized();
			if (normalizedSet == null)
				return null;
		}

		for (Map.Entry<String, Double> map_entry : normalizedSet)
		{
			result += map_entry.getKey() + "    " + map_entry.getValue() + "\n";
		}
		return result;
	}

	public void allResultNormalized() throws UnsupportedEncodingException
	{
		TreeSet<Map.Entry<String, Double>> normalizedSet = new TreeSet<Map.Entry<String, Double>>(
				new Comparator<Map.Entry<String, Double>>()
				{
					public int compare(Entry<String, Double> o1,
							Entry<String, Double> o2)
					{
						return o2.getValue().compareTo(o1.getValue());
					}
				});

		double sum = 0.0;

		if (set == null)
		{
			setSet();

			if (set == null)
				return;
		}

		for (Map.Entry<String, Double> map_entry : set)
		{
			sum += map_entry.getValue();
		}
		
		//HashMap<String,Double> map=new HashMap<String,Double>();
		for (Map.Entry<String, Double> map_entry : set)
		{
			//map.put(map_entry.getKey(),map_entry.getValue()/sum);
			
			Map.Entry<String, Double> a = new MyEntry<String, Double>(
					map_entry.getKey(), map_entry.getValue() / (Double)sum);
			normalizedSet.add(a);
			
		}
		//normalizedSet.addAll(map.entrySet());
		this.normalizedSet = normalizedSet;
	}

	public String getFinalResult(int threshold) throws UnsupportedEncodingException
	{
		String returnStr=new String();
		if (normalizedSet == null)
		{
			allResultNormalized();
			if (normalizedSet == null)
				return null;
		}
		
		
		String resultStr = null;
		ArrayList<String> list=new ArrayList<String>();
		int length = 0;
		for(Map.Entry<String,Double> map_entry:normalizedSet)
		{
			int nowLength=map_entry.getKey().length();
			length+=nowLength;
			if(length>threshold)
				break;
			list.add(map_entry.getKey());
		}
		
		String[] result=new String[list.size()];
		list.toArray(result);
		sortSentence(result,strArray);
		
		for(String temp:result)
		{
			returnStr=returnStr+temp+".";
		}
		
		return returnStr;
	}

	protected void sortSentence(String[] result,String[] strArray)
	{
		for(int i=0;i<result.length;i++)
		{
			for(int j=i+1;j<result.length;j++)
			{
				if(findStrInStrArray(result[i]) > findStrInStrArray(result[j]) )
				{
					String temp=result[i];
					result[i]=result[j];
					result[j]=temp;
				}
			}
		}
	}

	protected int findStrInStrArray(String finded)
	{
		int a = -1;
		for (int i = 0; i < strArray.length; i++)
		{
			if (strArray[i].equals(finded)) { return i; }
		}
		return a;
	}
}

class MyEntry<K, V> implements Map.Entry<K, V>
{
	private final K key;
	private V value;

	public MyEntry(final K key)
	{
		this.key = key;
	}

	public MyEntry(final K key, final V value)
	{
		this.key = key;
		this.value = value;
	}

	public K getKey()
	{
		return key;
	}

	public V getValue()
	{
		return value;
	}

	public V setValue(final V value)
	{
		final V oldValue = this.value;
		this.value = value;
		return oldValue;
	}
}
