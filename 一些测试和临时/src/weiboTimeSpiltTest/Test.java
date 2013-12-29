package weiboTimeSpiltTest;

import java.util.Date;

public class Test
{
	public static void main(String[] args)
	{
		String temp="2012/9/19  0:18:17";
		String[] array=temp.split("[^0-9]+");
		for(String temp1:array)
		{
			System.out.println(temp1);
		}
		System.out.println(array.length);
		
		System.out.println("-----------------------------");
		
		MyDate date=new MyDate("2012/1/19  0:18:17");
		System.out.println(date.toString());
		System.out.println(date.toLocaleString());
		
	}
}


class MyDate extends Date
{
	public  MyDate(String weiboTime)
	{
		String[] array=weiboTime.split("[^0-9]+");
		for(int i=0;i<array.length;i++)
		{
			switch(i)
			{
				case 0:
					this.setYear(Integer.parseInt(array[i])-1900);
					break;
				case 1:
					this.setMonth(Integer.parseInt(array[i])-1);
					break;
				case 2:
					this.setDate(Integer.parseInt(array[i]));
					break;
				case 3:
					this.setHours(Integer.parseInt(array[i]));
					break;
				case 4:
					this.setMinutes(Integer.parseInt(array[i]));
					break;
				case 5:
					this.setSeconds(Integer.parseInt(array[i]));
					break;

			}
			
		}
	}
}