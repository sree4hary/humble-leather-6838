//package com.day1jdbc;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.Scanner;
//
//import com.mysql.jdbc.Driver;
//
//public class Demo {
//	public static void main(String[] args){
//		
//		Scanner input =  new Scanner(System.in);
//		
//		System.out.print("Enter roll no: ");
//		int roll = input.nextInt();
//		
//		System.out.print("Enter the name: ");
//		String name = input.next();
//		
//		System.out.println("enter place: ");
//		String place = input.next();
//		
//		System.out.println("Enter marks: ");
//		int marks = input.nextInt();
//		try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//				
//			String url = "jdbc:mysql://localhost:3306/jdbcApp3Db";
//				try(Connection conn = DriverManager.getConnection(url, "root", "root")) { 
//					if(conn != null) {
//						System.out.println("Connected");
//						
//						PreparedStatement ps = conn.prepareStatement("insert into student values(?, ?, ?, ?)");
//						ps.setInt(1,  roll);
//						ps.setString(2, name);
//						ps.setString(3, place);
//						ps.setInt(4, marks);
//						
//						int x = ps.executeUpdate();
//						
//						if(x > 0) {
//							System.out.println("record got inserted successfully.");
//						} else {
//							System.out.println("record not inserted");
//						}
//						
//					} else {
//						System.out.println("not connected");
//					}
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
////					e.printStackTrace();
//					System.out.println(e.getMessage()); //this will print the message if there is an error in the sql operation.
//					}
//			System.out.println("Do you want to add grace marks? If 'yes', enter 'y'. Else, enter 'n'.");
//			String gmQuery = input.next();
//			
//			
//			switch(gmQuery) {
//			case "y": {
//				System.out.println("Enter the grace mark: ");
//				int gm = input.nextInt();
//				try(Connection conn = DriverManager.getConnection(url, "root", "root")) {
//					PreparedStatement ps = conn.prepareStatement("update student set marks = marks + ? where marks < 600");
//					ps.setInt(1, gm);
//					int x = ps.executeUpdate();
//					if(x > 0) {
//						System.out.println("grace marks got updated successfully.");
//					} else System.out.println("grace marks not updated.");
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			break;
//			case "n": System.out.println("Thanx for the confirmation.");
//				break;
//			default: System.out.println("Invalid input");
//			}
//	}
//}