package com.cmpe275.project.mapper;

import com.cmpe275.project.model.TicketDetails;
import com.cmpe275.project.model.Travellers;

import java.util.List;

public class Ticket {



    private int numberofPassenger ;
    private String source;
    private String destination ;
    private int price ;
    private long bookedBy;
    private String bookingDate ;
    private String tripType;
    private String travelingDate ;
    private List<TicketDetail> ticketDetail ;
    private List<Traveller> traveller ;

    public Ticket(int numberofPassenger, String source, String destination, int price, long bookedBy, String bookingDate, String tripType, String travelingDate, List<TicketDetail> ticketDetail, List<Traveller> traveller) {
        this.numberofPassenger = numberofPassenger;
        this.source = source;
        this.destination = destination;
        this.price = price;
        this.bookedBy = bookedBy;
        this.bookingDate = bookingDate;
        this.tripType = tripType;
        this.travelingDate = travelingDate;
        this.ticketDetail = ticketDetail;
        this.traveller = traveller;
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

    public List<TicketDetail> getTicketDetail() {
        return ticketDetail;
    }

    public void setTicketDetail(List<TicketDetail> ticketDetail) {
        this.ticketDetail = ticketDetail;
    }

    public List<Traveller> getTraveller() {
        return traveller;
    }

    public void setTraveller(List<Traveller> traveller) {
        this.traveller = traveller;
    }

    public class TicketDetail {
        private String from;
        private String to;
        private String deptTime;
        private String arivalTime;
        private String sequence;


    }
    public class Traveller {

        private String name;
        private String gender;
        private String age ;

        public Traveller(String name, String gender, String age) {
            this.name = name;
            this.gender = gender;
            this.age = age;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

}


