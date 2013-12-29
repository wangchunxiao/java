package auto_aubstract;
import org.junit.Test;


public class TestJuit
{
	@Test
	public void testJuit()
	{
		String temp = "我盟阿斯顿发斯蒂芬啊，刚啊刮大风的够。阿三了多个骄傲估计.阿迪噶的噶？               阿哥的拉萨高科技啊g?       sadadlgj;alsdgjlasdjg；     dfasdfalsdgjaldg  ";
		String[] strArray = null;
		if (!"".equals(temp))
		{
			strArray = temp.split("[.|!|?|;|；|？|。|！]");
			for (String str : strArray)
			{
				System.out.println(str);
			}
		}
	}
	
	@Test
	public void output_e()
	{
		System.out.println(Math.E);
		System.out.println(Math.sqrt(9.0));
	}
	
	
	
	
	
}
