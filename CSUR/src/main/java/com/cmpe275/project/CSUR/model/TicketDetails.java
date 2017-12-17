package com.cmpe275.project.CSUR.model;

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

	@ManyToOne
	private Ticket ticket;

	@OneToOne
	private Train train;

	private String fromstation;
	private String tostation;
	private String arrivaltime;
	private String depttime;
	private String price;
	private String sequencenumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Train getTrain() {
		return train;
	}

	public void setTrain(Train train) {
		this.train = train;
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
