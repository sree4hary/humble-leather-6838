package com.day1jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import com.mysql.jdbc.Driver;

public class Main {	
	
	private static void signUp() {
		String userName = null;
		Scanner input = new Scanner(System.in);
		
		while(userName == null || validateUserName(userName) == false) {
			System.out.println("Enter a valid user name: ");
			userName = input.next();
		} System.out.println("Username valid.");
		
		String pswd = null, rptPswd = null;
		while(pswd == null || validatePswd(pswd ,rptPswd) == false) {
			System.out.println("Enter a valid password (min. 8 characters): ");
			pswd = input.next();
			System.out.println("Repeat the password: ");
			rptPswd = input.next();
		} System.out.println("Password okay.");
		
		System.out.println("Enter your name: ");
		String name = input.next();
		
		System.out.println("Enter your mobile no: ");
		String mob = input.next();
		
		System.out.println("Enter your email id: ");
		String email = input.next();
		
		new User(userName, pswd, name);
	}
	
	public static boolean validatePswd(String pswd, String rptPswd) {
		if(pswd.length() < 8) {
			System.out.println("Sorry! Password length is below 8 characters.");
			return false;
		}
		if(!pswd.equals(rptPswd)) {
			System.out.println("Sorry! Passwords mistmatch.");
			return false;
		} return true;
	}
	
	public static boolean validateUserName(String username) {
		 try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		 } catch (ClassNotFoundException e) {
			 e.printStackTrace();
		 }
		 
		 String url = "jdbc:mysql://localhost:3306/fragmentdb";
		 
		 try(Connection conn = DriverManager.getConnection(url, "root", "root")) {
			 if(conn != null) {
					PreparedStatement ps = conn.prepareStatement("select * from users where username = ?");
					ps.setString(1, username);
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						System.out.println("The username you entered already used. Try another one.");
					} else {
						System.out.println("Okay. Valid username.");
						return true;
					}
			 }
		 } catch(SQLException e) {
			 System.out.println(e.getMessage());
		 } return false;
	 }
	
	private static void viewDeals() {
		// TODO Auto-generated method stub
		
	}

	private static void signIn() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		System.out.println("WELCOME TO FRAGMENT AUCTION SYSTEM");
		System.out.println("==================================");
		System.out.println("Select any of the following option by entering the respective SL number:");
		System.out.println("1.Sign up as a user(buyer/seller).\n2.Sign in(registered users).\n3.View the ongoing deals.\n4.Exit");
		
		Scanner input = new Scanner(System.in);
		int option = input.nextInt();
		switch(option) {
		case 1: signUp();
		break;
		case 2: signIn();
		break;
		case 3: viewDeals();
		break;
		case 4: break;
		default: System.out.println("Invalid input");
		break;
		}
		input.close();
	}
}