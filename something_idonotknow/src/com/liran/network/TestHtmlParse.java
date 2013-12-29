package com.liran.network;


import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

public class TestHtmlParse
{
	public static void extractKeyWordText(String url, String keyword) {
		try {
            
				//生成一个解析器对象，用网页的 url 作为参数
			Parser parser = new Parser(url);
			
			   //设置网页的编码,这里只是请求了一个 gb2312 编码网页
			parser.setEncoding("gb2312");
			  
			//迭代所有节点, null 表示不使用 NodeFilter
			NodeList list = parser.parse(null);
            
			  //从初始的节点列表跌倒所有的节点
			processNodeList(list, keyword);
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	private static void processNodeList(NodeList list, String keyword) {
		//迭代开始
		SimpleNodeIterator iterator = list.elements();
		while (iterator.hasMoreNodes()) {
			Node node = iterator.nextNode();
			//得到该节点的子节点列表
			NodeList childList = node.getChildren();
			//孩子节点为空，说明是值节点
			if (null == childList)
			{
				//得到值节点的值
				String result = node.toPlainTextString();
				//若包含关键字，则简单打印出来文本
				if (result.indexOf(keyword) != -1)
					System.out.println(result);
			} //end if
			//孩子节点不为空，继续迭代该孩子节点
			else 
			{
				processNodeList(childList, keyword);
			}//end else
		}//end wile
	}
	
	public static void selfWrite(String url) throws ParserException
	{
		Parser parser=new Parser(url);
		parser.setEncoding("gb2312");
		NodeList list = parser.parse(null);
		
		for(SimpleNodeIterator iter=list.elements();iter.hasMoreNodes();)
		{
			Node node=iter.nextNode();
			System.out.println(node.toPlainTextString());
		}
		
	}
	
	public static void testStringBean(String url)
	{
		StringBean sb = new StringBean();
		sb.setLinks(true);//设置结果中去点链接
		sb.setURL(url);//设置你所需要滤掉网页标签的页面 url
		System.out.println(sb.getStrings());//打印结果
	}
	
	
	public static void main(String[] args) throws ParserException
	{
		//extractKeyWordText("http://www.amazon.cn/","amazon");
		//selfWrite("http://www.baidu.com");
		testStringBean("http://s.weibo.com/weibo/%25E7%25A6%25BD%25E6%25B5%2581%25E6%2584%259F&Refer=STopic_box");
	}
}
