package com.cmpe275.project.mapper;

public class SearchCriteria {

	private int noOfPassengers;
	private long departureTime;
	private long depDate;
	private String from;
	private String to;
	private String trainType;
	private int noOfConnections;
	private String roundTrip;
	private boolean isExact;

	public boolean isExact() {
		return isExact;
	}

	public void setExact(boolean isExact) {
		this.isExact = isExact;
	}

	public String getRoundTrip() {
		return roundTrip;
	}

	public void setRoundTrip(String roundTrip) {
		this.roundTrip = roundTrip;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public long getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(long departureTime) {
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

	
}
