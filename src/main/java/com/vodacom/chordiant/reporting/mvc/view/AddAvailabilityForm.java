package com.vodacom.chordiant.reporting.mvc.view;

import com.vodacom.chordiant.reporting.utils.ReportingUtils;


public class AddAvailabilityForm {

	private Long id;
	private String title;
	private String description;
	private String startDate;
	private String endDate;
	private String startTime = "00:00:00";
	private String endTime = "23:59:59";
	private Boolean planned;
	private String changeNumber;
	private String incidentNumber;
	private Boolean networkPowerOutage = false;
	private String totalDowntime;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		
		if(startDate == null) {
			return ReportingUtils.getTodaysDate();
		}
		
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		
		if(endDate == null) {
			return ReportingUtils.getTodaysDate();
		}
		
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the planned
	 */
	public Boolean getPlanned() {
		return planned;
	}

	/**
	 * @param planned
	 *            the planned to set
	 */
	public void setPlanned(Boolean planned) {
		this.planned = planned;
	}

	/**
	 * @return the changeNumber
	 */
	public String getChangeNumber() {
		return changeNumber;
	}

	/**
	 * @param changeNumber
	 *            the changeNumber to set
	 */
	public void setChangeNumber(String changeNumber) {
		this.changeNumber = changeNumber;
	}

	/**
	 * @return the incidentNumber
	 */
	public String getIncidentNumber() {
		return incidentNumber;
	}

	/**
	 * @param incidentNumber
	 *            the incidentNumber to set
	 */
	public void setIncidentNumber(String incidentNumber) {
		this.incidentNumber = incidentNumber;
	}

	/**
	 * @return the networkPowerOutage
	 */
	public Boolean getNetworkPowerOutage() {
		return networkPowerOutage;
	}

	/**
	 * @param networkPowerOutage
	 *            the networkPowerOutage to set
	 */
	public void setNetworkPowerOutage(Boolean networkPowerOutage) {
		this.networkPowerOutage = networkPowerOutage;
	}

	/**
	 * @return the totalDowntime
	 */
	public String getTotalDowntime() {
		return totalDowntime;
	}

	/**
	 * @param totalDowntime
	 *            the totalDowntime to set
	 */
	public void setTotalDowntime(String totalDowntime) {
		this.totalDowntime = totalDowntime;
	}

}
