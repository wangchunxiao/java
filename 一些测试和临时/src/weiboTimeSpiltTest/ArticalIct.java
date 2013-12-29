package weiboTimeSpiltTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

import kevin.zhang.NLPIR;

public class ArticalIct
{
		public static void main(String[] args) throws Exception
		{
			NLPIR testNLPIR = new NLPIR();
			String argu = "";		
			if (testNLPIR.NLPIR_Init(argu.getBytes("GB2312"),1) == false)
			{
				System.out.println("Init Fail!");
				return;
			}
			
			BufferedReader br=new BufferedReader(new FileReader("1.txt"));
			BufferedWriter bw=new BufferedWriter(new FileWriter("testICTResult.txt"));
			
			String input;
			while(null!=(input=br.readLine()))
			{
				byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(input.getBytes("GB2312"), 0);
				String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "UTF-8");
				nativeStr+="\n";
				bw.write(nativeStr,0,nativeStr.length());
				
			}			
			br.close();
			bw.close();
			
			
			
			BufferedReader br1=new BufferedReader(new FileReader("testICTResult.txt"));
			BufferedWriter bw1=new BufferedWriter(new FileWriter("testICTResult2.txt"));
			//BufferedWriter bw2=new BufferedWriter(new FileWriter("testICTResult3.txt"));
			String input1;
			int wordNums=0;
			while(null!=(input1=br1.readLine()))
			{
				String temp=input1.replaceAll("[^\u4E00-\u9FA5^\\s]","");
				bw1.write(temp+"\n");
				
				String[] array=temp.split("[\\s]+");
				ArrayList<String> list=new ArrayList<String>();
				for(String j:array)
				{
					list.add(j);
					wordNums++;
				}
			}		
			
			br1.close();
			bw1.close();
			
			System.out.println(wordNums);
			System.out.println("--------------------------------");
		}
		
}
