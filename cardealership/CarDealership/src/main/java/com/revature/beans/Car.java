package com.revature.beans;

import java.io.Serializable;


public class Car implements Serializable{
	private static final long serialVersionUID = 3717083375803814202L;
	private Integer carId;
	private String carBrand;
	private String carColor;
	private int carPrice;
	private Integer carState;
	
	public Car() {
		super();
	}

	public Car(int carPrice) {
		this.carPrice = carPrice;

	}
	
	public Car(String carBrand, String carColor, int carPrice) {
		super();
		this.carBrand = carBrand;
		this.carColor = carColor;
		this.carPrice = carPrice;
		
	}
	
	public Car(Integer carId, String carBrand, String carColor, int carPrice, Integer carState) {
		super();
		this.carId = carId;
		this.carBrand = carBrand;
		this.carColor = carColor;
		this.carPrice = carPrice;
		this.carState = carState;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarColor() {
		return carColor;
	}

	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}

	public int getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(int carPrice) {
		this.carPrice = carPrice;
	}

	public Integer getCarState() {
		return carState;
	}

	public void setCarState(Integer carState) {
		this.carState = carState;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", carBrand=" + carBrand + ", carColor=" + carColor + ", carPrice=" + carPrice
				+ ", carState=" + carState + "]";
	}

	 


	
	

}
