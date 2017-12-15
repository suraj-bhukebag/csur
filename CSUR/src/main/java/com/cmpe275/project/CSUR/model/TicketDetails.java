package com.cmpe275.project.csur.model;


import javax.persistence.*;


@Entity
@Table(name = "ticketdetails")
public class TicketDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long ticketId;
    private long trainId;
    //@Column(name = "from")
    private String fromStation;
    //@Column(name = "to")
    private String toStation;
    private String arrivalTime;
    //@Column(name = "departureTime")
    private String deptTime;
    private String price;
    //Added by Akhilesh to access all tickets for particular users.
    private String userId;
    private String sequenceNumber;

    public TicketDetails(Long ticketId, Long trainId, String fromStation, String toStation, String arrivalTime, String deptTime, String price, String userId, String sequenceNumber) {
        this.ticketId = ticketId;
        this.trainId = trainId;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.arrivalTime = arrivalTime;
        this.deptTime = deptTime;
        this.price = price;
        this.userId = userId;
        this.sequenceNumber = sequenceNumber;
    }

    public TicketDetails() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getTicketId() {
        return ticketId;
    }


    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDeptTime() {
        return deptTime;
    }

    public void setDeptTime(String deptTime) {
        this.deptTime = deptTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
}
