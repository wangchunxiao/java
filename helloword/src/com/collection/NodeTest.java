package com.collection;

public class NodeTest
{
	public static void main(String[] args)
	{
		Node node1 = new Node("zhangsan");
		Node node2 = new Node("lisi");
		Node node3 = new Node("wangwu");
		node1.next = node2;
		node1.previous = node3;
		node2.next = node3;
		node2.previous = node1;
		node3.next = node1;
		node3.previous = node2;
		System.out.println(node1.next.next.previous.previous.previous.content);
		System.out.println("--------------------------------");
		
		insertlist(node1,"lisi",3,"zhaoliu");
		output(node1,4);
		System.out.println("--------------------------------");
		insertlist(node1,"zhaoliu",4,"sunqi");
		output(node1,5);
		
		
	}

	public static void insertlist(Node node, String content,int size,String newcontent)
	{
		Node nodex = null;
		Node nodenew = new Node(newcontent);
		if (node.content == content)
		{
			nodex = node.previous;
			nodex.next = nodenew;
			nodenew.previous = nodex;
			nodenew.next = node;
		}
		else
		{
			int num=0;
			while (node.content != content && num<size)
			{
				nodex = node;
				node = node.next;
				num++;
			}
			if (num<size)
			{
				nodex.next = nodenew;
				node.previous = nodenew;
				nodenew.previous = nodex;
				nodenew.next = node;
			}
			else
				System.out.println("不存在这个元素");
		}
	}
	
	public static void output(Node node,int size)
	{
		int i=0;
		for(;i<size;i++)
		{
			System.out.println(node.content);
			node=node.next;
		}
	}
}
