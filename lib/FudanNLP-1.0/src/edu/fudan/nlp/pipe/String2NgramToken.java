package edu.fudan.nlp.pipe;

import java.io.Serializable;
import java.util.ArrayList;
import edu.fudan.ml.types.Instance;

/**
 * 将字符串转为ngram记号序列
 * @author xpqiu
 *
 */
public class String2NgramToken extends Pipe implements Serializable {

	private static final long serialVersionUID = -3622451640920544061L;
	int[] gramSizes = null;

	public String2NgramToken(int[] sizes) {
		this.gramSizes = sizes;
	}

	public void addThruPipe(Instance instance) {
		String data = (String) instance.getData();
		
		// 提取ngram
		ArrayList<String> list = new ArrayList<String>();
		for (int j = 0; j < gramSizes.length; j++) {
			int len = gramSizes[j];
			if (len <= 0 || len > data.length())
				continue;
			for (int i = 0; i < data.length() - len; i++) {
				list.add(data.substring(i, i + len));

			}
		}
		instance.setData(list);
	}
	
}