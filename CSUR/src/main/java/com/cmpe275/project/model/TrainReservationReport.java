package com.cmpe275.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TrainReservationReport {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long date;
	private long trainid;
	private Double trainreservationrate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public long getTrainid() {
		return trainid;
	}
	public void setTrainid(long trainid) {
		this.trainid = trainid;
	}
	public Double getTrainreservationrate() {
		return trainreservationrate;
	}
	public void setTrainreservationrate(Double trainreservationrate) {
		this.trainreservationrate = trainreservationrate;
	}

}
