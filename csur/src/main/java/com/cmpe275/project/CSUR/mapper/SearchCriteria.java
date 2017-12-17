package com.cmpe275.project.CSUR.mapper;


public class SearchCriteria {
	
	private int noOfPassengers;
	private String departureTime;
	private String from;
	private String to;
	private String ticketType;
	private int noOfConnections;
	private String roundTrip;
	private String isExact;	
	
	public String getIsExact() {
		return isExact;
	}
	public void setIsExact(String isExact) {
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
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public int getNoOfConnections() {
		return noOfConnections;
	}
	public void setNoOfConnections(int noOfConnections) {
		this.noOfConnections = noOfConnections;
	}
	
	

}
