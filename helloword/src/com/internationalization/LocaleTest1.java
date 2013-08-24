/*1.���ǹ��ʻ��Ĳ��ԣ���Ӧ����sturts�еĹ��ʻ� 
 *2.��struts�й��ʻ���ʱ�����Ǵ�����properties�ļ������ֱ�����package_en_US.properties������ʽ��������������ͨ
 *  ��javaӦ���У����ǲ���Ҫ��ô�ϸ�ֻҪ����һ���Ϳ��ԣ���������liran_en_US.properties���־Ϳ���
 *3.���Ǵ��������properties�ļ��У���ͬ�Ĺ��ҵ�key�ĸ����Լ�����һ��Ҫ����һ���ģ���������ļ���Ҫ����classpath����
 * */
package com.internationalization;

import java.util.Locale;

public class LocaleTest1
{
	public static void main(String[] args)
	{
		Locale[] locales=Locale.getAvailableLocales();
		
		for(Locale locale:locales)
		{
			System.out.println(locale.getDisplayCountry() +"  :  "+ locale.getCountry());
		}
		
		System.out.println("------------------------------------------------------");
		
		for(Locale locale:locales)
		{
			System.out.println(locale.getDisplayLanguage() +"   :  " +locale.getLanguage());
		}
		
		System.out.println("------------------------------------------------------");
		
		System.out.println(Locale.getDefault());
	}
}
