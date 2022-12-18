package com.day1jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	String usrName = null;
	String pswd = null;
	String name = null;
	String mob = null;
	String email = null;
	
	User(String usrName, String pswd, String name, String mob, String email) {
		this.usrName = usrName;
		this.pswd = pswd;
		this.name = name;
		this.mob = mob;
		this.email = email;
		System.out.println("constructor execute.");
		createUserRecord();
	}
	
	public void createUserRecord() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3306/fragmentdb";
		try(Connection conn = DriverManager.getConnection(url, "root", "root")) {
			System.out.println("connected to database.");
			PreparedStatement st = conn.prepareStatement("insert into users(username, pswd, name, mobileno, email) values(?, ?, ?, ?, ?)");
			st.setString(1, this.usrName);
			st.setString(2, this.pswd);
			st.setString(3, this.name);
			st.setString(4, this.mob);
			st.setString(5, this.email);
			
			int x = st.executeUpdate();
			System.out.println(x);
			
			if(x > 0) {
				System.out.println("Account created successfully.");
			} else System.out.println("Account creation error.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}