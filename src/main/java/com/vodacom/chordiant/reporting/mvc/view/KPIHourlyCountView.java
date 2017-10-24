package com.vodacom.chordiant.reporting.mvc.view;

public class KPIHourlyCountView {

	private String channelName;
	private String serverName;
	private int RTDS1;
	private int RTDS2;
	private int RTDS3;
	private int RTDS4;
	private int RTDS5;
	private int RTDS6;
	private int RTDS7;
	private int RTDS8;
	private int RTDS9;
	
	public KPIHourlyCountView() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public KPIHourlyCountView(String channelName, String serverName, int rTDS1,
			int rTDS2, int rTDS3, int rTDS4, int rTDS5, int rTDS6, int rTDS7,
			int rTDS8, int rTDS9) {
		super();
		this.channelName = channelName;
		this.serverName = serverName;
		RTDS1 = rTDS1;
		RTDS2 = rTDS2;
		RTDS3 = rTDS3;
		RTDS4 = rTDS4;
		RTDS5 = rTDS5;
		RTDS6 = rTDS6;
		RTDS7 = rTDS7;
		RTDS8 = rTDS8;
		RTDS9 = rTDS9;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public int getRTDS1() {
		return RTDS1;
	}
	public void setRTDS1(int rTDS1) {
		RTDS1 = rTDS1;
	}
	public int getRTDS2() {
		return RTDS2;
	}
	public void setRTDS2(int rTDS2) {
		RTDS2 = rTDS2;
	}
	public int getRTDS3() {
		return RTDS3;
	}
	public void setRTDS3(int rTDS3) {
		RTDS3 = rTDS3;
	}
	public int getRTDS4() {
		return RTDS4;
	}
	public void setRTDS4(int rTDS4) {
		RTDS4 = rTDS4;
	}
	public int getRTDS5() {
		return RTDS5;
	}
	public void setRTDS5(int rTDS5) {
		RTDS5 = rTDS5;
	}
	public int getRTDS6() {
		return RTDS6;
	}
	public void setRTDS6(int rTDS6) {
		RTDS6 = rTDS6;
	}
	public int getRTDS7() {
		return RTDS7;
	}
	public void setRTDS7(int rTDS7) {
		RTDS7 = rTDS7;
	}
	public int getRTDS8() {
		return RTDS8;
	}
	public void setRTDS8(int rTDS8) {
		RTDS8 = rTDS8;
	}
	public int getRTDS9() {
		return RTDS9;
	}
	public void setRTDS9(int rTDS9) {
		RTDS9 = rTDS9;
	}	
}
