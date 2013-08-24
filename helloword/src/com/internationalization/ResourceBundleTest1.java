/*������������������ǵ�ResourceBundle�������ʹ�ã�
 * 1.ע��basename����������properties�ļ��У�_en_USǰ����ַ���
 * 2.������Ƕ���һ��basename.properties�ļ�����ô����ֻҪ�Ҳ������ǵ�ѡȡ��locale���ͻ���������൱����Ĭ�ϵ�
 * */
package com.internationalization;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleTest1
{
	public static void main(String[] args)
	{
		ResourceBundle resourceBundle=ResourceBundle.getBundle("liran");
		//System.out.println(resourceBundle);
		
		String result=resourceBundle.getString("hello");
		System.out.println(result);
		
		System.out.println("-----------------------------------------------");
		
		resourceBundle=ResourceBundle.getBundle("liran",Locale.US);
		result=resourceBundle.getString("hello");
		System.out.println(result);
	}
}
