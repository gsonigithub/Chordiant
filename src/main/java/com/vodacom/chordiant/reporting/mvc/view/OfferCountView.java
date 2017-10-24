package com.vodacom.chordiant.reporting.mvc.view;

public class OfferCountView {
	
	private int ussdShown;
	private int ussdAccepted;
	private int ppfeShown;
	private int ccrShown;
	private int ccrAccepted;
	public OfferCountView() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OfferCountView(int ussdShown, int ussdAccepted, int ppfeShown,
			int ccrShown, int ccrAccepted) {
		super();
		this.ussdShown = ussdShown;
		this.ussdAccepted = ussdAccepted;
		this.ppfeShown = ppfeShown;
		this.ccrShown = ccrShown;
		this.ccrAccepted = ccrAccepted;
	}
	public int getUssdShown() {
		return ussdShown;
	}
	public void setUssdShown(int ussdShown) {
		this.ussdShown = ussdShown;
	}
	public int getUssdAccepted() {
		return ussdAccepted;
	}
	public void setUssdAccepted(int ussdAccepted) {
		this.ussdAccepted = ussdAccepted;
	}
	public int getPpfeShown() {
		return ppfeShown;
	}
	public void setPpfeShown(int ppfeShown) {
		this.ppfeShown = ppfeShown;
	}
	public int getCcrShown() {
		return ccrShown;
	}
	public void setCcrShown(int ccrShown) {
		this.ccrShown = ccrShown;
	}
	public int getCcrAccepted() {
		return ccrAccepted;
	}
	public void setCcrAccepted(int ccrAccepted) {
		this.ccrAccepted = ccrAccepted;
	}
	

}
