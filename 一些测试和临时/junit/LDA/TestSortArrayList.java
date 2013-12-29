package LDA;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Test;

public class TestSortArrayList
{
	public static class MyCom implements Comparator<Integer>
	{
		public double[] sortPro;

		public MyCom(double[] sortPro)
		{
			this.sortPro = sortPro;
		}

		@Override
		public int compare(Integer o1, Integer o2)
		{
			if (sortPro[o1] > sortPro[o2])
				return -1;
			else if (sortPro[o1] < sortPro[o2])
				return 1;
			else
				return 0;
		}
	}

	@Test
	public void test()
	{
		double[] array=new double[5];
		for(int i=0;i<5;i++)
			array[i]=Math.random();
		
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<5;i++)
			list.add(i);
		
		Collections.sort(list,new TestSortArrayList.MyCom(array));
		
		for(int i=0;i<5;i++)
			System.out.println(list.get(i) + " : " + array[list.get(i)]);
	}
	
	
	
	@Test
	public void test1()
	{
		System.out.println("--------------------------------");
		for(int i=0;i<100;i++)
		{
			System.out.print("Ë¢ÐÂÁË"+i+"\r");
		}
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void test2() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		System.out.println("-----------------test2-----------------------");
		ArrayList<String> list=null;
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File("./src/LDA/MAIN/serializable/indexToTermMap.ser")));
		list=(ArrayList<String>) ois.readObject();
		
		for(String temp :list)
			System.out.println(temp);
		System.out.println("----------------------------------");
	}

}
