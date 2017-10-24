package com.vodacom.chordiant.reporting.mvc.view;

public class WLSFindingView {

	private Long id;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private String title = "tltles";
	private String description = "descriptions";
	private String outageType;
	private String affectedSystem;
	private Boolean planned = false;
	private String downtime;
	private String changeNumber;
	private String incidentNumber;
	private Boolean otherSystemOutage = false;
	private String otherSystemName;
	private String otherSystemDowntime;

	/**
	 * @return the affectedSystem
	 */
	public String getAffectedSystem() {
		return affectedSystem;
	}

	/**
	 * @param affectedSystem
	 *            the affectedSystem to set
	 */
	public void setAffectedSystem(String affectedSystem) {
		this.affectedSystem = affectedSystem;
	}

	/**
	 * @return the outageType
	 */
	public String getOutageType() {
		return outageType;
	}

	/**
	 * @param outageType
	 *            the outageType to set
	 */
	public void setOutageType(String outageType) {
		this.outageType = outageType;
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
	 * @return the downtime
	 */
	public String getDowntime() {
		return downtime;
	}

	/**
	 * @param downtime
	 *            the downtime to set
	 */
	public void setDowntime(String downtime) {
		this.downtime = downtime;
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
	 * @return the otherSystemOutage
	 */
	public Boolean getOtherSystemOutage() {
		return otherSystemOutage;
	}

	/**
	 * @param otherSystemOutage
	 *            the otherSystemOutage to set
	 */
	public void setOtherSystemOutage(Boolean otherSystemOutage) {
		this.otherSystemOutage = otherSystemOutage;
	}

	/**
	 * @return the otherSystemName
	 */
	public String getOtherSystemName() {
		return otherSystemName;
	}

	/**
	 * @param otherSystemName
	 *            the otherSystemName to set
	 */
	public void setOtherSystemName(String otherSystemName) {
		this.otherSystemName = otherSystemName;
	}

	/**
	 * @return the otherSystemDowntime
	 */
	public String getOtherSystemDowntime() {
		return otherSystemDowntime;
	}

	/**
	 * @param otherSystemDowntime
	 *            the otherSystemDowntime to set
	 */
	public void setOtherSystemDowntime(String otherSystemDowntime) {
		this.otherSystemDowntime = otherSystemDowntime;
	}

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
	 * @return the startDate
	 */
	public String getStartDate() {
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

}
