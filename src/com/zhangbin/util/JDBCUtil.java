package com.zhangbin.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil
{
	private static String driverClassName;
	private static String url;
	private static String user;
	private static String password;
	static
	{
		try
		{
			InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("db.properties");
			Properties props = new Properties();
			props.load(in);
			driverClassName = props.getProperty("driverClassName");
			url = props.getProperty("url");
			user = props.getProperty("user");
			password = props.getProperty("password");
			Class.forName(driverClassName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url,user,password);
		
	}
	public static void release(ResultSet rs,Statement stmt,Connection conn)
	{
		if (rs != null)
		{
			try
			{
				rs.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			rs = null;
		}
		if (stmt != null)
		{
			try
			{
				stmt.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			stmt = null;
		}
		if (conn != null)
		{
			try
			{
				conn.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			conn = null;
		}
		
	}
	
}
