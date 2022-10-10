package br.com.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	private static String url = "jdbc:postgresql://127.0.0.1:5432/jdbc";
	private static String user = "root";
	private static String password = "123456";
	private static Connection connection;
	
	static {
		connect();
	}
	
	private static void connect()
	{
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(url, user, password);
				//connection.setAutoCommit(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SingleConnection()
	{
		connect();
	}
	
	public static Connection getConnection()
	{
		return connection;
	}
}
