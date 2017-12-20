package com.cmpe275.project.mapper;

import java.util.List;

public class DailySearchCountResponse extends GenericResponse {
	
	private List<DailySearchCount> dailySearchCounts;

	public List<DailySearchCount> getDailySearchCounts() {
		return dailySearchCounts;
	}

	public void setDailySearchCounts(List<DailySearchCount> dailySearchCounts) {
		this.dailySearchCounts = dailySearchCounts;
	}
}
