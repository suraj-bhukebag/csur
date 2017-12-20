package com.cmpe275.project.mapper;

import java.util.List;

public class TrainReservationReportResponse extends GenericResponse{
	
	private List<TrainReservationRate> trainReservationRate;

	public List<TrainReservationRate> getTrainReservationRate() {
		return trainReservationRate;
	}

	public void setTrainReservationRate(List<TrainReservationRate> trainReservationRate) {
		this.trainReservationRate = trainReservationRate;
	}
	
}
