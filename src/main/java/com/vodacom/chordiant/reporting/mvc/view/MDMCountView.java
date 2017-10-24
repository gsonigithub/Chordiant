package com.vodacom.chordiant.reporting.mvc.view;

public class MDMCountView {
	
	private String processName;
	private String  processedDate;
	private long  loadedRecords;
	private long  runTime;
	
	

	public MDMCountView(String processName, String processedDate,
			long loadedRecords, long runTime) {
		super();
		this.processName = processName;
		this.processedDate = processedDate;
		this.loadedRecords = loadedRecords;
		this.runTime = runTime;
	}

	public MDMCountView() {
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
	 * @return the processedDate
	 */
	public String getProcessedDate() {
		return processedDate;
	}

	/**
	 * @param processedDate the processedDate to set
	 */
	public void setProcessedDate(String processedDate) {
		this.processedDate = processedDate;
	}

	/**
	 * @return the loadedRecords
	 */
	public long getLoadedRecords() {
		return loadedRecords;
	}

	/**
	 * @param loadedRecords the loadedRecords to set
	 */
	public void setLoadedRecords(long loadedRecords) {
		this.loadedRecords = loadedRecords;
	}

	/**
	 * @return the runTime
	 */
	public long getRunTime() {
		return runTime;
	}

	/**
	 * @param runTime the runTime to set
	 */
	public void setRunTime(long runTime) {
		this.runTime = runTime;
	}
	
	

}
