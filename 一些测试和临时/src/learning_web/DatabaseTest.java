package learning_web;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DatabaseTest
{
	public static void main(String[] args) throws ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");

		try
		{
			Connection conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/sunyu", "root", "");
			Statement stmt = (Statement) conn.createStatement();
			//ResultSet rs = stmt.executeQuery("select * from user where username="+"'sunyu'"+" and password="+"'sunyu'");
			
			/*
			ResultSet rs=stmt.executeQuery("SELECT MAX(userID) from user");
			rs.next();
			int id=Integer.parseInt(rs.getString(1));
			id++;
			System.out.println("INSERT INTO user(userID,username,password) values ("+id+",'"+"lisi"+"','"+"lisi"+"')");
			stmt.executeUpdate("INSERT INTO user(userID,username,password) values ("+id+",'"+"lisi"+"','"+"lisi"+"')");
			*/
			
			/*
			stmt.executeUpdate("DELETE FROM user WHERE username='"+"wangwu"+"'");
			*/
			
			
			
			boolean i=false;
			i=stmt.execute("SELECT username from user where username='"+"lisis"+"'");
			{
				System.out.println(i);
			}
			System.out.println(stmt.execute("SELECT username from user where username='"+"lisi"+"'"));
			ResultSet rs=stmt.executeQuery("SELECT username from user where username='"+"lisi"+"'");
			rs.next();
			System.out.println(rs.getString(1));
			//没有数据是ResultSet是空的
			
			
			
			if(stmt.execute("select * from user where username="+"'sunyu'"+" and password="+"'123'"))
			{
				stmt.executeUpdate("UPDATE user SET password='"+"sunyu"+"'"+ " WHERE username="+"'sunyu'"+" and password="+"'123'");
				
			}
			//System.out.println("()");
			/*
			while(rs.next())
			{
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
				System.out.println(rs.getString(4));
			}
			 */
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
