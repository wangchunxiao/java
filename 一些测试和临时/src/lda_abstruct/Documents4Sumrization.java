package lda_abstruct;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import LDA.MAIN.Documents;
import LDA.MAIN.LdaModel;
import LDA.MAIN.Stopwords;
import edu.fudan.nlp.tag.CWSTagger;

public class Documents4Sumrization
{
	private static Stopwords stopwords;
	public static ArrayList<String> stopwordsList;

	static
	{
		stopwords = new Stopwords("./src/lda_abstruct/resource/", "ChnStopList.txt");
		try
		{
			stopwordsList = stopwords.getStopwords();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public ArrayList<Document4Sumrization> documents;
	public Documents ldaDocuments;
	public LdaModel ldaModel;
	
	public ArrayList<String> filename;
	public ArrayList<ArrayList<String>> result;

	public Documents4Sumrization(Documents ldaDocuments,LdaModel ldaModel)
	{
		this.documents = new ArrayList<Document4Sumrization>();
		this.ldaDocuments=ldaDocuments;
		this.ldaModel=ldaModel;
		this.filename=new ArrayList<String>();
		this.result=new ArrayList<ArrayList<String>>();
	}

	public ArrayList<ArrayList<String>> readDocs(String pathname,int nums) throws Exception
	{
		//ArrayList<ArrayList<String>> result=new ArrayList<ArrayList<String>>();
		File path=new File(pathname);
		int i=0;
		for (File f : path.listFiles())
		{
			int top;//find the highest topic
			ArrayList<Integer> sortUse=new ArrayList<Integer>();
			for(int j=0;j<ldaModel.theta[i].length;j++)
			{
				sortUse.add(j);
			}
			Collections.sort(sortUse,new Documents4Sumrization.MyComparable(ldaModel.theta[i]));
			
			top=sortUse.get(0);
			
			Document4Sumrization document = new Document4Sumrization(f,ldaDocuments.indexToTermMap,ldaModel.phi[top],this.filename);
		
			result.add(document.getSumrization(nums));
		}
		return result;
	}

	public static class Document4Sumrization
	{
		public File file;
		public ArrayList<String> sentences;
		public ArrayList<ArrayList<String>> segmentSentences;
		public double[] sentenceWeight;

		public Document4Sumrization(File file,
				ArrayList<String> indexToTermMap, double[] phiK, ArrayList<String> filename)
				throws Exception
		{
			this.file = file;
			this.sentences = new ArrayList<String>();
			this.segmentSentences = new ArrayList<ArrayList<String>>();
            
			//add filename to the list
			filename.add(file.getName());
			
			// read the content
			int length = (int) file.length();
			byte[] buffer = new byte[length];
			FileInputStream fis = new FileInputStream(file);
			fis.read(buffer);
			fis.close();
			String content = new String(buffer);

			// process the content to sentences
			String[] sentencesArray = content.split("[¡£|£¡|\n]");
			for (String sen : sentencesArray)
			{
				if (!"".equals(sen))
				{
					sentences.add(sen.trim());
				}
			}

			// chinese segmentation
			CWSTagger tag = new CWSTagger("./src/lda_abstruct/resource/models/seg.c110722.gz");
			for (String sen : sentences)
			{
				ArrayList<String> segment = new ArrayList<String>();
				String s = tag.tag(sen);
				String temp[] = s.split(" ");
				for (String str : temp)
				{
					if (!Documents4Sumrization.stopwordsList.contains(str))
					{
						segment.add(str);
					}
				}
				this.segmentSentences.add(segment);
			}

			// compute the weight
			int size = sentences.size();
			this.sentenceWeight = new double[size];
			for (int i = 0; i < size; i++)
			{
				double weight = 0.0;
				ArrayList<String> wordSet = segmentSentences.get(i);
				for (String str : wordSet)
				{
					int index = indexToTermMap.indexOf(str);
					if(index==-1)
					{
						System.out.println(str);
						continue;
						
						//to do 
					}
					
					weight += phiK[index];
				}
				sentenceWeight[i] = weight;
			}
		}

		public ArrayList<String> getSumrization(int wordNums)
		{
			ArrayList<String> result = new ArrayList<String>();

			// sort the result
			ArrayList<Integer> sortUse = new ArrayList<Integer>();
			for (int i = 0; i < sentences.size(); i++)
			{
				sortUse.add(i);
			}

			Collections.sort(sortUse, new Documents4Sumrization.MyComparable(
					sentenceWeight));

			int nums = sentences.get(sortUse.get(0)).length();
			int i = 0;
			do
			{
				result.add(sentences.get(sortUse.get(i)));
				i++;
				nums+=sentences.get(sortUse.get(i)).length();
			} while (nums < wordNums);

			return result;
		}
	}

	public static class MyComparable implements Comparator
	{
		double[] weight;

		public MyComparable(double[] weight)
		{
			this.weight = weight;
		}

		@Override
		public int compare(Object o1, Object o2)
		{
			int ob1 = (Integer) o1;
			int ob2 = (Integer) o2;

			if (weight[ob1] < weight[ob2])
			{
				return 1;
			}
			else if (weight[ob1] > weight[ob2])
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
	}

	public static void main(String[] args)
	{

	}
}
