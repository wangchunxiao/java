package auto_aubstract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

public class LinearInterpolation extends Sentence
{
	private KeyFind keyfind;
	private TreeSet<Map.Entry<String, Double>> interpolationSet;

	public LinearInterpolation(String filename, String input)
			throws IOException
	{
		super(input);
		keyfind = new KeyFind(filename);
	}

	public void doInterpolationSet() throws UnsupportedEncodingException
	{
		this.allResultNormalized();
		keyfind.Normalized();

		this.interpolationSet = new TreeSet<Map.Entry<String, Double>>(
				new Comparator<Map.Entry<String, Double>>()
				{
					@Override
					public int compare(Entry<String, Double> o1,
							Entry<String, Double> o2)
					{
						return o2.getValue().compareTo(o1.getValue());
					}

				});

		for (Map.Entry<String, Double> map_entry : normalizedSet)
		{
			double newResultScore = map_entry.getValue() * 0.5;
			String str = map_entry.getKey();
			for (Map.Entry<String, Double> keyWord_score : keyfind.normalizedSet)
			{
				String word = keyWord_score.getKey();
				if (str.contains(word))
				{
					newResultScore += keyWord_score.getValue() * 0.5;
				}
			}
			Map.Entry<String, Double> a = new MyEntry<String, Double>(str,
					newResultScore);
			interpolationSet.add(a);
		}
	}

	public String getInterpolationSet() throws UnsupportedEncodingException
	{
		String result = new String();

		if (interpolationSet == null)
		{
			doInterpolationSet();

			if (interpolationSet == null)
				return null;
		}

		for (Map.Entry<String, Double> map_entry : interpolationSet)
		{
			result += map_entry.getKey() + "    " + map_entry.getValue() + "\n";
		}

		return result;
	}

	@Override
	public String getFinalResult(int threshold)
			throws UnsupportedEncodingException
	{
		String returnStr = new String();
		if (interpolationSet == null)
		{
			doInterpolationSet();
			if (interpolationSet == null)
				return null;
		}

		String resultStr = null;
		ArrayList<String> list = new ArrayList<String>();
		int length = 0;
		for (Map.Entry<String, Double> map_entry : interpolationSet)
		{
			int nowLength = map_entry.getKey().length();
			length += nowLength;
			if (length > threshold)
				break;
			list.add(map_entry.getKey());
		}

		String[] result = new String[list.size()];
		list.toArray(result);
		sortSentence(result, strArray);

		for (String temp : result)
		{
			returnStr = returnStr + temp + ".";
		}

		return returnStr;
	}

	public static void main(String[] args) throws IOException
	{
		String filename = "ժҪtest3.txt";

		File file = new File(filename);
		Long length = file.length();

		// System.out.println(length);

		FileInputStream fis = new FileInputStream(file);

		byte[] contentByte = new byte[length.intValue()];

		fis.read(contentByte, 0, length.intValue());

		fis.close();
		
		String input = new String(contentByte);

		LinearInterpolation test = new LinearInterpolation(filename, input);

		System.out.println(test.getInterpolationSet());
		
		System.out.println("-----------------------------------------------------------------------------");
		
		System.out.println(test.getFinalResult(250));
		
		FileWriter fw= new FileWriter(new File("ժҪtest3Result.txt"));// new FileOutputStream(new File("ժҪtest3Result.txt"));
		
		fw.write(test.getFinalResult(250));
		
		fw.close();
	}

}
