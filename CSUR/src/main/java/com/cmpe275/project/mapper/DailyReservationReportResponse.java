package com.cmpe275.project.mapper;

import java.util.List;

public class DailyReservationReportResponse extends GenericResponse{
	
	private List<DailyReservationRate> dailyReservationRates;

	public List<DailyReservationRate> getDailyReservationRates() {
		return dailyReservationRates;
	}

	public void setDailyReservationRates(List<DailyReservationRate> dailyReservationRates) {
		this.dailyReservationRates = dailyReservationRates;
	}

}
