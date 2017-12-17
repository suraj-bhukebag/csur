package com.cmpe275.project.CSUR.mapper;

import com.cmpe275.project.CSUR.model.Train;

public class SearchResults {
	
	private Train train;
	private String from;
	private String to;
	private String arvTime;
	private String depTime;
	private double price;
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}
	public String getArvTime() {
		return arvTime;
	}
	public void setArvTime(String arvTime) {
		this.arvTime = arvTime;
	}
	public String getDepTime() {
		return depTime;
	}
	public void setDepTime(String depTime) {
		this.depTime = depTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	

}
