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
			boolean flags = true;// �ж��ǲ�����ʼλ�ã���Ϊ��ʼλ�ã���������Ϳ��Բ���'/'��
	
			int sentence_length = sentence.length();
			int length = large_length;
			if (sentence_length < large_length)// �ж�������ӵĳ�����û����Ĵ��ﳤ�ȳ���ʹlengthΪ���������̵�
				length = sentence_length;
	
			int pos_begin = 0;
			int pos_end = length;// ���ﲻ��1
			while (pos_begin < sentence_length)
			{
				if (pos_begin == (pos_end - 1))// �жϾ�һ���ֵ���������־͹��ɴ�
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
					break;// ���ʾ�����ѭ����
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
	
							if (pos_end != sentence_length)// �ж��Ƿ��Ѿ���ĩβ�� ��û��������
							{
								pos_begin = pos_end;
								if (pos_begin + length > sentence_length)// �ж�ʣ�೤���Ƿ������ƥ��
																			// �ܷ�����
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
						if (pos_end != sentence_length)// �ж��Ƿ��Ѿ���ĩβ�� ��û��������
						{
							pos_begin = pos_end;
							if (pos_begin + length > sentence_length)// �ж�ʣ�೤���Ƿ������ƥ��
																		// �ܷ�����
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
