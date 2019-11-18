package br.crog.api.models;

import java.util.Date;
import java.util.List;

/**
 * This class keeps track of a user's punchs per day
 * @see java.lang.Object
 * @author crog
 *
 */
public class DaySummary {

	private float totalHours;
	private String restIntervalLaw;
	private String Status;
	private Date date;
	private List<Punch> Punchs;

	
	/**
	* get the list of punchs in a day
	* @return the <code>List</code> specifying the punchs.
	*/
	public List<Punch> getPunchs() {
		return Punchs;
	}

	/**
	* Configure the punch list in a day
	* @param punchs the list Of the punchs.
	*/
	public void setPunchs(List<Punch> punchs) {
		Punchs = punchs;
	}

	/**
	* get the date of the day informations
	* @return the <code>Date</code> specifying the date of day summary informations.
	*/
	public Date getDate() {
		return date;
	}

	/**
	* Configure the date of the informations
	* @param date the date.
	*/
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	* get the status of the punchs in a day
	* @return the <code>string</code> with IRREGULAR or REGULAR state.
	*/
	public String getStatus() {
		return Status;
	}

	/**
	* Configure the status
	* @param punchs the list Of the punchs.
	*/
	public void setStatus(String status) {
		Status = status;
	}

	/**
	* get the total of hours worked in a day
	* @return the <code>float</code> specifying the total of hours worked.
	*/
	public float getTotalHours() {
		return totalHours;
	}

	/**
	* Configure total of hours value
	* @param punchs the list Of the punchs.
	*/
	public void setTotalHours(float totalHours) {
		this.totalHours = totalHours;
	}

	/**
	* get the rest interval law message
	* @return the <code>string</code> specifying rest interval law that was not fulfilled, it can be null.
	*/
	public String getRestIntervalLaw() {
		return restIntervalLaw;
	}

	/**
	* Configure the rest interval law message
	* @param intervalLaw the message
	*/
	public void setRestIntervalLaw(String intervalLaw) {
		this.restIntervalLaw = intervalLaw;
	}
}
