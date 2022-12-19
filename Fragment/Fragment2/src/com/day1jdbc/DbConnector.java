package com.day1jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnector {
	
	public static boolean validateLogin(String username, String pswd) {
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 } catch (ClassNotFoundException e) {
			 e.printStackTrace();
		 }
		 String url = "jdbc:mysql://localhost:3306/fragmentdb";
		 
		 try(Connection conn = DriverManager.getConnection(url, "root", "root")) {
			 if(conn != null) {
					PreparedStatement ps = conn.prepareStatement("select * from users where username = ? and pswd = ?");
					ps.setString(1, username);
					ps.setString(2, pswd);
					ResultSet rs = ps.executeQuery();


					if(rs.next()) {
						System.out.println("Hi!"+ rs.getString("name")+". Your Login is Successful.");
						Main.user = new RegdUser(rs.getString("name"), rs.getString("pswd"));
						return true;
					} else {
						System.out.println("Invalid Credentials.");
						return false;
					}
					
			 }
		 } catch(SQLException e) {

			 System.out.println(e.getMessage());
		 } return false;
	 }
	
	public static boolean insertProd(String username, String pswd) {
		return false;
	 }
	
}