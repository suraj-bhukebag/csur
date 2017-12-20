package com.cmpe275.project.mapper;

public class TrainCancelRequest {

	private long train_id;
	private long cancelDate;
	
	
	public long getTrain_id() {
		return train_id;
	}
	public void setTrain_id(long train_id) {
		this.train_id = train_id;
	}
	public long getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(long cancelDate) {
		this.cancelDate = cancelDate;
	}

}
