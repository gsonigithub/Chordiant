package com.vodacom.chordiant.reporting.mvc.view;

public class OffersSelectionCriteria {

	private String dateReportingOn;

	public OffersSelectionCriteria() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OffersSelectionCriteria(String dateReportingOn) {
		super();
		this.dateReportingOn = dateReportingOn;
	}

	/**
	 * @return the dateReportingOn
	 */
	public String getDateReportingOn() {
		return dateReportingOn;
	}

	/**
	 * @param dateReportingOn
	 *            the dateReportingOn to set
	 */
	public void setDateReportingOn(String dateReportingOn) {
		this.dateReportingOn = dateReportingOn;
	}

}
