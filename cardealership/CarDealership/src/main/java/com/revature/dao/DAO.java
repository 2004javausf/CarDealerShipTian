package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Car;
import com.revature.beans.Customer;

public interface DAO {
	public void insertCustomer(String customerUsername, int customerPassword) throws SQLException;
	public List<Customer> getCustomerList() throws SQLException;
	public void regiser();
	public int customerLogin() throws SQLException;
	public void viewAllCar() throws SQLException;
	public void makeCarOffer(int customerId) throws SQLException;
	public void viewMyCar(int customerId);
	public void viewMyPayment(int customerId);
	
	public void insertCar(String carBrand, String carColor, int carPrice, Integer carState) throws SQLException;
	public void removeCar(Integer carId);
	public List<Car> getCarList1() throws SQLException;
	//public List<Car> getCarList2() throws SQLException;
	public void addCar();
	public void acceptOrDeny();
	public void viewAllPayment();
	
	public void updateCarState();
	public void monthlyPayment();
}
