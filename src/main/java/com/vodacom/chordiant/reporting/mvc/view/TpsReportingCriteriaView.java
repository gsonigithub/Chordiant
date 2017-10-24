package com.vodacom.chordiant.reporting.mvc.view;

import com.vodacom.chordiant.reporting.utils.ReportingUtils;

public class TpsReportingCriteriaView {

	private String startDate;

	private String endDate;

	private String offerType;

	private String intervals = "daily";

	private String startTime="00:00";

	private String endTime="23:59";

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		
		if(startDate == null) {
			startDate = ReportingUtils.getYesterdaysDate();
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
			endDate = ReportingUtils.getYesterdaysDate();
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
	 * @return the offerType
	 */
	public String getOfferType() {
		return offerType;
	}

	/**
	 * @param offerType
	 *            the offerType to set
	 */
	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	/**
	 * @return the interval
	 */
	public String getIntervals() {
		return intervals;
	}

	/**
	 * @param interval
	 *            the interval to set
	 */
	public void setIntervals(String intervals) {
		this.intervals = intervals;
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

}
