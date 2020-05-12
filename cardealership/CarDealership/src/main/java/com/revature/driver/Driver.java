package com.revature.driver;

import java.sql.SQLException;
import java.util.Scanner;
import com.revature.daoimpl.DAOImpl;

public class Driver {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException {
		Integer customerId = null;
		boolean quit = false;
		DAOImpl di = new DAOImpl();
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to Cardealership Demo");
		System.out.println("Please select your user type:");
		System.out.println("[1] Customer [2] Employee [3] admin");
		int select = sc.nextInt();
		switch(select)  {
		case 1:
			System.out.println("[1] Register [2] Login");
			select = sc.nextInt();
			switch(select) {
			case 1:
				di.regiser();
			case 2:
			while(customerId == null) {
				try {
					customerId = di.customerLogin();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			do {
			System.out.println("Please select an option:");
			System.out.println("[1] View all cars");
			System.out.println("[2] Make car offer");
			System.out.println("[3] View my car");
			System.out.println("[4] View my payment");
			System.out.println("[0] Exit");
			select = sc.nextInt();
			switch(select) {
			case 1:
				try {
					di.viewAllCar();
					break;
				} catch (SQLException e) {
					e.printStackTrace();
					break;
				}
			case 2:
				di.makeCarOffer(customerId);
				break;
			case 3:
				di.viewMyCar(customerId);
				break;
			case 4:
				di.viewMyPayment(customerId);
				break;
			case 0:
				quit = true;
				break;
			default:
				System.out.println("Invalid input");
				break;
			}
		} while(quit == false);
		System.out.println("Thank you for using this demo");
		System.exit(0);
			}
		case 2:
			System.out.println("Please enter your username:");
			String eusername = sc.nextLine();
			System.out.println("Please enter your password:");
			String epassword = sc.nextLine();
			if (eusername == "employee" && epassword == "123456") {
				do {
					System.out.println("Please select an option:");
					System.out.println("[1] Add car to car lot");
					System.out.println("[2] Accept/deny car offer");
					System.out.println("[3] View all payment");
					System.out.println("[0] Exit");
					select = sc.nextInt();
					switch(select) {
					case 1:
						di.addCar();
						break;
					case 2:
						di.acceptOrDeny();
						break;
					case 3:
						di.viewAllPayment();
						break;
					case 0:
						quit = true;
						break;
					default:
						System.out.println("Invalid input");
						break;
					}
				} while(quit == false);
				System.out.println("Thank you for using this demo");
				System.exit(0);
					}  else {
						System.out.println("Invalid input");
			}
		case 3:
			System.out.println("Please enter your username:");
			String ausername = sc.nextLine();
			System.out.println("Please enter your password:");
			String apassword = sc.nextLine();
			if (ausername == "admin" && apassword == "123456") {
				do {
					System.out.println("Please select an option:");
					System.out.println("[1] Update car state");
					System.out.println("[2] Calculate payment");
					System.out.println("[0] Exit");
					select = sc.nextInt();
					switch(select) {
					case 1:
						di.updateCarState();
						break;
					case 2:
						di.monthlyPayment();
						break;
					case 0:
						quit = true;
						break;
					default:
						System.out.println("Invalid input");
						break;
					}
				} while(quit == false);
				System.out.println("Thank you for using this demo");
				System.exit(0);
					}  else {
						System.out.println("Invalid input");
			}
				
	}
}
}