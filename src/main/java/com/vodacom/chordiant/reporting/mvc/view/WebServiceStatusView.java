package com.vodacom.chordiant.reporting.mvc.view;

public class WebServiceStatusView {

	private int port;
	private int offersAvailable;
	private boolean status;
	private long responseTime;

	public WebServiceStatusView(int port, int offersAvailable, boolean status, long responseTime) {
		super();
		this.port = port;
		this.offersAvailable = offersAvailable;
		this.status = status;
		this.responseTime = responseTime;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return the offersAvailable
	 */
	public int getOffersAvailable() {
		return offersAvailable;
	}

	/**
	 * @param offersAvailable
	 *            the offersAvailable to set
	 */
	public void setOffersAvailable(int offersAvailable) {
		this.offersAvailable = offersAvailable;
	}

	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}

}
