package com.cmpe275.project.csur.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Train {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String trainnumber;
	private String startingstation;
	private String endingstation;
	private long capacity;
	private String type;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTrainnumber() {
		return trainnumber;
	}
	public void setTrainnumber(String trainnumber) {
		this.trainnumber = trainnumber;
	}
	public String getStartingstation() {
		return startingstation;
	}
	public void setStartingstation(String startingstation) {
		this.startingstation = startingstation;
	}
	public String getEndingstation() {
		return endingstation;
	}
	public void setEndingstation(String endingstation) {
		this.endingstation = endingstation;
	}
	public long getCapacity() {
		return capacity;
	}
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
