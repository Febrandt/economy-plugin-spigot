package me.ferjao.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

	private String url = "jdbc:mysql://localhost:3306/test";
	private String username = "root";
	private String password = "";
	
	
	private Connection connection;
	
	public boolean isConnected() 
	{
		return connection != null;
	}
	
	public void connect() throws ClassNotFoundException , SQLException
	{
		if (!isConnected()) {
			connection = DriverManager.getConnection(url,username,password);
		}

	}
	
	public void disconnect() {
		if (isConnected()) {
			try {
				connection.close();
		
			} catch (SQLException e) {
				
			}
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
	
}
