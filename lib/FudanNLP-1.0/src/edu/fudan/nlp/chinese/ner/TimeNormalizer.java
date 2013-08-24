package edu.fudan.nlp.chinese.ner;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import edu.fudan.ml.classifier.Classifier;
import edu.fudan.ml.types.Instance;
import edu.fudan.ml.types.Results;

/*
 * 功能扩展说明:
1.加入一定的上下文相关逻辑,将根据上下文以及当前时间对表达时所代表的时间进行推测。
如对于一段话：2008年5月1日......6月7号......今天.....
将提取出：2008-5-1,2008-6-7,2010-5-4(当前时间)

2.增加了对待提取字符的预处理模块，以适应不同的输入习惯。
目前的预处理功能主要如下：
(1).空白符去除功能。该功能可以广泛适应不同带空格的输入方式使之正确识别以更好适应用户习惯，并且减少了正则模板的复杂性。
(2).语气助词去除功能。目的同上，目前仅用于去除"的"。扩展性良好。
(3).大写数字转化功能。就是将汉字表示的数字转化为阿拉伯数字的功能，具体参见《大写数字转化模块功能说明》，该功能为语音识别提供扩展性。

3.扩大了时间表达式的识别范围，主要内容如下：
(1).增加了省略"分"、"秒"的时间表达式识别，如17点32，15点19分14
(2).增加对分钟的常用说法"一刻"、"半"、"三刻"的正确识别。
(3).增加对固定形式时间表达式 小时：分钟 的识别。
(4).增加对固定形式时间表达式 年.月.日 的识别。
具体可参见下文中正则表达式修改部分。

4.增加对关键字"中午,午间,下午,午后,晚上,傍晚,晚间,pm"的识别以正确判断当前时间，规约如下：
(1).中午/午间0-10点视为12-22点
(2).下午/午后0-11点视为12-23点
(3).晚上/傍晚/晚间1-11点视为13-23点，12点视为0点
(4).0-11点pm视为12-23点

5.增加对当前关联时间的正确判断与计算，如"今天、明天、大后天、上个月、去年、今早、明晚"等常用说法。

6.支持星期关联的正确识别和计算，如"上周三，下星期天"等常用说法。

7.支持X天后，X月后等说法的正确计算。

清除bug:
1.清除了只能识别两位小时数的bug。

正则模板修改：
1.增加表达式支持：
(前|昨|今|明|后)(天|日)?(早|晚)(晨|上|间)?
\d+个[年月日天][以之]?[前后]
大+(前|后)天
下(个)?月
\d+个?半?(小时|钟头|h|H)
半个?(小时|钟头)
\d+(分钟|min)
[13]刻钟
这个月
午后
(早|晚)?(\d+[时点](\d+)?分?(\d+秒?)?)\s*(am|AM|pm|PM)?
(早|晚)?([0-2]?[0-9](点|时)[13一三]刻)(am|AM|pm|PM)?
(早|晚)?([0-2]?[0-9](点|时)半)(am|AM|pm|PM)?
(上|这|本|下)+(周|星期)([一二三四五六七天日]|[1-7])?
(周|星期)([一二三四五六七天日]|[1-7])
[0-9]?[0-9]?[0-9]{2}\.((10)|(11)|(12)|([1-9]))\.((?<!\\d))([0-3][0-9]|[1-9])

2.修改表达式：
\d+:\d+(:\d+)* 修改为 (早|晚)?(\d+[:：]\d+([:：]\d+)*)\s*(am|AM|pm|PM)?

3.删除表达式：
今年(\d+)      (会引起无法识别今年X月X日的bug)
([零一二三四五六七八九十百千万]+|\d+)刻
 */

/**
 * 新版时间表达式识别的主要工作类，改进了timebase的工作方式以提高识别准确率，
 * 并支持获得推测后时间和推测前时间两种时间信息
 * 
 * @author 邬桐 072021156
 * @author 曹零07300720158
 * @version 1.1 2010-4-28
 */
public class TimeNormalizer implements Classifier, Serializable {

	private static final long serialVersionUID = 463541045644656392L;
	private String timeBase;
	private String oldTimeBase;
	private static Pattern patterns = null;
	private String target;
	private TimeUnit[] timeToken = new TimeUnit[0];

	public TimeNormalizer() {
	}

	public TimeNormalizer(String path){
		if(patterns == null){
			try {
				patterns = readModel(path);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.print("Read model error!");
			}
		}
	}
	/**
	 * TimeNormalizer的构造方法，根据提供的待分析字符串和timeBase进行时间表达式提取
	 * 在构造方法中已完成对待分析字符串的表达式提取工作
	 * 
	 * @param target 待分析字符串
	 * @param timeBase 给定的timeBase
	 * @return 返回值
	 */
	public TimeUnit[] parse(String target,String timeBase){
		this.target = target;
		this.timeBase = timeBase;
		this.oldTimeBase = timeBase;
		preHandling();//字符串预处理
		timeToken = TimeEx(this.target,timeBase);
		return timeToken;
	}

