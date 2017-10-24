package com.vodacom.chordiant.reporting.mvc.view;

public class DatabaseView {
	private String tableName;
	private int numOfRecords;

	public DatabaseView(String tableName, int numOfRecords) {
		this.setTableName(tableName);
		this.setNumOfRecords(numOfRecords);
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the numOfRecords
	 */
	public int getNumOfRecords() {
		return numOfRecords;
	}

	/**
	 * @param numOfRecords the numOfRecords to set
	 */
	public void setNumOfRecords(int numOfRecords) {
		this.numOfRecords = numOfRecords;
	}
	
}
