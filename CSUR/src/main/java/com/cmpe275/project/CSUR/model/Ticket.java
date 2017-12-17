package com.cmpe275.project.CSUR.model;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long numberofpassengers;
	private String source;
	private String destination;
	private Double totalPrice;
	private long bookedby;
	private long travellingDate;
	private String tripType;
	private long numberOfConnections;
	private long bookingDate;






	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getNumberofpassengers() {
		return numberofpassengers;
	}
	public void setNumberofpassengers(long numberofpassengers) {
		this.numberofpassengers = numberofpassengers;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public long getBookedby() {
		return bookedby;
	}
	public void setBookedby(long bookedby) {
		this.bookedby = bookedby;
	}
	public long getTravellingDate() {
		return travellingDate;
	}
	public void setTravellingDate(long travellingDate) {
		this.travellingDate = travellingDate;
	}
	public String getTripType() {
		return tripType;
	}
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public long getNumberOfConnections() {
		return numberOfConnections;
	}
	public void setNumberOfConnections(long numberOfConnections) {
		this.numberOfConnections = numberOfConnections;
	}
	public long getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(long bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	
}
