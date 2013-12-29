package regexTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Http
{
	public static void main(String[] args)
	{
		Pattern p=Pattern.compile("[htp]{4}[^\u4E00-\u9FA5]+");
		String s1="张谁看是平dfasdfaffhttp://www.sidasfa?1341234...?//df.as,gna.com.cn1231244?fadsf奥迪发的啊撒打发发的 ";
		Matcher m=p.matcher(s1);
		while(m.find())
		{
			System.out.println(m.group());
		}
		
	}
}
