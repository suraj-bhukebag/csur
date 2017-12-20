package com.cmpe275.project.mapper;

public class SearchCriteria {

	private int noOfPassengers;
	private String departureTime;
	private String returnDepartureTime;
	private long returnDepDate;
	private boolean isRoundtrip;
	private long depDate;
	private String from;
	private String to;
	private String trainType;
	private int noOfConnections;
	private boolean isExact;

	public boolean isExact() {
		return isExact;
	}

	public void setExact(boolean isExact) {
		this.isExact = isExact;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
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

	public int getNoOfConnections() {
		return noOfConnections;
	}

	public void setNoOfConnections(int noOfConnections) {
		this.noOfConnections = noOfConnections;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public long getDepDate() {
		return depDate;
	}

	public void setDepDate(long depDate) {
		this.depDate = depDate;
	}

	public String getReturnDepartureTime() {
		return returnDepartureTime;
	}

	public void setReturnDepartureTime(String returnDepartureTime) {
		this.returnDepartureTime = returnDepartureTime;
	}

	public long getReturnDepDate() {
		return returnDepDate;
	}

	public void setReturnDepDate(long returnDepDate) {
		this.returnDepDate = returnDepDate;
	}

	public boolean isRoundtrip() {
		return isRoundtrip;
	}

	public void setRoundtrip(boolean isRoundtrip) {
		this.isRoundtrip = isRoundtrip;
	}
	
	

	
}
