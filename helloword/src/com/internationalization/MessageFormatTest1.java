/*这个程序我们用来测试MessageFormat的使用
 * 1.通过这个类的介绍，我们可以看到{0}作为String的占位符，而'{0}'则认为是原来的字符串
 * 2.我们将这种pattern的形式写入到我们的国际化的.properties文件中，就可以动态的将这些占位符给替换成我们想要的字符串
 * 3.用MessageFormat的静态方法format就可以实现动态给占位符赋值了，注意它的参数是个可变参数，我们用object类型的数组，
 * 而java的所有都是对象，且都是Object的子类，这个我忘记了，我还new Object[]{new Object()} 直接new Object[]{"string"}
 * 就可以了，原生类型自动装箱
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
		
		String result=MessageFormat.format(value,new Object[]{"就是你啊"});
		
		System.out.println(result);
	}
}
