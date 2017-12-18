package com.cmpe275.project.model;


import com.cmpe275.project.CSUR.mapper.TicketMapper;

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
    private long bookedBy;

//  Doubt Need to be asked

    // Akhilesh Commneted

////	@OneToOne
//	private User bookedby;
//	@OneToMany(mappedBy = "ticket")
//	private Set<Travellers> travellers;
//	@OneToMany(mappedBy = "ticket")
//	private Set<TicketDetails> ticketdetails;


    public Ticket(){};

    public Ticket(long numberofpassengers, String source, String destination, Double totalprice, long travellingdate, String triptype, long numberofconnections, long bookingDate, long bookedBy) {
        this.numberofpassengers = numberofpassengers;
        this.source = source;
        this.destination = destination;
        this.totalprice = totalprice;
        this.travellingdate = travellingdate;
        this.triptype = triptype;
        this.numberofconnections = numberofconnections;
        this.bookingDate = bookingDate;
        this.bookedBy = bookedBy;
    }


    public long getId() {
        return id;
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

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
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

    public long getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(long bookingDate) {
        this.bookingDate = bookingDate;
    }

    public long getBookedby() {
        return bookedBy;
    }

    public void setBookedby(long bookedby) {
        this.bookedBy = bookedby;
    }
}