	/**
	 * 同上的TimeNormalizer的构造方法，timeBase取默认的系统当前时间
	 * 
	 * @param target 待分析字符串
	 * @return 时间单元数组
	 */
	public TimeUnit[] parse(String target){
		this.target = target;
		this.timeBase = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(Calendar.getInstance().getTime());
		this.oldTimeBase = timeBase;
		preHandling();//字符串预处理
		timeToken = TimeEx(this.target,timeBase);
		return timeToken;
	}

	//


	/**
	 * timeBase的get方法
	 * 
	 * @return 返回值
	 */
	public String getTimeBase(){
		return timeBase;
	}

	/**
	 * oldTimeBase的get方法
	 * 
	 * @return 返回值
	 */
	public String getOldTimeBase(){
		return oldTimeBase;
	}

	/**
	 * timeBase的set方法
	 * 
	 * @param s timeBase
	 */
	public void setTimeBase(String s){
		timeBase = s;
	}

	/**
	 * 重置timeBase为oldTimeBase
	 * 
	 */
	public void resetTimeBase(){
		timeBase = oldTimeBase;
	}

	/**
	 * 时间分析结果以TimeUnit组的形式出现，此方法为分析结果的get方法 
	 * 
	 * @return 返回值
	 */
	public TimeUnit[] getTimeUnit(){
		return timeToken;
	}

	/**
	 *构建正则表达式
	 *
	 *从"TimeExp-Rules.txt"文件中抽取时间表达式相关的正则表达式，
	 *将所有正则表达式整合，构成一个完整的正则表达式用于识别文本中的时间表达式
	 *
	 * @param none 该方法没有参数
	 * @return String 返回整合后的正则表达式
	 * @throws IOException 文件读取错误时抛出
	 * 
	 */

	/**
	 * 待匹配字符串的清理空白符和语气助词以及大写数字转化的预处理
	 */
	private void preHandling(){
		target = stringPreHandlingModule.delKeyword(target, "\\s+"); //清理空白符
		target = stringPreHandlingModule.delKeyword(target, "[的]+"); //清理语气助词
		target = stringPreHandlingModule.numberTranslator(target);//大写数字转化
	}

	/**
	 *有基准时间输入的时间表达式识别
	 *
	 *这是时间表达式识别的主方法，
	 *通过已经构建的正则表达式对字符串进行识别，并按照预先定义的基准时间进行规范化
	 *将所有别识别并进行规范化的时间表达式进行返回，
	 *时间表达式通过TimeUnit类进行定义
	 *
	 *
	 * @param String 输入文本字符串
	 * @param String 输入基准时间
	 * @return TimeUnit[] 时间表达式类型数组
	 * 
	 */
	private TimeUnit[] TimeEx(String tar,String timebase)
	{
		Matcher match;
		int startline=-1,endline=-1;

		String [] temp = new String[99];
		int rpointer=0;
		TimeUnit[] Time_Result = null;

		match=patterns.matcher(tar);	
		boolean startmark=true;
		while(match.find())
		{
			startline=match.start();
			if (endline==startline) 
			{
				rpointer--;
				temp[rpointer]=temp[rpointer]+match.group();
			}
			else
			{
				if(!startmark)
				{
					rpointer--;
					//System.out.println(temp[rpointer]);
					rpointer++;	
				}	
				startmark=false;
				temp[rpointer]=match.group();
			}
			endline=match.end();
			rpointer++;
		}
		if(rpointer>0)
		{
			rpointer--;
			//System.out.println(temp[rpointer]);
			rpointer++;
		}
		Time_Result=new TimeUnit[rpointer];
		//	System.out.println("Basic Data is " + timebase); 
		for(int j=0;j<rpointer;j++)
		{
			Time_Result[j]=new TimeUnit(temp[j],this);
			//System.out.println(result[j]);
		}

		return Time_Result;
	}

	/**
	 * 输出模型
	 * @param inText
	 * @param outBin
	 * @throws IOException
	 */
	public void text2binModel(String inText,String outBin) throws IOException {
		ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream (
				new GZIPOutputStream (new FileOutputStream(outBin))));
		String rules = loadtxt(inText);
		Pattern p = Pattern.compile(rules);
		out.writeObject(p);
		out.close();
	}

	private Pattern readModel(InputStream is) throws Exception{
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream 
				(new GZIPInputStream (is)));
		return readModel(in);
	}
	private Pattern readModel(String file) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream 
				(new GZIPInputStream (new FileInputStream(file))));
		return readModel(in);
	}

	private Pattern readModel(ObjectInputStream in) throws Exception {

		Pattern p = (Pattern) in.readObject();
		return p=Pattern.compile(p.pattern());
	}

	private String loadtxt(String path){
		String rules = "";
		try {		
			InputStreamReader  read = new InputStreamReader (new FileInputStream(path),"utf-8");
			BufferedReader bin = new BufferedReader(read);
			String _ruleunit = bin.readLine();
			while (_ruleunit!=null)
			{
				if (!_ruleunit.startsWith("-"))
				{
					if(rules.equals(""))
						rules=rules+"("+_ruleunit+")";
					else
						rules=rules+"|("+_ruleunit+")";
				}	
				_ruleunit=bin.readLine();
			}
		}catch(Exception e){
			System.out.println("正则表达式文件未找到！");
		}
		return rules;
	}

	public Results predict(Instance instance) {
		return null;
	}

}










