package n_program2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

public class Main
{
	public static void main(String[] args) throws Exception 
	{
		DataProcess test1 = new DataProcess(new File("NiuTrans.txt"));
		test1.doStatistic();
		test1.doStatisticWeight();
		
		//我们在这里保存统计结果，这个是后加的代码
		FileOutputStream fos=new FileOutputStream(new File("序列化保存结果.txt"));
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(test1);
		oos.close();

		
		
		
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
