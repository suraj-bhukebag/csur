package com.cmpe275.project.CSUR.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;


public class TicketMapper {



    private long trainId ;
    private int numberofPassenger ;
    private String source;
    private String destination ;
    private int price ;
    private long bookedBy;
    private String bookingDate ;
    private String tripType;
    private String travelingDate ;
    @JsonProperty("ticketDetail")
    private List<TicketDetailMapper> ticketDetailMapper;
    @JsonProperty("traveller")
    private List<TravellerMapper> travellerMapper;

    public TicketMapper(){

    };

    public TicketMapper(long trainId, int numberofPassenger, String source, String destination, int price, long bookedBy, String bookingDate, String tripType, String travelingDate, List<TicketDetailMapper> ticketDetailMapper, List<TravellerMapper> travellerMapper) {
        this.trainId = trainId;
        this.numberofPassenger = numberofPassenger;
        this.source = source;
        this.destination = destination;
        this.price = price;
        this.bookedBy = bookedBy;
        this.bookingDate = bookingDate;
        this.tripType = tripType;
        this.travelingDate = travelingDate;
        this.ticketDetailMapper = ticketDetailMapper;
        this.travellerMapper = travellerMapper;
    }

    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public int getNumberofPassenger() {
        return numberofPassenger;
    }

    public void setNumberofPassenger(int numberofPassenger) {
        this.numberofPassenger = numberofPassenger;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(long bookedBy) {
        this.bookedBy = bookedBy;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getTravelingDate() {
        return travelingDate;
    }

    public void setTravelingDate(String travelingDate) {
        this.travelingDate = travelingDate;
    }

    public List<TicketDetailMapper> getTicketDetailMapper() {
        return ticketDetailMapper;
    }

    public void setTicketDetailMapper(List<TicketDetailMapper> ticketDetailMapper) {
        this.ticketDetailMapper = ticketDetailMapper;
    }

    public List<TravellerMapper> getTravellerMapper() {
        return travellerMapper;
    }

    public void setTravellerMapper(List<TravellerMapper> travellerMapper) {
        this.travellerMapper = travellerMapper;
    }



}


