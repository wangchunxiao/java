/*
 * 文件名：SimpleFileReader.java
 * 版权：Copyright 2008-20012 复旦大学 All Rights Reserved.
 * 描述：
 * 修改人：xpqiu
 * 修改时间：2009 Sep 2, 2009 6:19:22 PM
 * 修改内容：新增
 *
 * 修改人：〈修改人〉
 * 修改时间：YYYY-MM-DD
 * 跟踪单号：〈跟踪单号〉
 * 修改单号：〈修改单号〉
 * 修改内容：〈修改内容〉
 */
package edu.fudan.ml.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import edu.fudan.ml.types.Instance;

/**
 * @author xpqiu
 * @version 1.0
 * SimpleFileReader
 * 简单文件格式如下：
 * 类别 ＋ “空格” ＋ 数据
 * package edu.fudan.ml.data
 */
public class SimpleFileReader extends Reader {

	String content = null;
	BufferedReader reader;
	public SimpleFileReader(String file){
		try {
			File f = new File(file);
			FileInputStream in = new FileInputStream(f);
			reader = new BufferedReader(new InputStreamReader(in,
					"UTF-8"));
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public boolean hasNext() {
		try {
			content = reader.readLine();
			if(content==null){
				reader.close();
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
			
		}
		return true;
	}
	
	public Instance next() {
		int idx = content.indexOf(" ");
		if(idx==-1){
			System.err.println("数据格式不对");
			System.err.println(content);
			return null;
		}
		return new Instance (content.substring(idx+1), content.substring(0, idx));
	}

}
