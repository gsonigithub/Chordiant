package com.vodacom.chordiant.reporting.mvc.view;

public class GetOffersCriteria {

	private String msisdn;
	private String server;
	private String ports;

	public GetOffersCriteria() {

	}

	public GetOffersCriteria(String msisdn, String server, String ports) {
		super();
		this.msisdn = msisdn;
		this.server = server;
		this.ports = ports;
	}

	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}

	/**
	 * @param msisdn
	 *            the msisdn to set
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @param server
	 *            the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * @return the ports
	 */
	public String getPorts() {
		return ports;
	}

	/**
	 * @param ports
	 *            the ports to set
	 */
	public void setPorts(String ports) {
		this.ports = ports;
	}

}
