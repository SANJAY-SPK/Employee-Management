package com.techm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBUtil {
//Method to create database connection
	static Connection connection;
	public static Connection createConnection() {
	    try {
	        if (connection == null) {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            String username = "root";
	            String password = "root";
	            String url = "jdbc:mysql://localhost:3306/servlet";
	            connection = DriverManager.getConnection(url, username, password);
	            return connection;
	        }
	        else
	        {
	        	return connection;
	        }
	        
	    } 
	    catch (ClassNotFoundException ce) 
	    {
	        System.out.println("JDBC Driver not found!");
	        ce.printStackTrace();
	    } 
	    catch (SQLException se) 
	    {
	        System.out.println("Connection failed!");
	        se.printStackTrace();
	    }
	    return connection;
	    
	}


}