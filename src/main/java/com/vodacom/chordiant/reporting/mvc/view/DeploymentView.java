package com.vodacom.chordiant.reporting.mvc.view;

public class DeploymentView {

	private int packageId;
	private int packageFamily;
	private String project;
	private int version;
	private int containerPackageId;
	private String status;
	
	
	public DeploymentView(int packageId,int packageFamily,String project,int version,int containerPackageId,String status){
		this.packageId=packageId;
		this.packageFamily=packageFamily;
		this.project=project;
		this.version = version;
		this.containerPackageId=containerPackageId;
		this.status=status;
		
	}
	public DeploymentView() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the packageId
	 */
	public int getPackageId() {
		return packageId;
	}
	/**
	 * @param packageId the packageId to set
	 */
	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}
	/**
	 * @return the packageFamily
	 */
	public int getPackageFamily() {
		return packageFamily;
	}
	/**
	 * @param packageFamily the packageFamily to set
	 */
	public void setPackageFamily(int packageFamily) {
		this.packageFamily = packageFamily;
	}
	/**
	 * @return the project
	 */
	public String getProject() {
		return project;
	}
	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return the containerPackageId
	 */
	public int getContainerPackageId() {
		return containerPackageId;
	}
	/**
	 * @param containerPackageId the containerPackageId to set
	 */
	public void setContainerPackageId(int containerPackageId) {
		this.containerPackageId = containerPackageId;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
