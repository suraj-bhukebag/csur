package com.cmpe275.project.CSUR.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TicketPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Double express;
	private Double regular;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Double getExpress() {
		return express;
	}
	public void setExpress(Double express) {
		this.express = express;
	}
	public Double getRegular() {
		return regular;
	}
	public void setRegular(Double regular) {
		this.regular = regular;
	}
	
	
	
}
