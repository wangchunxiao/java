import edu.fudan.nlp.keyword.WordExtract;
import edu.fudan.nlp.tag.CWSTagger;

/**
 * 关键词抽取使用示例
 * @author jyzhao,ltian
 *
 */
public class WordExtraction {
	public static WordExtract key = null;; 
	public static CWSTagger seg = null; 

	static {	
		try {
			seg = new CWSTagger("models/seg.c110722.gz");
			key = new WordExtract(seg,"models/StopWord.txt");
		} catch (Exception e) {			
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		System.out.println(key.extract("赵嘉亿是好人还是坏人", 5, true));

		System.out.println(key.extract("甬温线特别重大铁路交通事故车辆经过近24小时的清理工作，26日深夜已经全部移出事故现场，之前埋下的D301次动车车头被挖出运走", 20, true));
	}
}
