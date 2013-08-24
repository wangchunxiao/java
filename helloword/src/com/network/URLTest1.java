package com.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class URLTest1
{
	public static void main(String[] args) throws UnknownHostException
	{
		InetAddress address=InetAddress.getLocalHost();
		System.out.println(address);
		
		address=InetAddress.getByName("www.sina.com.cn");
		System.out.println(address);
	}
}
