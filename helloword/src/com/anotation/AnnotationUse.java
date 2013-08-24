package com.anotation;

@AnnotationTest(value="zhangsan",hello="not hello")//有两个的时候，不能省略value，要用现在的形式
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
