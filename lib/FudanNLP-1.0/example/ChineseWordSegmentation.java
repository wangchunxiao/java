

import edu.fudan.nlp.tag.CWSTagger;

/**
 * 分词使用示例
 * @author xpqiu
 *
 */
public class ChineseWordSegmentation {
	/**
	 * @param args
	 * @throws IOException 
	 * @throws  
	 */
	public static void main(String[] args) throws Exception {
		CWSTagger tag = new CWSTagger("./models/seg.c110722.gz");
		String str = "加字是我给你设计一张含你所需要的字的图纸，字是需要你自己绣上去的哦，十字绣是一种自己动手的艺术，抱枕上需要加上新人的名字或结婚日期等有意义的文字吗？10个字以内只需要10元，你是王秋雨吗？";
		String s = tag.tag(str);
		System.out.println(s);
		
	}

}
