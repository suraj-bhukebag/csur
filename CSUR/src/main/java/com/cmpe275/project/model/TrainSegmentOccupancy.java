package com.cmpe275.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="train_segment_occupancy")
public class TrainSegmentOccupancy {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long date;
	private long trainid;
	private long segmentid;
	private long noofticketsbooked;
	private Double segmentoccupancyrate;
	
	public TrainSegmentOccupancy(){
		
	}
	
	public TrainSegmentOccupancy(long t, Double r ){
		trainid = t;
		segmentoccupancyrate = r;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public long getTrainid() {
		return trainid;
	}
	public void setTrainid(long trainid) {
		this.trainid = trainid;
	}
	public long getSegmentid() {
		return segmentid;
	}
	public void setSegmentid(long segmentid) {
		this.segmentid = segmentid;
	}
	public long getNoofticketsbooked() {
		return noofticketsbooked;
	}
	public void setNoofticketsbooked(long noofticketsbooked) {
		this.noofticketsbooked = noofticketsbooked;
	}
	public Double getSegmentoccupancyrate() {
		return segmentoccupancyrate;
	}
	public void setSegmentoccupancyrate(Double segmentoccupancyrate) {
		this.segmentoccupancyrate = segmentoccupancyrate;
	}
	
}
