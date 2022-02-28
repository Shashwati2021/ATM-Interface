package com.atm.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConncetion {
	public Connection connection()
	{
		Connection conn=null;
		
		String databaseUrl="jdbc:mysql://localhost:3306/SBIAtmUser";
		String userName="root";
		String userPassword="mysql";
		
			
				try {
					conn=DriverManager.getConnection(databaseUrl, userName, userPassword);
					
				} 
				
				catch (SQLException e) {
					
					System.out.println("Internal Server Error!!");
				}
				
				return conn;
			
	}
	
	

}


