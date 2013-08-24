/*�������������������MessageFormat��ʹ��
 * 1.ͨ�������Ľ��ܣ����ǿ��Կ���{0}��ΪString��ռλ������'{0}'����Ϊ��ԭ�����ַ���
 * 2.���ǽ�����pattern����ʽд�뵽���ǵĹ��ʻ���.properties�ļ��У��Ϳ��Զ�̬�Ľ���Щռλ�����滻��������Ҫ���ַ���
 * 3.��MessageFormat�ľ�̬����format�Ϳ���ʵ�ֶ�̬��ռλ����ֵ�ˣ�ע�����Ĳ����Ǹ��ɱ������������object���͵����飬
 * ��java�����ж��Ƕ����Ҷ���Object�����࣬����������ˣ��һ�new Object[]{new Object()} ֱ��new Object[]{"string"}
 * �Ϳ����ˣ�ԭ�������Զ�װ��
 * */
package com.internationalization;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageFormatTest1
{
	public static void main(String[] args)
	{
		ResourceBundle rb=ResourceBundle.getBundle("liran",Locale.US);
		String value=rb.getString("welcome");
		
		String result=MessageFormat.format(value,new Object[]{"�����㰡"});
		
		System.out.println(result);
	}
}
