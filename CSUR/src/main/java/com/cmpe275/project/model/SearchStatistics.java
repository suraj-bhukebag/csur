package com.cmpe275.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.mysql.jdbc.log.Log;

@Entity
public class SearchStatistics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long date;
	
	private long noofsearchrequests;
	//private long typeofsearchid;
	private String typeofsearch;
	private long timeofrequest;
	private long timeofresponse;
	private long latency;
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
	public long getNoofsearchrequests() {
		return noofsearchrequests;
	}
	public void setNoofsearchrequests(long noofsearchrequests) {
		this.noofsearchrequests = noofsearchrequests;
	}
//	public long getTypeofsearchid() {
//		return typeofsearchid;
//	}
//	public void setTypeofsearchid(long typeofsearchid) {
//		this.typeofsearchid = typeofsearchid;
//	}
	public String getTypeofsearch() {
		return typeofsearch;
	}
	public void setTypeofsearch(String typeofsearch) {
		this.typeofsearch = typeofsearch;
	}
	public long getTimeofrequest() {
		return timeofrequest;
	}
	public void setTimeofrequest(long timeofrequest) {
		this.timeofrequest = timeofrequest;
	}
	public long getTimeofresponse() {
		return timeofresponse;
	}
	public void setTimeofresponse(long timeofresponse) {
		this.timeofresponse = timeofresponse;
	}
	public long getLatency() {
		return latency;
	}
	public void setLatency(long latency) {
		this.latency = latency;
	}
	
	
	

}
