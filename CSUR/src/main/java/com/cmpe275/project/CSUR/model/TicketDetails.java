package com.cmpe275.project.CSUR.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class TicketDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long ticketId;
	private long trainId;
	private String from;
	private String to;
	private long arrivalTime;
	private long departureTime;
	private Double price;
	private long sequenceNumber;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTicketId() {
		return ticketId;
	}
	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	public long getTrainId() {
		return trainId;
	}
	public void setTrainId(long trainId) {
		this.trainId = trainId;
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
	public long getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public long getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(long departureTime) {
		this.departureTime = departureTime;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public long getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	
}
