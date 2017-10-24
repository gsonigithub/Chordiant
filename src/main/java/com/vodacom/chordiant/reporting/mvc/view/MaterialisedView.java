package com.vodacom.chordiant.reporting.mvc.view;

public class MaterialisedView {

	private String objectName;
	private String objectType;
	private String lastDdl;

	/**
	 * @return the objectName
	 */
	public String getObjectName() {
		return objectName;
	}

	/**
	 * @param objectName
	 *            the objectName to set
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	/**
	 * @return the objectType
	 */
	public String getObjectType() {
		return objectType;
	}

	/**
	 * @param objectType
	 *            the objectType to set
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	/**
	 * @return the lastDdl
	 */
	public String getLastDdl() {
		return lastDdl;
	}

	/**
	 * @param lastDdl
	 *            the lastDdl to set
	 */
	public void setLastDdl(String lastDdl) {
		this.lastDdl = lastDdl;
	}

}
