/*测试了我们的gson
 * 1.首先无论何种list都是可以转换成json的，如果是list或者array则转换成为json的array，如果不是那种像people一样包含属性
 *   的对象，就是直接转换成了js的数组，数组的元素类型都是String
 * 2.要是那种包含属性的对象就转换成了json对象，方法舍弃，只要属性
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
