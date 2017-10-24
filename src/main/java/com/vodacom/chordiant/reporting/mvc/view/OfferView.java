package com.vodacom.chordiant.reporting.mvc.view;

public class OfferView {

	private String username;
	private String offerExistsInd;
	private String response;
	private String fulfilmentInd;
	private long numRecords;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the offerExistsInd
	 */
	public String getOfferExistsInd() {
		return offerExistsInd;
	}

	/**
	 * @param offerExistsInd
	 *            the offerExistsInd to set
	 */
	public void setOfferExistsInd(String offerExistsInd) {
		this.offerExistsInd = offerExistsInd;
	}

	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * @return the fulfilmentInd
	 */
	public String getFulfilmentInd() {
		return fulfilmentInd;
	}

	/**
	 * @param fulfilmentInd
	 *            the fulfilmentInd to set
	 */
	public void setFulfilmentInd(String fulfilmentInd) {
		this.fulfilmentInd = fulfilmentInd;
	}

	/**
	 * @return the numRecords
	 */
	public long getNumRecords() {
		return numRecords;
	}

	/**
	 * @param numRecords
	 *            the numRecords to set
	 */
	public void setNumRecords(long numRecords) {
		this.numRecords = numRecords;
	}

}
