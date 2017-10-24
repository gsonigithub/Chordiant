package com.vodacom.chordiant.reporting.mvc.view;

import org.thymeleaf.util.StringUtils;

import com.vodacom.chordiant.reporting.utils.ReportingUtils;

public class AvailabilityCriteriaView {

	private String startDate;

	private String endDate;

	private String startTime = "00:00:00";

	private String endTime = "23:59:59";

	/**
	 * @return the startDate
	 */
	public String getStartDate() {

		if (StringUtils.isEmptyOrWhitespace(startDate)) {
			return ReportingUtils.getDateMinusDays(30);
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

		if (StringUtils.isEmptyOrWhitespace(endDate)) {
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

}
