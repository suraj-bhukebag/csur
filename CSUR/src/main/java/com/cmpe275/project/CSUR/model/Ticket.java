package com.cmpe275.project.CSUR.model;


import javax.persistence.*;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private long numberofpassengers;
	private String source;
	private String destination;
	private Double totalprice;
	private long travellingdate;
	private String triptype;
	private long numberofconnections;
	private long bookingDate;


	@OneToOne
	private User bookedby;
	@OneToMany(mappedBy = "ticket")
	private Set<Travellers> travellers;
	@OneToMany(mappedBy = "ticket")
	private Set<TicketDetails> ticketdetails;


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

	public long getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(long bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public User getBookedby() {
		return bookedby;
	}

	public void setBookedby(User bookedby) {
		this.bookedby = bookedby;
	}

	public long getTravellingdate() {
		return travellingdate;
	}

	public void setTravellingdate(long travellingdate) {
		this.travellingdate = travellingdate;
	}

	public String getTriptype() {
		return triptype;
	}

	public void setTriptype(String triptype) {
		this.triptype = triptype;
	}

	public long getNumberofconnections() {
		return numberofconnections;
	}

	public void setNumberofconnections(long numberofconnections) {
		this.numberofconnections = numberofconnections;
	}

	public Set<Travellers> getTravellers() {
		return travellers;
	}

	public void setTravellers(Set<Travellers> travellers) {
		this.travellers = travellers;
	}

	public Set<TicketDetails> getTicketdetails() {
		return ticketdetails;
	}

	public void setTicketdetails(Set<TicketDetails> ticketdetails) {
		this.ticketdetails = ticketdetails;
	}

}
