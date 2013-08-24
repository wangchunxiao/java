package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import n_program2.DataProcess;
import n_program2.FindShort;
import n_program2.SentenceProcessAndCreateGrphic;


public class TestSerializable
{
	public static void main(String[] args) throws Exception
	{
		FileInputStream fis=new FileInputStream(new File("序列化保存结果.txt"));
		ObjectInputStream ois=new ObjectInputStream(fis);
		
		DataProcess test1=(DataProcess)ois.readObject();
		
		ois.close();
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String input;
		System.out.println("input quit to quit!");
		while(null!=(input=br.readLine()))
		{
			if(input.equals("quit"))
				break;
			
			SentenceProcessAndCreateGrphic test2 = new SentenceProcessAndCreateGrphic(
					input);
			
			test2.setLargeLengthOfWord(test1.getLargeLengthOfWord());
			test2.setWordSet(test1.getWordSet());
			test2.setMapWeight(test1.getMapWeight());
			test2.setLargeWeight(test1.getLargeWeight());
			
			test2.getAllWordInSentence();
			test2.createVertexList();
			test2.createGraph();
			
			FindShort test3=new FindShort(test2);
			test3.Dijkstra();
			System.out.println(test3.getResult());
		}
		br.close();
		System.out.println("finish");
	}
}


