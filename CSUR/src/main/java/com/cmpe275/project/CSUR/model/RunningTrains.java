package com.cmpe275.project.CSUR.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RunningTrains {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long trainId;
	private long date;
	private String status;
	private long availablecount;
	private long ticketsBooked;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTrainId() {
		return trainId;
	}
	public void setTrainId(long trainId) {
		this.trainId = trainId;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getAvailablecount() {
		return availablecount;
	}
	public void setAvailablecount(long availablecount) {
		this.availablecount = availablecount;
	}
	public long getTicketsBooked() {
		return ticketsBooked;
	}
	public void setTicketsBooked(long ticketsBooked) {
		this.ticketsBooked = ticketsBooked;
	}
	
	
	
}
