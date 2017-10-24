package com.vodacom.chordiant.reporting.mvc.view;

public class MDMDailyView {
	private String processName;
	private int loadedRecordToday;
	private int loadedRecordOneDayAgo;
	private int loadedRecordTwoDayAgo;
	private int loadedRecordThreeDayAgo;
	private int loadedRecordFourDayAgo;

	

	public MDMDailyView(String processName, int loadedRecordToday, int loadedRecordOneDayday, int loadedRecordTwoDayday, int loadedRecordThreeDayday, int loadedRecordFourDayday) {
		this.processName=processName;
		this.loadedRecordToday = loadedRecordToday;
		this.setLoadedRecordOneDayAgo(loadedRecordOneDayday);
		this.setLoadedRecordTwoDayAgo(loadedRecordTwoDayday);
		this.setLoadedRecordThreeDayAgo(loadedRecordThreeDayday);
		this.setLoadedRecordFourDayAgo(loadedRecordFourDayday);
		
	}

	public MDMDailyView() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the processName
	 */
	public String getProcessName() {
		return processName;
	}

	/**
	 * @param processName the processName to set
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	/**
	 * @return the loadedRecordToday
	 */
	public int getLoadedRecordToday() {
		return loadedRecordToday;
	}

	/**
	 * @param loadedRecordToday the loadedRecordToday to set
	 */
	public void setLoadedRecordToday(int loadedRecordToday) {
		this.loadedRecordToday = loadedRecordToday;
	}

	/**
	 * @return the loadedRecordOneDayAgo
	 */
	public int getLoadedRecordOneDayAgo() {
		return loadedRecordOneDayAgo;
	}

	/**
	 * @param loadedRecordOneDayAgo the loadedRecordOneDayAgo to set
	 */
	public void setLoadedRecordOneDayAgo(int loadedRecordOneDayAgo) {
		this.loadedRecordOneDayAgo = loadedRecordOneDayAgo;
	}

	/**
	 * @return the loadedRecordTwoDayAgo
	 */
	public int getLoadedRecordTwoDayAgo() {
		return loadedRecordTwoDayAgo;
	}

	/**
	 * @param loadedRecordTwoDayAgo the loadedRecordTwoDayAgo to set
	 */
	public void setLoadedRecordTwoDayAgo(int loadedRecordTwoDayAgo) {
		this.loadedRecordTwoDayAgo = loadedRecordTwoDayAgo;
	}

	/**
	 * @return the loadedRecordThreeDayAgo
	 */
	public int getLoadedRecordThreeDayAgo() {
		return loadedRecordThreeDayAgo;
	}

	/**
	 * @param loadedRecordThreeDayAgo the loadedRecordThreeDayAgo to set
	 */
	public void setLoadedRecordThreeDayAgo(int loadedRecordThreeDayAgo) {
		this.loadedRecordThreeDayAgo = loadedRecordThreeDayAgo;
	}

	/**
	 * @return the loadedRecordFourDayAgo
	 */
	public int getLoadedRecordFourDayAgo() {
		return loadedRecordFourDayAgo;
	}

	/**
	 * @param loadedRecordFourDayAgo the loadedRecordFourDayAgo to set
	 */
	public void setLoadedRecordFourDayAgo(int loadedRecordFourDayAgo) {
		this.loadedRecordFourDayAgo = loadedRecordFourDayAgo;
	}


}
