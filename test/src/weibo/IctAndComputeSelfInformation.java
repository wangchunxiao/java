package weibo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import kevin.zhang.NLPIR;

	public class IctAndComputeSelfInformation
	{
		private ProcessData data;
		private int wordNums;
		private HashMap<String,Integer> wordTimesMap;
		private HashMap<String,Double> wordFrequency;
		
		
		public IctAndComputeSelfInformation(File file) throws IOException
		{
			this.data=new ProcessData(file);
			this.data.prcess();
			this.wordNums=0;
			this.wordFrequency=new HashMap<String,Double>();
			this.wordTimesMap=new HashMap<String,Integer>();
		}
		
		public void IctData() throws UnsupportedEncodingException
		{
			NLPIR testNLPIR = new NLPIR();
			String argu = "";		
			if (testNLPIR.NLPIR_Init(argu.getBytes("GB2312"),1) == false)
			{
				System.out.println("Init Fail!");
				return;
			}
			
			ArrayList<WeiboInfoNew> weiboList=data.getWeiboListNew();
			for(WeiboInfoNew i:weiboList)
			{
				byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(i.getArticle().getBytes("GB2312"), 0);
				String temp=new String(nativeBytes, 0, nativeBytes.length, "UTF-8");
				String result=temp.replaceAll("[^\u4E00-\u9FA5^\\s]","");
				String[] array=result.split("[\\s]+");
				
				ArrayList<String> list=new ArrayList<String>();
				for(String j:array)
				{
					list.add(j);//将词语加入ArrayList 这个list是我们在WeiboInfo中记录每条微博分词结果的list
					this.wordNums++;//总词语数量++
					
					//将词语数量加入统计词语出现次数的map
					if(wordTimesMap.containsKey(j))
					{
						int times=wordTimesMap.get(j).intValue();
						times++;
						wordTimesMap.put(j,times);					
					}
					else if(!wordTimesMap.containsKey(j))
					{
						wordTimesMap.put(j,1);
					}
				}
				
				i.setIctArticle(list);
			}	
			
			testNLPIR.NLPIR_Exit();
		}

		public void computeFrequencyAndSelfInformation() throws UnsupportedEncodingException
		{
			if(this.wordNums==0)//判断是否已经执行过IctData函数
			{
				this.IctData();
			}
			
			Set<Map.Entry<String,Integer>> set=wordTimesMap.entrySet();
			for(Iterator<Map.Entry<String,Integer>> iter=set.iterator();iter.hasNext();)
			{
				Map.Entry<String,Integer> map_entry=iter.next();
				int times=map_entry.getValue().intValue();
				double frequency=(double)times/this.wordNums;
				wordFrequency.put(map_entry.getKey(),frequency);
			}
			
			ArrayList<WeiboInfoNew> weiboListNew=data.getWeiboListNew();
			
			double u=0.3;
			for(WeiboInfoNew i:weiboListNew)
			{
				double selfInformation=this.computeSelfInformation(i.getIctArticle());
				i.setSelfInformation(selfInformation);
				i.setEnergy( (Math.exp( -1*i.getTimeSubstraction() ) )*selfInformation*( (1-u)*( 1+i.getTransmit() )+u*( 1+i.getDiscuss())));
			}
			
			
		}
		
		public ProcessData getData()
		{
			return data;
		}

		public int getWordNums()
		{
			return wordNums;
		}
		
		private  double computeSelfInformation(ArrayList<String> list)
		{
					
			double selfInformation=0.0;
			for(String i:list)
			{
				double probablity=this.wordFrequency.get(i).doubleValue();
				double temp=Math.log10(probablity)*(-1);
				selfInformation=selfInformation+temp;
			}
			return selfInformation;
		}
		
		public void outputReuslt(File file) throws IOException
		{
			
			if(this.wordNums==0)
				this.IctData();
			
			if(this.wordFrequency.isEmpty())
				this.computeFrequencyAndSelfInformation();
			
			BufferedWriter bw=new BufferedWriter(new FileWriter(file));
			
			ArrayList<WeiboInfoNew> weiboListNew=data.getWeiboListNew();
			for(WeiboInfoNew i:weiboListNew)
			{
				bw.write(i+"\n");
			}
			
			bw.close();
			
		}
}
