package com.vodacom.chordiant.reporting.mvc.view;

public class TPSCountView {
	
	private String ts;
	private int amount;
	private float tps;
	private float maxTps;
	public TPSCountView() {
		super();		
	}
	public TPSCountView(String ts, int amount, float tps, float maxTps) {
		super();
		this.ts = ts;
		this.amount = amount;
		this.tps = tps;
		this.maxTps = maxTps;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getTps() {
		return tps;
	}
	public void setTps(float tps) {
		this.tps = tps;
	}
	public float getMaxTps() {
		return maxTps;
	}
	public void setMaxTps(float maxTps) {
		this.maxTps = maxTps;
	}
	
}
