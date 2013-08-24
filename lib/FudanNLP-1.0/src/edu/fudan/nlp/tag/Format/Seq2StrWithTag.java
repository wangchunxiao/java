package edu.fudan.nlp.tag.Format;

import java.util.List;

import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.InstanceSet;

/**
 * 将序列标注转换成字符串标签
 * 
 * @author xpqiu
 * 
 */
public class Seq2StrWithTag {

	public static String format(InstanceSet testSet, String[][] labelsSet) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < testSet.size(); i++) {
			Instance inst = testSet.getInstance(i);
			String[] labels = labelsSet[i];
			sb.append(format(inst, labels));
		}
		return sb.toString();
	}

	public static String format(Instance inst, String[] labels) {
		String[][] data = (String[][]) inst.getSource();

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < data.length; j++) {
			String label = labels[j];
			int tagidx = label.indexOf("-");
			String tag = label.substring(tagidx + 1);
			label = label.substring(0, tagidx);
			String w = data[j][0];
			sb.append(w);
			if (label.equals("E") || label.equals("S")) {
				sb.append("/" + tag + " ");
			}
		}
		return sb.toString();
	}
}
