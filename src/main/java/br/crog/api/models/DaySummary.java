package br.crog.api.models;

import java.util.Date;
import java.util.List;

public class DaySummary {

	private float totalHours;
	private String restIntervalLaw;
	private String Status;
	private Date date;
	private List<Punch> Punchs;

	public List<Punch> getPunchs() {
		return Punchs;
	}

	public void setPunchs(List<Punch> punchs) {
		Punchs = punchs;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public float getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(float totalHours) {
		this.totalHours = totalHours;
	}

	public String getRestIntervalLaw() {
		return restIntervalLaw;
	}

	public void setRestIntervalLaw(String intervalLaw) {
		this.restIntervalLaw = intervalLaw;
	}
}
