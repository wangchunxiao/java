package com.anotation;

@AnnotationTest(value="zhangsan",hello="not hello")//��������ʱ�򣬲���ʡ��value��Ҫ�����ڵ���ʽ
public class AnnotationUse
{
	@AnnotationTest(value="lisi")
	public static void main(String[] args)
	{
		System.out.println("use");
	}
	
	
	@AnnotationTest(value="liran",hello="not hello",world="not world")
	@Deprecated
	public void output()
	{
		System.out.println("output funciton output hello world");
	}
}
