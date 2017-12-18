package com.cmpe275.project.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticketdetails")
public class TicketDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

//	@ManyToOne
//	private Ticket ticket;
//
//	@OneToOne
//	private Train train;

	private long ticketId;
	private long trainId;

	private String fromstation;
	private String tostation;

	private String arrivaltime;
	private String depttime;
	private String price;
	private String sequencenumber;

	public TicketDetails(){};

	public TicketDetails(long ticketId, long trainId, String fromstation, String tostation, String arrivaltime, String depttime, String price, String sequencenumber) {
		this.ticketId = ticketId;
		this.trainId = trainId;
		this.fromstation = fromstation;
		this.tostation = tostation;
		this.arrivaltime = arrivaltime;
		this.depttime = depttime;
		this.price = price;
		this.sequencenumber = sequencenumber;
	}



	public long getId() {
		return id;
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

	public String getFromstation() {
		return fromstation;
	}

	public void setFromstation(String fromstation) {
		this.fromstation = fromstation;
	}

	public String getTostation() {
		return tostation;
	}

	public void setTostation(String tostation) {
		this.tostation = tostation;
	}

	public String getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(String arrivaltime) {
		this.arrivaltime = arrivaltime;
	}

	public String getDepttime() {
		return depttime;
	}

	public void setDepttime(String depttime) {
		this.depttime = depttime;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSequencenumber() {
		return sequencenumber;
	}

	public void setSequencenumber(String sequencenumber) {
		this.sequencenumber = sequencenumber;
	}
}