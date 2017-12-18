package com.cmpe275.project.mapper;

import java.util.List;

import com.cmpe275.project.model.Train;

public class SearchResults  {
	
	private List<Connection> connections;
	private String from;
	private String to;
	private String arvTime;
	private String depTime;
	private double price;
	private long dateOfTravel;
	private long paxs;
	
	
	
	public long getPaxs() {
		return paxs;
	}
	public void setPaxs(long paxs) {
		this.paxs = paxs;
	}
	public long getDateOfTravel() {
		return dateOfTravel;
	}
	public void setDateOfTravel(long dateOfTravel) {
		this.dateOfTravel = dateOfTravel;
	}
	public List<Connection> getConnections() {
		return connections;
	}
	public void setConnections(List<Connection> connections) {
		this.connections = connections;
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
