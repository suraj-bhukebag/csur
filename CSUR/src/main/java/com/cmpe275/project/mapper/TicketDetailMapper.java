package com.cmpe275.project.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TicketDetailMapper {

        private long trainId ;
        private String from;
        private String to;
        private String deptTime;
        @JsonProperty("arrivalTime")
        private String arivalTime;
        @JsonProperty("sequenceNumber")
        private String sequence;

        public TicketDetailMapper(){};

    public long getTrainId() {
        return trainId;
    }

    public void setTrainId(long trainId) {
        this.trainId = trainId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDeptTime() {
        return deptTime;
    }

    public void setDeptTime(String deptTime) {
        this.deptTime = deptTime;
    }

    public String getArivalTime() {
        return arivalTime;
    }

    public void setArivalTime(String arivalTime) {
        this.arivalTime = arivalTime;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }
}

