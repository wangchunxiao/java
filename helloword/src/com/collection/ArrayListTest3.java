package com.collection;

import java.util.ArrayList;

/**
 * 
 * @author Eric
 *
 */
public class ArrayListTest3
{
	public static void main(String[] args)
	{
		ArrayList list=new ArrayList();
		list.add(new Point(2,2));
		list.add(new Point(3,3));
		
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}
	}
}

/**
 * 
 */
class Point
{
	int x;
	int y;
	/**
	 * 
	 * @param x ������
	 * @param y ������
	 */
	Point(int x,int y)
	{
		this.x=x;
		this.y=y;
	}

    /**
     * @ruturn "x="+this.x+" y="+this.y+" "
     */
    public String toString()
    {
    	// TODO �Զ����ɵķ������
    	return ("x="+this.x+" y="+this.y+" ");
    }
}