package com.cmpe275.project.mapper;

import java.util.List;

public class DailySearchPercentageResponse extends GenericResponse {

	private List<DailySearchPercentage> dailySearchPercentages;

	public List<DailySearchPercentage> getDailySearchPercentages() {
		return dailySearchPercentages;
	}

	public void setDailySearchPercentages(List<DailySearchPercentage> dailySearchPercentages) {
		this.dailySearchPercentages = dailySearchPercentages;
	}
	
	
}
