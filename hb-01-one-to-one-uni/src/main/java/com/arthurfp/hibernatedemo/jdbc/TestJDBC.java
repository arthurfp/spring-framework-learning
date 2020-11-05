package com.arthurfp.hibernatedemo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {
	
	public static void main(String[] args) {
		
		try {
			
			String jdbcUrl = 
				"jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?"
				+ "allowPublicKeyRetrieval=true&useSSL=false&ServerTimezone=UTC";
			
			String user = "hbstudent";
			String pass = "hbstudent";
			
			System.out.println("Connecting to database: " + jdbcUrl);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection successful!!!");
			
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

}
