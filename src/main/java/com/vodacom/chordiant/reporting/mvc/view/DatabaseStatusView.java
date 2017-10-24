package com.vodacom.chordiant.reporting.mvc.view;

public class DatabaseStatusView {

	private String tableName;
	private Boolean dataAvailable;
	private long timeTaken;

	public DatabaseStatusView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatabaseStatusView(String tableName, Boolean dataAvailable,
			long timeTaken) {
		super();
		this.tableName = tableName;
		this.dataAvailable = dataAvailable;
		this.timeTaken = timeTaken;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName
	 *            the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the dataAvailable
	 */
	public Boolean getDataAvailable() {
		return dataAvailable;
	}

	/**
	 * @param dataAvailable
	 *            the dataAvailable to set
	 */
	public void setDataAvailable(Boolean dataAvailable) {
		this.dataAvailable = dataAvailable;
	}

	/**
	 * @return the timeTaken
	 */
	public long getTimeTaken() {
		return timeTaken;
	}

	/**
	 * @param timeTaken
	 *            the timeTaken to set
	 */
	public void setTimeTaken(long timeTaken) {
		this.timeTaken = timeTaken;
	}

}
