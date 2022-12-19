package com.day1jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

abstract class User {
	String username;
	String name;
	String pswd;
	String userTyp;
	abstract void showMethods();
	void getUser() {
		System.out.println("Current User: " + this.username);
	}
	void showDeals() {
		System.out.println("showDeals executed");
	}
}

class Admin extends User {
	@Override
	void showMethods() {
		// TODO Auto-generated method stub
		
	}
}

class RegdUser extends User {
	RegdUser(String UserName, String name) {
		this.username = UserName;
		this.name = name;
		this.userTyp = "Normal User";
	}
	@Override
	void showMethods() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println(" 1.View deals \n 2.Create Deal(Sell product) \n 3.Bid a deal(Buy product) \n 4.Logout(SignedIn User)");
		System.out.print("Enter your input: ");
		int choice = input.nextInt();
		switch(choice) {
		case 1: this.showDeals();
		break;
		case 2: this.createDeal();
		break;
		case 3: this.bidDeal();
		break;
		case 4: logOut();
		break;
		}
		input.close();
	}
	private void bidDeal() {
		// TODO Auto-generated method stub
		
	}
	private void createDeal() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println("Enter product description: ");
		String desc = input.nextLine();
		System.out.println("Enter the base price: ");
		int basePrice = input.nextInt();
//		new DbConnector().insertProd();
		input.close();
	}
	private void logOut() {
		// TODO Auto-generated method stub
		Main.user = new Guest();
		System.out.println("You are successfully signed out.");
	}
}

class Guest extends User {
	Guest() {
		this.username = "guest";
		this.name = "guest";
		this.userTyp = "guest";
	}
	
	@Override
	void showMethods() {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		System.out.println(" 1.View deals \n 2.Sign In(for Registered users) \n 3.Sign Up(for new users)");
		System.out.println("-------------------------------------------------");
		System.out.print("Enter your input: ");
		int choice = input.nextInt();
		switch(choice) {
		case 1: showDeals();
		break;
		case 2: signIn();
		break;
		case 3: signUp();
		break;
		} input.close();
	}
	//////////
	private static void signUp() {
		String userName = null;
		Scanner input = new Scanner(System.in);
		System.out.println("==================================================");
		System.out.println("--------------------USER SIGN UP------------------");
		while(userName == null || validateUserName(userName) == false) {
			System.out.print("Enter a valid user name: ");
			userName = input.next();
		}
		
		String pswd = null, rptPswd = null;
		while(pswd == null || validatePswd(pswd ,rptPswd) == false) {
			System.out.print("Enter a valid password (min. 8 characters): ");
			pswd = input.next();
			System.out.print("Repeat the password: ");
			rptPswd = input.next();
		} System.out.println("Password okay.");
		
		System.out.print("Enter your name: ");
		String name = input.nextLine();
		input.nextLine();
		System.out.print("Enter your mobile no: ");
		String mob = input.next();
		
		System.out.print("Enter your email id: ");
		String email = input.next();
		
//		new User(userName, pswd, name, mob, email);
		new DbConnector().createUserRecord(userName, pswd, name, mob, email);
			
		
		input.close();
	}
	//////////////
	
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
	
	
	
	//////////////////
	
	
	
	void signIn() {
		System.out.println("==================================================");
		System.out.println("------------------USER SIGN IN------------------");
		System.out.print("Enter username: ");
		Scanner input = new Scanner(System.in);
		String tempUsrName = input.next();
		System.out.print("Enter password: ");
		String tempPswd = input.next();
		new DbConnector().validateLogin(tempUsrName, tempPswd);
		input.close();
	}
}

public class Main {
	static User user;
	public static void main(String[] args) {
		user = new Guest();
		Scanner input = new Scanner(System.in);
		System.out.println("       FRAGMENT - AUTOMATED AUCTION SYSTEM     ");
		System.out.println("===============================================");
		while(user != null) {
			System.out.println("---------------------MENU------------------------");
			System.out.println("Current user: " + user.username + " |     | " + "User Type: " + user.userTyp);
			System.out.println("-------------------------------------------------");
			user.showMethods();
			System.out.println("-------------------------------------------------");
			System.out.println("Do you wish to continue? If so, enter 1. Else, enter any other number.");
			int x = input.nextInt();
			
			if(x != 1) {
				user = null;
			}
		} System.out.println("Program exited");
		input.close();
	}
}