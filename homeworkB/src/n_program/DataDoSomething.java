package n_program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DataDoSomething
{
	private File file;
	public DataDoSomething(){}
	public DataDoSomething(File file)
	{
		this.file=file;
	}
	public HashMap<String,Integer> dataProcess() throws Exception
	{
		HashMap<String,Integer> map=new HashMap<String,Integer>();
		if(null==file)
		{
			System.out.println("file is null");
			return null;
		}
		else
		{
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			String temp=null;
			while(null!=(temp=br.readLine()))
			{
				String[] array_string=temp.split(" ");
				for(int i=0;i<array_string.length;i++)
				{
					if(i==0)
					{
						if(array_string[i].matches("[\\pP]"))//判断第一个字符是标点的情况，不添加到map
							continue;
						
						if(!map.containsKey("B|"+array_string[i]))
						{
							map.put("B|"+array_string[i],1);
						}
						else
						{
							int temp_int=map.get("B|"+array_string[i]).intValue();
							map.put("B|"+array_string[i],temp_int+1);
						}
					}
					else if(i==array_string.length-1)
					{
						if(array_string[i].matches("[\\pP]"))//判断最后一个字符是标点的情况，不添加map
							continue;
						
						if(!(map.containsKey(array_string[i]+"|E")))
						{
							map.put(array_string[i]+"|E",1);
						}
						else
						{
							int temp_int=map.get(array_string[i]+"|E").intValue();
							map.put(array_string[i]+"|E",temp_int+1);
						}
					}
					else
					{
						if(array_string[i-1].matches("[\\pP]") && array_string[i].matches("[\\pP]"))
							  continue;
						else if(array_string[i-1].matches("[\\pP]"))
						{
							if(!(map.containsKey("B|"+array_string[i])))
							{
								map.put("B|"+array_string[i],1);
							}
							else
							{
								int temp_int=map.get("B|"+array_string[i]).intValue();
								map.put("B|"+array_string[i],temp_int+1);
							}
						}
						else if(array_string[i].matches("[\\pP]"))
						{
							if(!(map.containsKey(array_string[i-1]+"|E")))
							{
								map.put(array_string[i-1]+"|E",1);
							}
							else
							{
								int temp_int=map.get(array_string[i-1]+"|E").intValue();
								map.put(array_string[i-1]+"|E",temp_int+1);
							}
						}
						else
						{
							if(!(map.containsKey(array_string[i-1]+"|"+array_string[i])))
							{
								map.put(array_string[i-1]+"|"+array_string[i],1);
							}
							else
							{
								int temp_int=map.get(array_string[i-1]+"|"+array_string[i]).intValue();
								map.put(array_string[i-1]+"|"+array_string[i],temp_int+1);
							}
						}
						
						/*
						if(!(map.containsKey(array_string[i-1]+"|"+array_string[i])))
						{
							if(array_string[i-1].matches("[\\pP]") && array_string[i].matches("[\\pP]"))
								continue;
							else if(array_string[i-1].matches("[\\pP]"))
								map.put("B|"+array_string[i],1);
							else if(array_string[i].matches("[\\pP]"))
								map.put(array_string[i-1]+"|E",1);
							else
								map.put(array_string[i-1]+"|"+array_string[i],1);
						}
						else
						{
							int temp_int=map.get(array_string[i-1]+"|"+array_string[i]).intValue();
							map.put(array_string[i-1]+"|"+array_string[i],temp_int+1);
						}
						*/
					}//else					
				}//for
			}//while
			
			br.close();
			return map;
			
		}//else
	}//dataProcess
	public void outputTheFrency(HashMap<String,Integer> map) throws Exception
	{
		FileWriter fw=new FileWriter("frency.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		
		Set<Map.Entry<String,Integer>> set=map.entrySet();
		
		Iterator<Map.Entry<String,Integer>> iter=set.iterator();
		for( ;iter.hasNext(); )
		{
			Map.Entry map_entry=iter.next();
			bw.write((String)map_entry.getKey()+" "+(Integer)map_entry.getValue()+"\n");
		}
		bw.close();
	}
	
	public static void main(String[] args) throws Exception
	{
		DataDoSomething test=new DataDoSomething(new File("NiuTrans.txt"));
		HashMap<String,Integer> map=test.dataProcess();
		test.outputTheFrency(map);
		System.out.println("finish");
	}
}
