package com.revature.beans;

import java.io.Serializable;

public class CarOffer implements Serializable{
	private static final long serialVersionUID = -6645854225593138831L;
	private Integer carId;
	private Integer carState;
	
	public CarOffer() {
		super();
	}
	
	public CarOffer(Integer carId, Integer carState) {
		super();
		this.carId = carId;
		this.carState = carState;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getCarState() {
		return carState;
	}

	public void setCarState(Integer carState) {
		this.carState = carState;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", carBrand=" + ", carColor=" +  ", carState=" + carState
				+ "]";
	}

}
