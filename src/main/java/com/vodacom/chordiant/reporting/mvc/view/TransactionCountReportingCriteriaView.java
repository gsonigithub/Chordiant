package com.vodacom.chordiant.reporting.mvc.view;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class TransactionCountReportingCriteriaView {

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
			startDate = getDate(30);
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
			endDate = getDate(1);
		}
		
		return endDate;
	}

	private String getDate(int daysToSubstract) {
		
		DateTime dateTime = new DateTime();
		
		
		dateTime = dateTime.minusDays(daysToSubstract);
		
		Date date = dateTime.toDate();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		return format.format(date);
		
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
