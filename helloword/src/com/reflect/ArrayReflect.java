/*
 * 1.���������ͨ��Array����Ļ��ƴ�����ά���飬�Լ���ȡ������ĳһ��Ԫ�ص�ֵ
 * 2.�����ע��Ҫע��
 * 3.getComponentType��û���,ò�ƾ��Ƕ�Ӧÿ������ά�ȵ����ͣ�һά��ʱ����Ԫ�����ͣ���ά��ʱ����Ӧ�ľ���һά����
 * */

package com.reflect;

import java.lang.reflect.Array;

public class ArrayReflect
{
	public static void main(String[] args)
	{
		int[] dimensions=new int[]{2,3,4};
		
		//Object array_obj=Array.newInstance(int,dimensions);
		Object array_obj=Array.newInstance(Integer.TYPE,dimensions);
		Object array_temp=Array.get(array_obj,1);  //���ǻ�ȡ��һά���±�Ϊ1������(�����ȡ���Ǹ���ά���飬ע�ⲻҪԽ��)
		array_temp=Array.get(array_temp,2);//��ȡ�ڵ�һά�Ļ����ϵڶ�ά�����飬����ȡ�ղŻ�õ��Ǹ���ά������µĵ�һά
	    Array.setInt(array_temp,3,100);
	    int[][][] array=(int[][][])array_obj;
	    System.out.println(array[1][2][3]);
	    //System.out.println(Long.TYPE);
	    System.out.println(array_obj.getClass().getComponentType());
	    
	    
	    //һ����componentType��һ������ �õ�����ר��Ϊ�˲��Զ��������
	    System.out.println("----------------------------------");
	    ComponentTypeTest1[] test_array=new ComponentTypeTest1[2];
	    System.out.println(test_array.getClass().getComponentType());
	}
}

class ComponentTypeTest1
{
	public int test;
}