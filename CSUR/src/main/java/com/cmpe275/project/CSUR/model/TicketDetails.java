package com.cmpe275.project.CSUR.model;


import javax.persistence.*;


@Entity
@Table(name = "ticketdetails")
public class TicketDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;
    private Long trainId;
    //@Column(name = "from")
    private String fromStation;
    //@Column(name = "to")
    private String toStation;
    private String arrivalTime;
    //@Column(name = "departureTime")
    private String deptTime;
    private String price;
    private String sequenceNumber;

    public TicketDetails() {
    }



    public Long getTicketId() {
        return ticketId;
    }

    public TicketDetails(String fromStation, String toStation, String arrivalTime, String deptTime, String price, String sequenceNumber) {
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.arrivalTime = arrivalTime;
        this.deptTime = deptTime;
        this.price = price;
        this.sequenceNumber = sequenceNumber;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getTrainId() {
        return trainId;
    }

    public void setTrainId(Long trainId) {
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
