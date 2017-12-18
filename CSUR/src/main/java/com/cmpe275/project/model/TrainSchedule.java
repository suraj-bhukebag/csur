package com.cmpe275.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TrainSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne
	private Train train;

	private long stationId;

	private String arrivaltime;

	private String departuretime;

	private long arvtime;

	private long deptime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
	}

	public long getStationId() {
		return stationId;
	}

	public void setStationId(long stationId) {
		this.stationId = stationId;
	}

	public String getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public String getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}

	public long getArvtime() {
		return arvtime;
	}

	public void setArvtime(long arvtime) {
		this.arvtime = arvtime;
	}

	public long getDeptime() {
		return deptime;
	}

	public void setDeptime(long deptime) {
		this.deptime = deptime;
	}

}
