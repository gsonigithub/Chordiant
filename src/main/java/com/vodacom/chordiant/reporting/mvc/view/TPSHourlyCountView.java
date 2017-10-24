package com.vodacom.chordiant.reporting.mvc.view;

import java.sql.Timestamp;

public class TPSHourlyCountView {
	
	private Timestamp ts;
	private String channelName;
	private String type;
	private int amount;
	private float tps;
	private float l100;
	private float l200;
	private float l300;
	private float l400;
	private float l500;
	private float l600;
	private float l700;
	private float l800;
	private float l900;
	private float l1000;
	private int sla_target;
	public TPSHourlyCountView() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TPSHourlyCountView(Timestamp ts, String channelName, String type,
			int amount, float tps, float l100, float l200, float l300,
			float l400, float l500, float l600, float l700, float l800,
			float l900, float l1000, int sla_target) {
		super();
		this.ts = ts;
		this.channelName = channelName;
		this.type = type;
		this.amount = amount;
		this.tps = tps;
		this.l100 = l100;
		this.l200 = l200;
		this.l300 = l300;
		this.l400 = l400;
		this.l500 = l500;
		this.l600 = l600;
		this.l700 = l700;
		this.l800 = l800;
		this.l900 = l900;
		this.l1000 = l1000;
		this.sla_target = sla_target;
	}
	public Timestamp getTs() {
		return ts;
	}
	public void setTs(Timestamp ts) {
		this.ts = ts;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public float getL100() {
		return l100;
	}
	public void setL100(float l100) {
		this.l100 = l100;
	}
	public float getL200() {
		return l200;
	}
	public void setL200(float l200) {
		this.l200 = l200;
	}
	public float getL300() {
		return l300;
	}
	public void setL300(float l300) {
		this.l300 = l300;
	}
	public float getL400() {
		return l400;
	}
	public void setL400(float l400) {
		this.l400 = l400;
	}
	public float getL500() {
		return l500;
	}
	public void setL500(float l500) {
		this.l500 = l500;
	}
	public float getL600() {
		return l600;
	}
	public void setL600(float l600) {
		this.l600 = l600;
	}
	public float getL700() {
		return l700;
	}
	public void setL700(float l700) {
		this.l700 = l700;
	}
	public float getL800() {
		return l800;
	}
	public void setL800(float l800) {
		this.l800 = l800;
	}
	public float getL900() {
		return l900;
	}
	public void setL900(float l900) {
		this.l900 = l900;
	}
	public float getL1000() {
		return l1000;
	}
	public void setL1000(float l1000) {
		this.l1000 = l1000;
	}
	public int getSla_target() {
		return sla_target;
	}
	public void setSla_target(int sla_target) {
		this.sla_target = sla_target;
	}
	

}
