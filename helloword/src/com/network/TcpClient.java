package com.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient
{
	public static void main(String[] args) throws UnknownHostException,
			IOException, InterruptedException
	{
		Socket socket = new Socket("127.0.0.1", 5000);

		OutputStream os = socket.getOutputStream();
		os.write("hello sever!".getBytes());

		InputStream is = socket.getInputStream();
		int length = 0;
		byte[] buffer = new byte[2000];
		while (-1 != (length = is.read(buffer, 0, 2000)))
		{
			System.out.println(new String(buffer));
		}
		socket.close();
	}
}
