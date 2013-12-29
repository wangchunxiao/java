package auto_aubstract;

import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kevin.zhang.NLPIR;

public class KeyFind
{
	private TreeSet<Map.Entry<String, Double>> set;
	public TreeSet<Map.Entry<String, Double>> normalizedSet;
	private String fileName;

	public  KeyFind(String fileName)
	{
		this.fileName = fileName;
		this.set = new TreeSet<Map.Entry<String, Double>>(
				new Comparator<Map.Entry<String, Double>>()
				{
					@Override
					public int compare(Entry<String, Double> o1,
							Entry<String, Double> o2)
					{
						return o2.getValue().compareTo(o1.getValue());
					}

				});

		this.normalizedSet = new TreeSet<Map.Entry<String, Double>>(
				new Comparator<Map.Entry<String, Double>>()
				{
					@Override
					public int compare(Entry<String, Double> o1,
							Entry<String, Double> o2)
					{
						return o2.getValue().compareTo(o1.getValue());
					}

				});
	}

	public Set<Map.Entry<String, Double>> getSet()
			throws UnsupportedEncodingException
	{
		if (set.size() == 0)
			process();
		return set;
	}

	public void process() throws UnsupportedEncodingException
	{
		NLPIR testNLPIR = new NLPIR();
		String argu = "";
		if (testNLPIR.NLPIR_Init(argu.getBytes("GB2312"), 1) == false)
		{
			System.out.println("Init Fail!");
			return;
		}

		byte[] nativeBytes = testNLPIR.NLPIR_GetFileKeyWords(
				fileName.getBytes("GB2312"), 10, true);
		// 如果是处理内存，可以调用testNLPIR.NLPIR_GetKeyWords
		String nativeStr = new String(nativeBytes, 0, nativeBytes.length,
				"utf-8");

		Pattern pattern = Pattern.compile("([^/\\s]+)/(\\d+\\.\\d+)");
		Matcher matcher = pattern.matcher(nativeStr);

		HashMap<String, Double> map = new HashMap<String, Double>();
		while (matcher.find())
		{
			map.put(matcher.group(1), Double.valueOf(matcher.group(2)));
		}

		this.set.addAll(map.entrySet());
		testNLPIR.NLPIR_Exit();
	}

	public void Normalized() throws UnsupportedEncodingException
	{
		if (set.size()==0)
			process();

		double sum = 0.0;
		for (Map.Entry<String, Double> map_entry : set)
		{
			sum += map_entry.getValue();
		}
		
		for (Map.Entry<String, Double> map_entry : set)
		{
			
			Map.Entry<String, Double> a = new MyEntry<String, Double>(
					map_entry.getKey(), map_entry.getValue() / (Double)sum);
			
			normalizedSet.add(a);
			
		}
	}
	
	public String getNormalizedResult() throws UnsupportedEncodingException
	{
		String result=new String();
		if(normalizedSet.size()==0)
			Normalized();
		
		for (Map.Entry<String, Double> map_entry : normalizedSet)
		{
			result += map_entry.getKey() + "    " + map_entry.getValue() + "\n";
		}
		
		return result;
		
	}

	public static void main(String[] args) throws UnsupportedEncodingException
	{
		KeyFind keyfind=new KeyFind("摘要test2.txt");
		keyfind.Normalized();
		
		System.out.println(keyfind.getNormalizedResult());
	}

}
