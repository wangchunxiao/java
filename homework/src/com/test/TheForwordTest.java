package com.test;

import java.util.HashSet;

public class TheForwordTest
{
	public static String ictclas(HashSet<String> set, String sentence,
			int large_length)
	{
		if(sentence.matches("[\u4e00-\u9fa5]+"))
		{
			String result = new String();
			boolean flags = true;// 判断是不是起始位置，真为起始位置，这样后面就可以不加'/'了
	
			int sentence_length = sentence.length();
			int length = large_length;
			if (sentence_length < large_length)// 判断这个句子的长度有没有最长的词语长度长，使length为它们两个短的
				length = sentence_length;
	
			int pos_begin = 0;
			int pos_end = length;// 这里不减1
			while (pos_begin < sentence_length)
			{
				if (pos_begin == (pos_end - 1))// 判断就一个字的情况，单字就构成词
				{
					if (flags == false)
						result = result
								+ ("/" + sentence.substring(pos_begin, pos_end));
					else if (flags == true)
					{
						result = result + sentence.substring(pos_begin, pos_end);
						flags = false;
					}
					pos_begin = sentence_length;
					break;// 单词就跳出循环了
				}
	
				else
				{
					while (pos_begin < (pos_end - 1))
					{
						String temp = sentence.substring(pos_begin, pos_end);
						if (true == set.contains(temp))
						{
							if (flags == false)
								result = (result + "/" + temp);
							else if (flags == true)
							{
								result = result + temp;
								flags = false;
							}
	
							if (pos_end != sentence_length)// 判断是否已经到末尾了 ，没到返回真
							{
								pos_begin = pos_end;
								if (pos_begin + length > sentence_length)// 判断剩余长度是否还能最大匹配
																			// 能返回真
									pos_end = sentence_length;
								else
									pos_end = pos_begin + length;
							}
							else
							{
								pos_begin = sentence_length;
								break;
							}
						}
						else// if (true == set.contains(temp))
						{
							pos_end--;
						}
					}// else if(pos_begin == pos_end)
					if (pos_begin == (pos_end - 1))
					{
						if (flags == false)
							result = result
									+ ("/" + sentence.substring(pos_begin, pos_end));
						else if (flags == true)
						{
							result = result
									+ sentence.substring(pos_begin, pos_end);
							flags = false;
						}
						if (pos_end != sentence_length)// 判断是否已经到末尾了 ，没到返回真
						{
							pos_begin = pos_end;
							if (pos_begin + length > sentence_length)// 判断剩余长度是否还能最大匹配
																		// 能返回真
								pos_end = sentence_length;
							else
								pos_end = pos_begin + length;
						}
						else
						{
							pos_begin = sentence_length;
						}
	
					}
				}
			}
			return result;
		}//if(sentence.matches("[\u4e00-\u9fa5]+"))
		else
		{
			return sentence;
		}
			
	}
}
