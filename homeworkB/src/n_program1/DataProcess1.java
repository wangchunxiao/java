package n_program1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DataProcess1
{

	private static final double INFINITY = 100000.0;// ��ʾͼ�е������㲻��ͨ
	private File file;
	int times;
	double largeweight;

	public DataProcess1()
	{
	}

	public DataProcess1(File file)
	{
		this.file = file;
		times = 0;
		largeweight = 0.0;
	}

	public HashMap<String, Integer> doStatistic() throws Exception
	{
		if (null != file)
		{
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String temp;
			while (null != (temp = br.readLine()))
			{
				String[] words = temp.split(" ");//����������������õģ�һ���ո�
				//String[] words = temp.split("  ");//����΢������õģ������ո�
				for (String word : words)
				{
					if (!map.containsKey(word))
					{
						map.put(word, 1);
						times++;
					}
					else
					{
						int inttemp = map.get(word).intValue();
						map.put(word, inttemp + 1);
						times++;
					}
				}
			}
			return map;
		}
		else
		{
			throw new Exception("file is null");
		}
	}

	public HashMap<String, Double> StatisticFrency(HashMap<String, Integer> map)
			throws Exception
	{
		if (map != null)
		{
			HashMap<String, Double> mapFrency = new HashMap<String, Double>();

			Set<Map.Entry<String, Integer>> set = map.entrySet();
			Iterator<Map.Entry<String, Integer>> itor = set.iterator();
			for (; itor.hasNext();)
			{
				Map.Entry<String, Integer> map_entry = itor.next();
				int temp_int = map_entry.getValue();
				double temp_double = (double) temp_int / times;
				temp_double = Math.log(temp_double) * (-1);
				mapFrency.put(map_entry.getKey(), temp_double);

				largeweight = (largeweight > temp_double) ? largeweight
						: temp_double;
			}
			return mapFrency;
		}
		else
		{
			throw new Exception("map is null in function StatisticFrency");
		}

	}

	public void output(HashMap<String, Double> map, File f) throws Exception
	{
		FileWriter fw = new FileWriter(f);
		BufferedWriter bw = new BufferedWriter(fw);

		Set<Map.Entry<String, Double>> set = map.entrySet();
		for (Iterator<Map.Entry<String, Double>> itor = set.iterator(); itor
				.hasNext();)
		{
			Map.Entry map_entry = itor.next();
			fw.write(map_entry.getKey() + " " + map_entry.getValue() + "\n");
		}
	}

	public void outputLargeWeight()
	{
		System.out.println(largeweight);
	}

	public double[][] getMatrix(HashMap<String, Double> map, String sentence)
	{
		int length = sentence.length();
		double[][] matrix = new double[length + 1][length + 1];
		Set<String> set = map.keySet();

		for (int i = 0; i < length; i++)
		{
			for (int j = 0; j < length; j++)
				matrix[i][j] = INFINITY;
		}

		for (int i = 0; i <= length; i++)
		{
			for (int j = i + 1; j <= length; j++)
			{
				if (j == i + 1)
				{
					String word = sentence.substring(i, j);
					double weigth = getWigth(word, map, set);
					if (INFINITY == weigth)
					{
						matrix[i][j] = largeweight * 10.0;
					}
					else
						matrix[i][j] = weigth;
				}
				else
				{
					String word = sentence.substring(i, j);
					double weigth = getWigth(word, map, set);
					matrix[i][j] = weigth;
				}

			}
		}
		return matrix;
	}

	public double getWigth(String word, HashMap<String, Double> map,
			Set<String> set)
	{
		double weigth = INFINITY;

		if (set.contains(word))
		{
			weigth = map.get(word);
			return weigth;
		}
		else
			return weigth;
	}

	// Ĭ���Ǵ�0�Ŷ��㿪ʼ�ģ������������Ҫ��һ���͵Ͻ�˹������ͬ�ĳ�ʼ����������path������
	public boolean[][] dijkstraShotrest(double[][] matrix, String sentence)
	{
		int sentenceLength=sentence.length();
		
		boolean[][] path = new boolean[sentenceLength + 1][sentenceLength + 1];//PΪ·������p[u][y]Ϊtrue,���ʾ�Ӷ���0��u�����·��Ҫ����y
		double[] distance = new double[sentenceLength + 1];
		boolean[] ifFinal = new boolean[sentenceLength + 1];

		
		// ��һ��ѭ����ʼ���������������
		for (int i = 0; i < sentenceLength + 1; i++)
		{
			ifFinal[i] = false;
			distance[i] = matrix[0][i];
			for (int j = 0; j < sentenceLength + 1; j++)
			{
				path[i][j] = false;
			}

			if (distance[i] < INFINITY)
			{
				path[0][i] = true;
			}
		}

		//��ʼ��ѭ��
		for (int i = 1; i < sentenceLength + 1; i++)
		{
			double min = INFINITY;
			int v=-1;
			for (int j = 1; j < sentenceLength + 1; j++)//Ѱ����̵�·����
			{
				if (!ifFinal[j])
				{
					if (distance[j] < min)
					{
						min = distance[j];
						v=j;
					}
				}
			}
			ifFinal[v]=true;
			for(int j=1;j<sentenceLength+1;j++)//�ҵ���̵ĵ�֮�����·��
			{
				if(!ifFinal[j] && (min+matrix[v][j])<distance[j])
				{
					distance[j]=min+matrix[v][j];
					for(int k=0;k<sentenceLength+1;k++)//�������·������Ҫ������Ǹ���̵��·����������µ��·������һ��
					{
						path[j][k]=path[v][k];
					}
					path[j][v]=true;
				}
			}
		}

		
		//����Ҫ������˵���Ǹ��ƵĶദ��һ������Ϊpath[u][k]��u=kʱ��ʾ�Ӷ���0������u�����·��·�����Լ�������Ȼ����ȷ��
		//��������֮ǰ�Ĵ���û�������
		for (int i = 0; i < sentenceLength + 1; i++)
		{
			for (int j = 0; j < sentenceLength + 1; j++)
			{
				if(i==j)
					path[i][j] = true;
			}
		}
		return path;
	}
	
	public String getResult(boolean[][] path,String sentence)
	{
		int length=sentence.length();
		String result=new String();
		int begin=0;
		for(int k=0;k<length+1;k++)
		{
			if(path[length][k])
			{
				String temp=sentence.substring(begin,k);
				begin=k;
				
				//��������������Ϊ�������Ǿ���ĩβ�ġ�/����ȥ��
				if(length!=k)
					result+=temp+"/";//������Ǵ����õģ���'/'���ָ�
					//result+=temp+"  ";//����΢������õ�
				else
					result+=temp;
			}
		}
		return result;
	}
	
	public String getInput() throws IOException
	{
		String input=new String();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));		
		input=br.readLine();
		
		//br.close();		
		return input;
	}

	public static void main(String[] args) throws Exception
	{
		DataProcess1 test = new DataProcess1(new File("NiuTrans.txt"));
		HashMap<String, Integer> map;
		HashMap<String, Double> mapFrency;

		map = test.doStatistic();
		mapFrency = test.StatisticFrency(map);

//		test.output(mapFrency, new File("һԪ�������ȡ������.txt"));
//		test.outputLargeWeight();
//		System.out.println("finish  һԪ�������ȡ������.txt");

//		double[][] matrix;
//		matrix = test.getMatrix(mapFrency, "������˵��ȷʵ����.");
//		
//		boolean[][] path=test.dijkstraShotrest(matrix,"������˵��ȷʵ����.");
//		
//
//		String result=test.getResult(path,"������˵��ȷʵ����.");
//		System.out.println(result);
		
		System.out.println("������ʵ����ӣ��Կս�����");
		
		String input=test.getInput();
		while(null!=input)
		{
			double[][] matrix;
			matrix = test.getMatrix(mapFrency, input);			
			boolean[][] path=test.dijkstraShotrest(matrix,input);
			
			String result=test.getResult(path,input);
			System.out.println(result);
			
			input=test.getInput();
		}	
	}
}
