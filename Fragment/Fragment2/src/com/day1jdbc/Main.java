package com.day1jdbc;
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
		System.out.println(" 1.View deals \n 2.Sign In(for Registered users)");
		System.out.println("Enter your input: ");
		int choice = input.nextInt();
		switch(choice) {
		case 1: showDeals();
		break;
		case 2: signIn();
		break;
		} input.close();
	}
	
	void signIn() {
		System.out.println("-------------USER SIGN IN------------");
		System.out.println("-------------------------------------");
		System.out.print("Enter username: ");
		Scanner input = new Scanner(System.in);
		String tempUsrName = input.next();
		System.out.println("Enter password: ");
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
			System.out.println("--------------------MENU------------------------");
			System.out.println("Current user: " + user.username + " |     | " + "User Type: " + user.userTyp);
			System.out.println("-------------------------------------------------");
			user.showMethods();
			System.out.println("er: 05:47");
			int x = input.nextInt();
			
			if(x != 1) {
				user = null;

				 System.out.println("inside dbconnector");
			}
		} System.out.println("Program exited");
		input.close();
	}
}