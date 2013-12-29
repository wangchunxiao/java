package juit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.Test;

public class TestLdaSerializable
{
	@Test
	public void test() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File("./src/LDA/MAIN/serializable/indexToTermMap.ser")));
		Object o1=ois.readObject();
		System.out.println(o1 instanceof ArrayList);
		System.out.println("--------------------------------------------------");
		ArrayList<String> list= (ArrayList<String>)o1;
		for(String word:list)
			System.out.println(word);
		
		ois.close();
		System.out.println("------------------------------------------------");
	}
	
	
	@Test
	public void test1() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		double[][] array=new double[2][5];
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[i].length;j++)
			{
				array[i][j]=Math.random();
			}
		}
		
		ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(new File("./src/LDA/MAIN/serializable/test.ser")));
		oos.writeObject(array);
		oos.close();
		System.out.println("------------------------------");
		
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File("./src/LDA/MAIN/serializable/test.ser")));
		double[][] array1=(double[][])ois.readObject();
		ois.close();
		
		System.out.println(array1.length);
		for(int i=0;i<array1.length;i++)
		{
			for(int j=0;j<array1[i].length;j++)
			{
				System.out.print(array1[i][j]+"    ");
			}
			System.out.print("\n");
		}
		
		System.out.println("-----------------------------------");
	}

}
