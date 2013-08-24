/*
 * 1.这个事例是通过Array反射的机制创建多维数组，以及获取和设置某一个元素的值
 * 2.下面的注释要注意
 * 3.getComponentType还没理解,貌似就是对应每个数组维度的类型，一维的时候是元素类型，二维的时候相应的就是一维数组
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
		Object array_temp=Array.get(array_obj,1);  //这是获取第一维的下标为1的数组(这里获取的是个二维数组，注意不要越界)
		array_temp=Array.get(array_temp,2);//获取在第一维的基础上第二维的数组，即获取刚才获得的那个二维数组的新的第一维
	    Array.setInt(array_temp,3,100);
	    int[][][] array=(int[][][])array_obj;
	    System.out.println(array[1][2][3]);
	    //System.out.println(Long.TYPE);
	    System.out.println(array_obj.getClass().getComponentType());
	    
	    
	    //一下是componentType的一个测试 用到下面专门为了测试而定义的类
	    System.out.println("----------------------------------");
	    ComponentTypeTest1[] test_array=new ComponentTypeTest1[2];
	    System.out.println(test_array.getClass().getComponentType());
	}
}

class ComponentTypeTest1
{
	public int test;
}