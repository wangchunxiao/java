package edu.fudan.nlp.keyword;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.String;
import java.util.*;

/**
 * 本类主要功能是过滤停用词
 * @author ltian
 *
 */

public class ToWord {
	//String data[] = new String[100000];

	//List<String> sWord = new ArrayList<String>();
	TreeSet<String> sWord = new TreeSet<String>();

	//public int length;

	/**
	 * 构造函数
	 * @param dicPath
	 *        stopword所在地址
	 */

	public ToWord(String dicPath) {
		try {
			readStopWord(dicPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 读取stopword
	 * @param dicPath
	 *       stopword所在地址
	 * @throws FileNotFoundException
	 */

	public void readStopWord(String dicPath) throws FileNotFoundException{


		try{
			InputStreamReader read = new InputStreamReader(new FileInputStream(dicPath), "UTF-8");
			BufferedReader in = new BufferedReader(read);
			String s;
			while ((s = in.readLine()) != null){ 
				sWord.add(s);
			}
			in.close();
		}catch (Exception e) {

		}
	}

	/**
	 * 删除stopword
	 * 将string字符串转换为List类型，并返回
	 * @param str
	 *       要进行处理的字符串 
	 * @return
	 *       删除stopword后的List类型
	 */

	public List<String> phraseDel(String str){
		List<String> list = new ArrayList<String>(); 
		//data = str.split(" ");
		List<String> listTemp = new ArrayList<String>(); 
		listTemp = Arrays.asList(str.split(" "));
		String s;
		//System.out.println("StopWords: "+sWord);		
		//Iterator<String> ii = list.iterator();
		int length= listTemp.size();
		for(int i = 0; i < length; i++){
			s = listTemp.get(i);
			if(s.length() >= 2 && sWord.contains(s) == false)
				list.add(s);
		}
		return list;
	}
}
