package auto_aubstract;
import org.junit.Test;


public class TestJuit
{
	@Test
	public void testJuit()
	{
		String temp = "���˰�˹�ٷ�˹�ٷҰ����հ��δ��Ĺ��������˶����������.���ϸ��ĸ���               ����������߿Ƽ���g?       sadadlgj;alsdgjlasdjg��     dfasdfalsdgjaldg  ";
		String[] strArray = null;
		if (!"".equals(temp))
		{
			strArray = temp.split("[.|!|?|;|��|��|��|��]");
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
