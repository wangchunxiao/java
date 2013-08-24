package edu.fudan.nlp.keyword;

import java.text.DecimalFormat;
import java.util.*;

import edu.fudan.nlp.tag.CWSTagger;
import edu.fudan.util.MyArrays;

/**
 * 本类计算各词的权重
 * 并返回权重最高的若干词作为关键词
 * @author ltian
 *
 */

class WordExtractDataSet {
	public double[][] dataMap;
	public double[] w;
	
	public List<String> subList;
	
	public WordExtractDataSet(double[][] dataMap, double[] w) {
		this.dataMap = dataMap;
		this.w = w;
	}
}

public class WordExtract{
	private CWSTagger tag;
	private ToWord test;
	/**
	 * 权重收敛的默认阈值
	 */
	private double precision = 0.001;//权重收敛的默认阈值
	/**
	 * 阻尼参数
	 */
	private double dN = 0.85;  //阻尼参数
	/**
	 * 窗体大小
	 */
	private int windowN = 10; //窗体大小

	
	/**
	 *设置窗口大小 和阻尼系数
	 */
	public void setN(int windowN,double dN){
		this.windowN = windowN;
		this.dN = dN;
	}

	/**
	 * 将权重收敛的阈值设小
	 * 算出来的关键词更精确
	 */
	public void setPrecisionHigh() {
		this.precision = 0.000000001;
	}

	/**
	 * 将权重收敛的阈值设大
	 * 算出来的关键词粗糙，但速度更快
	 */
	public void setPrecisionLow() {
		this.precision = 0.1;
	}

	/**
	 * 将权重收敛的阈值设为默认
	 */
	public void setPrecisionDefault() {
		this.precision = 0.001;
	}

	/**
	 * 
	 * @param precision
	 *       权重收敛的阈值
	 */
	public void setPrecision(double precision){
		this.precision = precision;
	}

	/**
	 * 构造函数
	 * @param segPath
	 *        要处理文字的地址
	 * @param dicPath
	 *        stopword的地址
	 * @throws Exception 
	 */
	public WordExtract(String segPath, String dicPath) throws Exception{
		tag = new CWSTagger(segPath);
		test = new ToWord(dicPath);
	}

	/**
	 * 构造函数
	 * @param tag
	 * @param dicPath
	 *       stopword的地址
	 */
	public WordExtract(CWSTagger tag, String dicPath){
		this.tag = tag;
		test = new ToWord(dicPath);
	}

	/**
	 * 读入要处理的文字
	 * 过滤掉stopword
	 * 得到List类型的数据
	 * @param str
	 */
	private List<List<String>> getWord(String str){
		Set<String> set=new TreeSet<String>();
		List<String> sublist=new ArrayList<String>();
		String s = tag.tag(str);		
		List<String> list = test.phraseDel(s);		
		String temp;
		for(int i = 0; i < list.size(); i++){
			temp = list.get(i);
			set.add(temp);
		}		
		Iterator<String> ii = set.iterator();
		while(ii.hasNext()){
			sublist.add(ii.next());
		}
		List<List<String>> rl = new ArrayList<List<String>>();
		rl.add(list);
		rl.add(sublist);
		return rl;
	}


	/**
	 * 建立有权重的有向图
	 * @param windowN
	 *        窗口大小
	 */
	private WordExtractDataSet mapInit(int windowN, List<List<String>> rl){
		List<String> list = rl.get(0);
		List<String> sublist= rl.get(1);
		//用于加速
		TreeMap<String, Integer> subMap = new TreeMap<String, Integer>();
		for(int i = 0; i < sublist.size(); i++)
			subMap.put(sublist.get(i), i);
		
		int len = sublist.size();
		
		/**
		 * 有权重的有向图
		 */
		double[][] dataMap = new double[len][len];
		
		/**
		 * 各词的权重
		 */
		double[] w = new double[len];
		
		/**
		 * 临时变量
		 */
		double[] wBack = new double[len];
		
		String s;
		for(int i = 0;i < sublist.size();i++)
			w[i]=1.0;
		for(int i = 0;i < list.size() - windowN;i++){
			s = list.get(i);
			//int temp1 = sublist.indexOf(s);
			int temp1 = subMap.get(s);
			for(int j = i+1; j <i + windowN ;j++){
				s = list.get(j);
				//int temp2 = sublist.indexOf(s);
				int temp2 = subMap.get(s);
				dataMap[temp1][temp2]++;
			}
		}
		
		return new WordExtractDataSet(dataMap, w);
	}

