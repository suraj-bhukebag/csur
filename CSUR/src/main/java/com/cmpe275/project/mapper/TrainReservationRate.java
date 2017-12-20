package com.cmpe275.project.mapper;

import javax.persistence.Entity;

public class TrainReservationRate {
	
	private String date;
	private String train;
	private Double reservationRate;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTrain() {
		return train;
	}
	public void setTrain(String train) {
		this.train = train;
	}
	public Double getReservationRate() {
		return reservationRate;
	}
	public void setReservationRate(Double reservationRate) {
		this.reservationRate = reservationRate;
	}
	
	

}
