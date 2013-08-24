package com.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer
{
	public static void main(String[] args) throws IOException
	{
		ServerSocket ss = new ServerSocket(5000);
		Socket socket = ss.accept();

		// System.out.println("connection success");

		InputStream is = socket.getInputStream();
		byte[] buffer = new byte[2000];
		int length = 0;
		// while(-1!=(length=is.read(buffer,0,2000)))
		if (-1 != (length = is.read(buffer, 0, 2000)))
		{
			System.out.println(new String(buffer));
		}

		OutputStream os = socket.getOutputStream();
		os.write("hello client".getBytes());

		socket.close();
		ss.close();
		is.close();
	}
}
