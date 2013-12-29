/*���������ǵ�gson
 * 1.�������ۺ���list���ǿ���ת����json�ģ������list����array��ת����Ϊjson��array���������������peopleһ����������
 *   �Ķ��󣬾���ֱ��ת������js�����飬�����Ԫ�����Ͷ���String
 * 2.Ҫ�����ְ������ԵĶ����ת������json���󣬷���������ֻҪ����
 * */
package junit;

import java.util.Date;
import java.util.LinkedList;

import org.junit.Test;

import com.google.gson.Gson;

public class TestGson
{

	@Test
	public void test()
	{
		LinkedList<Object> list=new LinkedList<Object>();
		list.add("hello");
		list.add("world");
		Date date=new Date();
		list.add(date);
		
		People p=new People();
		p.setAge(19);   p.setName("zjamgsam");
		list.add(p);
		
		Gson gson=new Gson();
		String result=gson.toJson(list);
		System.out.println(result);
	}

}

class People
{
	private String name;
	private int age;
	
//	public String getName()
//	{
//		return name;
//	}
	public void setName(String name)
	{
		this.name = name;
	}
//	public int getAge()
//	{
//		return age;
//	}
	public void setAge(int age)
	{
		this.age = age;
	}
	
}
