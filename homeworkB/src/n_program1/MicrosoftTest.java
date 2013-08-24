package n_program1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class MicrosoftTest
{
	public static void main(String[] args) throws Exception
	{
		//计算语料的相应的概率以及值
		DataProcess1 test = new DataProcess1(new File("msr_training.txt"));
		HashMap<String, Integer> map;
		HashMap<String, Double> mapFrency;
		map = test.doStatistic();
		mapFrency = test.StatisticFrency(map);
		
		BufferedReader br=new BufferedReader(new FileReader("msr_test.txt"));
		BufferedWriter bw=new BufferedWriter(new FileWriter("msr_result.txt"));
		
		
		String sentence;
		while(null!=(sentence=br.readLine()))
		{
			double[][] matrix;
			matrix = test.getMatrix(mapFrency, sentence);			
			boolean[][] path=test.dijkstraShotrest(matrix,sentence);
			
			String result=test.getResult(path,sentence);
			bw.write(result+"\n");
		}
		
		br.close();
		bw.close();
		
		System.out.println("finish");
		
	}
}
