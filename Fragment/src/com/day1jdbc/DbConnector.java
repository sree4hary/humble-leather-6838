package com.day1jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnector {
	
	
	//////////////////
	
	public boolean validateLogin(String tempUsrName, String tempPswd) {
	
//	validateUserName(String username) {
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 } catch (ClassNotFoundException e) {
			 e.printStackTrace();
		 }
		 
		 String url = "jdbc:mysql://localhost:3306/fragmentdb";
		 
		 try(Connection conn = DriverManager.getConnection(url, "root", "root")) {
			 if(conn != null) {
					PreparedStatement ps = conn.prepareStatement("select * from users where username = ? and pswd = ?");
					ps.setString(1, tempUsrName);
					ps.setString(2, tempPswd);
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
	
	/////////////////////
	public void createUserRecord(String usrName, String pswd, String name, String mob, String email) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/fragmentdb";
		try(Connection conn = DriverManager.getConnection(url, "root", "root")) {
			PreparedStatement st = conn.prepareStatement("insert into users(username, pswd, name, mobileno, email) values(?, ?, ?, ?, ?)");
			st.setString(1, usrName);
			st.setString(2, pswd);
			st.setString(3, name);
			st.setString(4, mob);
			st.setString(5, email);
			
			int x = st.executeUpdate();
//			System.out.println(x);
			
			if(x > 0) {
				System.out.println("Account created successfully.");
			} else System.out.println("Account creation error.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	///////////////////
}