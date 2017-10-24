package com.vodacom.chordiant.reporting.mvc.view;

import java.util.List;

import dm.com.chordiant.dm.ra.Product;

public class J4UOffersView {

	String hostname;
	String port;
	List<Product> products;

	public J4UOffersView(String port, String hostname, List<Product> products) {
		this.port = port;
		this.hostname = hostname;
		this.products = products;
	}

	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * @param hostname
	 *            the hostname to set
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the products
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * @param products
	 *            the products to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
