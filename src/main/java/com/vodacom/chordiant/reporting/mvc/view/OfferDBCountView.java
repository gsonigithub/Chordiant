package com.vodacom.chordiant.reporting.mvc.view;

import java.sql.Timestamp;

public class OfferDBCountView {
	
	private String offeredOn;
	private int ussd;
	private int ppfe;
	private int mobiApp;
	public OfferDBCountView() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OfferDBCountView(String offeredOn, int ussd, int ppfe, int mobiApp) {
		super();
		this.offeredOn = offeredOn;
		this.ussd = ussd;
		this.ppfe = ppfe;
		this.mobiApp = mobiApp;
	}
	public String getOfferedOn() {
		return offeredOn;
	}
	public void setOfferedOn(String offeredOn) {
		this.offeredOn = offeredOn;
	}
	public int getUssd() {
		return ussd;
	}
	public void setUssd(int ussd) {
		this.ussd = ussd;
	}
	public int getPpfe() {
		return ppfe;
	}
	public void setPpfe(int ppfe) {
		this.ppfe = ppfe;
	}
	public int getMobiApp() {
		return mobiApp;
	}
	public void setMobiApp(int mobiApp) {
		this.mobiApp = mobiApp;
	}

}
