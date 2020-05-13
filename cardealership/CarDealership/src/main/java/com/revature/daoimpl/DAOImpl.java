package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.revature.beans.Car;
import com.revature.beans.CarOffer;
import com.revature.beans.Customer;
import com.revature.dao.DAO;
import com.revature.util.ConnFactory;
import com.revature.util.Logs;

public class DAOImpl implements DAO{
	ConnFactory cf = ConnFactory.getInstance();
	Connection conn = null;
	PreparedStatement stmt = null;
	
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Statement is not exist");
			e.printStackTrace();
		}
		
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("Connection is not exist");
			e.printStackTrace();
		}
	}
	
	@Override
	public void insertCustomer(String customerUsername, int customerPassword) throws SQLException {
		try {
			conn = cf.getConnection();
			String sql = "INSERT INTO CUSTOMER (C_USERNAME, C_PASSWORD) VALUES (?,?)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, customerUsername);
			stmt.setInt(2, customerPassword);
			stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources();
			}
		Logs.LogIt("info", "Customer added");


	}
	@Override
	public List<Customer> getCustomerList() {
		List<Customer> customerList = new ArrayList<Customer>();
		try {
		conn = cf.getConnection();
		String sql = "SELECT * FROM CUSTOMER";
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		Customer s = null;
		while(rs.next()) {
			s = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3));
			customerList.add(s);
		} 
		} catch  (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return customerList;
	}
	@Override
	public void insertCar(String carBrand, String carColor, int carPrice, Integer carState) throws SQLException {
		try {
			conn = cf.getConnection();
			String sql = "INSERT INTO CAR(CAR_BRAND, CAR_COLOR, CAR_PRICE, CAR_STATE) VALUES (?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, carBrand);
			stmt.setString(2, carColor);
			stmt.setInt(3, carPrice);
			stmt.setNull(4, Types.INTEGER);
			stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources();
			}
		Logs.LogIt("info", "Car added");
	}
	@Override
	public void removeCar(Integer carId) {
		Connection conn = cf.getConnection();
		String sql = "DELETE FROM CAR WHERE CAR_ID = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, carId);  
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		Logs.LogIt("info", "Car deleted");
	}
		
	@Override
	public List<Car> getCarList1() throws SQLException {
		List<Car> carList = new ArrayList<Car>();
		try {
		conn = cf.getConnection();
		String sql = "SELECT * FROM CAR";
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Car s = new Car(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
			carList.add(s);
		} 
		} catch  (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return carList;
	}
	@SuppressWarnings("resource")
	@Override
	public void regiser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your username: ");
		String username = sc.nextLine();
		System.out.println("Please enter your password: ");
		int password = sc.nextInt();
		
		try {
			insertCustomer(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Successfully registered.");
		
		
	}
	@SuppressWarnings("resource")
	@Override
	public int customerLogin() throws SQLException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter your username:");
		String username = sc.nextLine();
		System.out.println("Please enter your password:");
		int password = sc.nextInt();
		Connection conn = cf.getConnection();
		String sql = "SELECT CUSTOMER_ID,C_USERNAME, C_PASSWORD FROM CUSTOMER WHERE C_USERNAME = ? AND C_PASSWORD = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setInt(2, password);
		ResultSet rs = stmt.executeQuery();
		if(rs.next() == false) {
			System.out.println("Invalid login.");
			username = null;
		} else {
			int customerId = rs.getInt(1);
			return customerId;
		}
		return 0;
		 
	}
	@Override
	public void viewAllCar() throws SQLException {
		List<Car> carList = getCarList1();
		for(int i = 0; i < carList.size(); i++){
            System.out.println(carList.get(i));
        }
	}
	@SuppressWarnings("resource")
	@Override
	public void makeCarOffer(int customerId){
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the car id: ");
		int carId = sc.nextInt();
		try {
		conn = cf.getConnection();
		String sql = "INSERT INTO CAR_OFFER VALUES (?,?)";
		stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, carId);
		stmt.setInt(2, customerId);
		} catch  (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
	}
	@Override
	public void viewMyCar(int customerId) {
		List<Car> myCar = new ArrayList<Car>();
		try {
		conn = cf.getConnection();
		String sql = "SELECT * FROM CAR WHERE CAR_STATE = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customerId);  
		ResultSet rs = stmt.executeQuery();
		Car s = null;
		while(rs.next()) {
			s = new Car(rs.getString(2), rs.getString(3), rs.getInt(4));
			myCar.add(s);
		} 
		} catch  (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		for(int i = 0; i < myCar.size(); i++){
            System.out.println(myCar.get(i));
        }
	}
	@Override
	public void viewMyPayment(int customerId) {
		List<Car> myPayment = new ArrayList<Car>();
		try {
		conn = cf.getConnection();
		String sql = "SELECT CAR_PRICE FROM CAR WHERE CAR_STATE = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, customerId);  
		ResultSet rs = stmt.executeQuery();
		Car s = null;
		while(rs.next()) {
			s = new Car(rs.getString(2), rs.getString(3), rs.getInt(4));
			myPayment.add(s);
		} 
		} catch  (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		for(int i = 0; i < myPayment.size(); i++){
            System.out.println(myPayment.get(i));
        }	
	}
	@SuppressWarnings("resource")
	@Override
	public void addCar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insert car brand: ");
		String carBrand = sc.nextLine();
		System.out.println("Insert car color: ");
		String carColor = sc.nextLine();
		System.out.println("Insert car price: ");
		int carPrice = sc.nextInt();
		
		try {
			insertCar(carBrand, carColor, carPrice, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Car added.");
		
	}
	@SuppressWarnings("resource")
	@Override
	public void acceptOrDeny() {
		List<CarOffer> carOffer = new ArrayList<CarOffer>();
		try {
		conn = cf.getConnection();
		String sql = "SELECT * FROM CAR_OFFER";
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		CarOffer s = null;
		while(rs.next()) {
			s = new CarOffer(rs.getInt(1), rs.getInt(2));
			carOffer.add(s);
		} 
		} catch  (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		for(int i = 0; i < carOffer.size(); i++){
            System.out.println("Here are the car offers: \n" + carOffer.get(i));
        }
		Scanner sc = new Scanner(System.in);
		System.out.println("Choose operation: ");
		System.out.println("[1]accept");
		System.out.println("[2]deny");
		int input = sc.nextInt();
		if (input == 1) {
			System.out.println("Insert customer id: ");
			Integer customerId = sc.nextInt();
			System.out.println("Insert car id: ");
			Integer carId = sc.nextInt();
			try {
			conn = cf.getConnection();
			String sql = "UPDATE CAR SET CAR_STATE = ? WHERE CAR_ID = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customerId);
			stmt.setInt(2, carId);
			} catch  (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources();
			}
		} else if (input == 2) {
			Connection conn = cf.getConnection();
			String sql = "DELETE FROM CAR_OFFER WHERE CAR_STATE = ?";
			System.out.println("Insert customer id: ");
			Integer customerId = sc.nextInt();
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, customerId);  
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources();
			}
		} else {
			System.out.println("Invalid input!");
		}
		
		
	}
	@Override
	public void viewAllPayment() {
		List<Car> allPayment = new ArrayList<Car>();
		try {
		conn = cf.getConnection();
		String sql = "SELECT CAR_PRICE FROM CAR";
		stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		Car s = null;
		while(rs.next()) {
			s = new Car(rs.getInt(1));
			allPayment.add(s);
		} 
		} catch  (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		for(int i = 0; i < allPayment.size(); i++){
            System.out.println("CarPrice:"+allPayment.get(i).getCarPrice());
        }	
		
	}
	@Override
	public void updateCarState() {
		
	}
	@Override
	public void monthlyPayment() {
		// TODO Auto-generated method stub
		
	}
}
