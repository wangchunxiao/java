package n_program2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MicrosoftTest
{
	public static void main(String[] args) throws Exception
	{
		DataProcess test1 = new DataProcess(new File("msr_training.txt"));
		test1.doStatistic();
		test1.doStatisticWeight();
		
		BufferedReader br=new BufferedReader(new FileReader(new File("msr_test.txt")));
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("msr_result_2.txt")));
		
		String sentence;
		while(null!=(sentence=br.readLine()))
		{
			SentenceProcessAndCreateGrphic test2 = new SentenceProcessAndCreateGrphic(
					sentence);
			
			test2.setLargeLengthOfWord(test1.getLargeLengthOfWord());
			test2.setWordSet(test1.getWordSet());
			test2.setMapWeight(test1.getMapWeight());
			test2.setLargeWeight(test1.getLargeWeight());
			
			test2.getAllWordInSentence();
			test2.createVertexList();
			test2.createGraph();
			
			FindShort test3=new FindShort(test2);
			test3.Dijkstra();
			bw.write(test3.outpurMircosoftResult()+"\n");
		}
		br.close();
		bw.close();
		System.out.println("finish");
	}
}
