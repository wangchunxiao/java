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
            
				//����һ����������������ҳ�� url ��Ϊ����
			Parser parser = new Parser(url);
			
			   //������ҳ�ı���,����ֻ��������һ�� gb2312 ������ҳ
			parser.setEncoding("gb2312");
			  
			//�������нڵ�, null ��ʾ��ʹ�� NodeFilter
			NodeList list = parser.parse(null);
            
			  //�ӳ�ʼ�Ľڵ��б�������еĽڵ�
			processNodeList(list, keyword);
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

	private static void processNodeList(NodeList list, String keyword) {
		//������ʼ
		SimpleNodeIterator iterator = list.elements();
		while (iterator.hasMoreNodes()) {
			Node node = iterator.nextNode();
			//�õ��ýڵ���ӽڵ��б�
			NodeList childList = node.getChildren();
			//���ӽڵ�Ϊ�գ�˵����ֵ�ڵ�
			if (null == childList)
			{
				//�õ�ֵ�ڵ��ֵ
				String result = node.toPlainTextString();
				//�������ؼ��֣���򵥴�ӡ�����ı�
				if (result.indexOf(keyword) != -1)
					System.out.println(result);
			} //end if
			//���ӽڵ㲻Ϊ�գ����������ú��ӽڵ�
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
		sb.setLinks(true);//���ý����ȥ������
		sb.setURL(url);//����������Ҫ�˵���ҳ��ǩ��ҳ�� url
		System.out.println(sb.getStrings());//��ӡ���
	}
	
	
	public static void main(String[] args) throws ParserException
	{
		//extractKeyWordText("http://www.amazon.cn/","amazon");
		//selfWrite("http://www.baidu.com");
		testStringBean("http://s.weibo.com/weibo/%25E7%25A6%25BD%25E6%25B5%2581%25E6%2584%259F&Refer=STopic_box");
	}
}