	/**
	 * 计算节点a的出度边的权重和
	 * @param a
	 *       需要计算的节点
	 * @return
	 *       权重和
	 */
	private double outV(int a, int subListLen, double[][] dataMap){
		double sum = 0;
		for(int k = 0;k < subListLen; k++)
			sum += dataMap[a][k];
		return sum;
	}

	/**
	 * 根据算法计算各节点的权重
	 * @param dN
	 *       阻尼系数
	 */

	private WordExtractDataSet wCal(double dN, int subListLen, WordExtractDataSet weds){
		double sumTemp;
		double[] wBack = new double[subListLen];
		for(int k=0; ;k++){
			sumTemp = 0.0;
			for(int i = 0;i < subListLen; i++)
				wBack[i] = weds.w[i];
			//double s;
			for(int i = 0;i < subListLen; i++){
				double s = 0.0;
				for(int j = 0;j < subListLen;j++){
					double temp = outV(j, subListLen, weds.dataMap);
					if(temp != 0 && weds.dataMap[j][i] != 0)
						s += (weds.dataMap[j][i]*weds.w[j])/temp;
					//System.out.println(s);
				}
				weds.w[i] = 1 - dN + dN*s;
				//System.out.println(w[i]);
			}
			for(int i = 0; i < subListLen; i++){
				sumTemp += Math.abs(weds.w[i] - wBack[i]);
				//System.out.println(sumTemp);
			}
			if(sumTemp <= precision){
//				System.out.println("Iteration Times: "+k);
				break;
			}
		}
		
		return weds;
	}

	/**
	 * 选取权重最高的num个词
	 * @param num
	 *       需要选取关键词的数目
	 * @return
	 *       关键词的数目
	 */
	private int[] selectTop(int num, WordExtractDataSet weds){
		double max;
		int temp;
		int subListLen = weds.subList.size();
		double[] wBack = new double[subListLen];
		
		num = Math.min(num,subListLen);
		/**
		 * 若干最高权重词的序号
		 */
		int[] topNum = new int[num];
		for(int i = 0;i < subListLen; i++)
			wBack[i] = weds.w[i];
		for(int i = 0; i < num; i++){
			max = -1;
			temp = 0;
			for(int j = 0;j < subListLen; j++){
				if(wBack[j] > max){
					max = wBack[j];
					temp = j;
				}
			}
			topNum[i] = temp;
			wBack[temp] = -2;
		}	
		return topNum;
	}
	
	/**
	 * 
	 * @param num
	 *       需要抽取的关键词的数目
	 * @param isWeighted
	 *        是否带权重
	 * @return 返回选取的关键词
	 */
	private ArrayList<String> toList(int num, boolean isWeighted, WordExtractDataSet weds){
		//计算实际数目
		int[] topNum = selectTop(num, weds);
		DecimalFormat df = new DecimalFormat("0.##");
		//StringBuilder sb = new StringBuilder();
		ArrayList<String> al = new ArrayList<String>();
		for(int i = 0;i < topNum.length;i++){
			int temp = topNum[i];
//			if(i>0){
//				sb.append("\n");
//			}
			//sb.append(weds.subList.get(temp));
			String stemp = weds.subList.get(temp);
			if(isWeighted){
				//sb.append(" / ");
				stemp += " / ";
				//sb.append(df.format(weds.w[temp]));
				stemp += df.format(weds.w[temp]);
			}
			al.add(stemp);
		}
		//return sb.toString();
		return al;
	}
	
	/**
	 * 计算个节点的权值
	 * 选取若干关键词
	 * @param str
	 *       需要处理的文字
	 */
	private WordExtractDataSet process(String str){
		int N = windowN;
		List<List<String>> rl = getWord(str);
		int len = rl.get(0).size();

		while(N > len/2)
			N = N/2;
		if(N < 2)
			N = 2;

		WordExtractDataSet weds = mapInit(N, rl);
		weds = wCal(dN, rl.get(1).size(), weds);

		//w归一化
		MyArrays.normalize(weds.w);
		
		weds.subList = rl.get(1);
		return weds;
	}

	public ArrayList<String> extract(String str, int num, boolean isWeighted){
		WordExtractDataSet weds = process(str);
		 return toList(num, isWeighted, weds);
	}
}


