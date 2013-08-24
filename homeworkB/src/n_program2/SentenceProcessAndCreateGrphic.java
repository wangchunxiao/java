package n_program2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SentenceProcessAndCreateGrphic
{
	private String sentence;
	private HashSet<String> word;
	private double[][] matrix;
	//private VertexType[] vertexArray;
	private ArrayList<VertexType> vertexList;
	private static final double INFINITY=1000000;
	
	private double largeWeight;
	private int largeLengthOfWord;
	private HashSet<String> wordSet;
	private HashMap<String, Double> mapWeight;

	public SentenceProcessAndCreateGrphic(String sentence)
	{
		this.sentence = sentence;
		word = new HashSet<String>();
	}

	public void setLargeLengthOfWord(int largeLengthOfWord)
	{
		this.largeLengthOfWord = largeLengthOfWord;
	}

	public void setWordSet(HashSet<String> wordSet)
	{
		this.wordSet = wordSet;
	}

	public void setLargeWeight(double largeWeight)
	{
		this.largeWeight = largeWeight;
	}

	public void setMapWeight(HashMap<String, Double> mapWeight)
	{
		this.mapWeight = mapWeight;
	}

	public HashSet<String> getWord()
	{
		return word;
	}

	public void getAllWordInSentence() throws Exception
	{
		if ((largeLengthOfWord == 0) || (null == wordSet))
			throw new Exception("largeLengthOfWord if equal 0 or null==wordset");
		else
		{
			int sentenceLength = sentence.length();
			for (int i = 0; i < sentenceLength; i++)
			{
				for (int j = sentenceLength; j > i + 1; j--)
				{
					String temp = sentence.substring(i, j);
					if (wordSet.contains(temp))
					{
						word.add(temp);
					}
				}
			}
		}// else
	}

	public ArrayList<VertexType> createVertexList()
	{
		if(null==sentence)
			return null;
	
		//int number = word.size();
		//number += sentence.length() + 2;
		//vertexArray = new VertexType[number];//����ͷ�������
		
		vertexList=new ArrayList<VertexType>();
		
		String sentence="B"+ this.sentence +"E";
		char[] array=sentence.toCharArray();
		
		for(int i=0;i<array.length;i++)
		{
			VertexType vertex=new VertexType(sentence.substring(i, i+1),i);
			//if(!vertexList.contains(vertex))
			//����û����дVertexType��equals�����������Ƚϵ����ڴ��ַ�����д��������壬���һ���ǲ��������������ǽ�����ͼ��ͬλ����ͬ�Ĵʻ���Ҫ���
			//����ͼ�У�Ҳ����Ҫ��Ϊ�ڵ�
				vertexList.add(vertex);
			for(int j=i+2;j<array.length;j++)
			{
				String temp=sentence.substring(i,j);
				if(word.contains(temp))
				{
					VertexType vertex_temp=new VertexType(temp,i);
					//if(!vertexList.contains(vertex_temp))
						vertexList.add(vertex_temp);
				}
			}
		}
		
//		int i = 0;
//		for (; i < 2; i++)
//		{
//			if (0 == i)
//				vertexArray[i] = new VertexType("B");
//			else if (1 == i)
//				vertexArray[i] = new VertexType("E");
//			else
//				System.out
//						.println("there is somethin wrong in createVertexArray function");
//		}
//		for (; i < sentence.length() + 2; i++)
//		{
//			vertexArray[i] = new VertexType(sentence.substring(i - 2, i - 1));
//		}
//		String[] temp = (word.toArray(new String[word.size()]));// ����и����⣬����toString����������Ҫ����
//		for (int j = 0; i < number && j < word.size(); i++, j++)
//		{
//			if (i < number && j < word.size())
//			{
//				vertexArray[i] = new VertexType(temp[j]);
//			}
//			else if (i < number && j >= word.size())
//				System.out
//						.println("something in the function createVertexArray");
//			else if (j < word.size() && i >= number)
//				System.out
//						.println("something in the function createVertexArray");
//		}
		return vertexList;
	}

	public double[][] createGraph()
	{
//		int number=word.size();
//		number+=sentence.length()+2;
//		matrix=new double[number][number];
		
		int number=vertexList.size();
		matrix=new double[number][number];
		//��ʼ���ڽӾ���,��ʼ��Ϊ�����ɴ�
		for(int i=0;i<number;i++)
		{
			for(int j=0;j<number;j++)
			{
				matrix[i][j]=INFINITY;
			}
		}
		
		
		//��ʼ����Ȩֵ
		String sentence="B" + this.sentence +"E";
		VertexType[] vertexArray=vertexList.toArray(new VertexType[vertexList.size()]);//�����toString����������û�������⣬֮�����ٿ���
		
		for(int i=0;i<vertexArray.length;i++)
		{
			String first=vertexArray[i].getToken();
			//ArrayList<String> secondList=new ArrayList<String>();//����beginPosȫ�����
			for(int j=i+1;j<vertexArray.length;j++)
			{
				String second=vertexArray[j].getToken();
				
				if(first.length()==1 && second.length()==1)//���ǵ��ֵ�����£�������Ҫ��Ȩֵ��ƽ������������ͼ�д�ͷ��β�Ŀɴ�·��
				{
					if( (vertexArray[i].getBeginPos()+1) == (vertexArray[j].getBeginPos()) )//���ֻ�Ҫ�ǽ������ڵĲ���
					{
							double weight=getWeight(second + "|" +first);
							if(INFINITY==weight)//��ƽ��
							{
								weight=this.largeWeight*50;
							}
							matrix[i][j]=weight;
					}					
				}
				else//�ǵ����������Ȼ��Ӧ���еıߣ���Ӧ����ƽ����������Ϊwordset��û���־Ͳ�ƽ��
				{
					if( (vertexArray[i].getBeginPos()+first.length()) == (vertexArray[j].getBeginPos()) )
					{
						double weight=getWeight(second + "|" +first);
						if(INFINITY==weight)//��ƽ��
						{
							weight=this.largeWeight*50;
						}
						matrix[i][j]=weight;
					}
				}
			}
		}
	
		return matrix;
	}

	public double getWeight(String word)//�����wordֱ����A|B��ʽ
	{
		double weight=INFINITY;
		if(mapWeight.containsKey(word))
		{
			weight=mapWeight.get(word);
			return weight;
		}
		else
			return weight;
	}
	
	
	public String getSentence() {
		return sentence;
	}

	public double[][] getMatrix() {
		return matrix;
	}

	public ArrayList<VertexType> getVertexList() {
		return vertexList;
	}

	public double getLargeWeight() {
		return largeWeight;
	}

	public int getLargeLengthOfWord() {
		return largeLengthOfWord;
	}

	public HashSet<String> getWordSet() {
		return wordSet;
	}

	public HashMap<String, Double> getMapWeight() {
		return mapWeight;
	}

	public void setMatrix(double[][] matrix) {
		this.matrix = matrix;
	}

	public void setVertexList(ArrayList<VertexType> vertexList) {
		this.vertexList = vertexList;
	}

	public void outputMatrix()
	{
		VertexType[] vertexArray=vertexList.toArray(new VertexType[vertexList.size()]);//�����toArray����������û�������⣬֮�����ٿ���
		for(int i=0;i<matrix.length;i++)
		{
			System.out.print(vertexArray[i].getToken()+"  ");
			for(int j=0;j<matrix.length;j++)
			{

				System.out.print(matrix[i][j]+" ");
			}
			System.out.print("\n");
		}
	}
	public void outputMatrixInCowAndLowNumber()
	{
		VertexType[] vertexArray=vertexList.toArray(new VertexType[vertexList.size()]);//�����toArray����������û�������⣬֮�����ٿ���
		for(int i=0;i<matrix.length;i++)
		{
			System.out.print(vertexArray[i].getToken()+"��"+i+"��             ");
			for(int j=0;j<matrix.length;j++)
			{
				if(INFINITY!=matrix[i][j])
				{
					System.out.print(i+"��"+j+"��                   ");
				}
				
			}
			System.out.print("\n");
		}
	}
	public static void main(String[] args) throws Exception
	{
		// д�����Գ���
		/******************* DataProcess.java���� ****************/
		DataProcess test1 = new DataProcess(new File("test_doStatistic.txt"));
		test1.doStatistic();
		test1.doStatisticWeight();
		System.out.println(test1.getLargeLengthOfWord());
		System.out.println(test1.getTimes());
		System.out.println(test1.getLargeWeight());

		System.out.println("-------------------------------------");
		
		HashSet<String> wordSet = test1.getWordSet();
		for (Iterator<String> iter = wordSet.iterator(); iter.hasNext();)
		{
			System.out.println(iter.next());
		}

		System.out.println("-------------------------------------");
		HashMap<String, Integer> map = test1.getMap();
		Set<Map.Entry<String, Integer>> set = map.entrySet();
		for (Iterator<Map.Entry<String, Integer>> iter = set.iterator(); iter
				.hasNext();)
		{
			Map.Entry map_entry = iter.next();
			System.out.println(map_entry.getKey() + "   "
					+ map_entry.getValue());
		}

		System.out.println("-------------------------------------");
		HashMap<String, Double> mapWeight = test1.getMapWeight();
		Set<Map.Entry<String, Double>> set2 = mapWeight.entrySet();
		for (Iterator<Map.Entry<String, Double>> iter = set2.iterator(); iter
				.hasNext();)
		{
			Map.Entry map_entry = iter.next();
			System.out.println(map_entry.getKey() + "   "
					+ map_entry.getValue());
		}

		System.out.println("-------------------------------------");
		/******************* DataProcess.java���� ****************/

		/******************* SentenceProcessAndCreateGrphic.java���� ****************/
		SentenceProcessAndCreateGrphic test2 = new SentenceProcessAndCreateGrphic(
				"��ͣ���ظ����ظ�");
		test2.setLargeLengthOfWord(test1.getLargeLengthOfWord());
		test2.setWordSet(test1.getWordSet());
		test2.setMapWeight(test1.getMapWeight());
		test2.setLargeWeight(test1.getLargeWeight());
		HashSet<String> word = test2.getWord();

		test2.getAllWordInSentence();
		for (Iterator<String> iter = word.iterator(); iter.hasNext();)
		{
			System.out.println(iter.next());
		}
		System.out.println("-------------------------------------");
		ArrayList<VertexType> vertexList = test2.createVertexList();
		for(Iterator<VertexType> iter=vertexList.iterator();iter.hasNext(); )
		{
			System.out.println(iter.next());
		}
		System.out.println("-------------------------------------");
		/******************* SentenceProcessAndCreateGrphic.java���� ****************/
		
		/******************* createGraph���� ****************/
		test2.createGraph();
		test2.outputMatrixInCowAndLowNumber();
		/******************* createGraph ���� ****************/
		test2.outputMatrix();
		/******************* createGraph ���� ****************/
	}

}

