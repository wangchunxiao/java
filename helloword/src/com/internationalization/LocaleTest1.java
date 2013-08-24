/*1.这是国际化的测试，对应我们sturts中的国际化 
 *2.在struts中国际化的时候我们创建的properties文件的名字必须是package_en_US.properties这种形式，但是在我们普通
 *  的java应用中，我们不必要这么严格，只要后面一样就可以，就是形如liran_en_US.properties这种就可以
 *3.我们创建的这个properties文件中，不同的国家的key的个数以及名称一定要都是一样的，此外这个文件还要放在classpath下面
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
