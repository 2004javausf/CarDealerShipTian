   package com.revature.beans;

import java.io.Serializable;


public class Customer implements Serializable{

	private static final long serialVersionUID = -4698689716559718275L;
	private Integer customerId;
	private String customerUsername;
	private String customerPassword;
	
	public Customer() {
		super();
	}
	
	public Customer(Integer customerId, String customerUsername, String customerPassword) {
		super();
		this.customerId = customerId;
		this.customerUsername = customerUsername;
		this.customerPassword = customerPassword;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerUsername() {
		return customerUsername;
	}

	public void setCustomerUsername(String customerUsername) {
		this.customerUsername = customerUsername;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerUsername=" + customerUsername + ", customerPassword="
				+ customerPassword + "]";
	}

	
	
	

}
