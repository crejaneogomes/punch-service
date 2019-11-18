package br.crog.api.models;

import java.util.List;

public class MonthSummary {

	private float totalHoursOnMonth;
	private String Moth;
	private List<DaySummary> detailsByDay;
	
	public float getTotalHoursOnMonth() {
		return totalHoursOnMonth;
	}
	public void setTotalHoursOnMonth(float totalHoursOnMonth) {
		this.totalHoursOnMonth = totalHoursOnMonth;
	}
	public String getMoth() {
		return Moth;
	}
	public void setMoth(String moth) {
		Moth = moth;
	}
	public List<DaySummary> getDaySummary() {
		return detailsByDay;
	}
	public void setDaySummary(List<DaySummary> daySummary) {
		this.detailsByDay = daySummary;
	}
}
