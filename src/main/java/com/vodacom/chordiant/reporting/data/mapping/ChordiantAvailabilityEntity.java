package com.vodacom.chordiant.reporting.data.mapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.vodacom.chordiant.reporting.data.repository.AttachmentEntity;

@SequenceGenerator(name = "CHORDIANT_AVAIL_ID_GEN", sequenceName = "CHORDIANT_AVAIL_ID_GEN")
@Entity
@Table(name = "cdt_avail_report")
public class ChordiantAvailabilityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHORDIANT_AVAIL_ID_GEN")
	private Long id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "START_DATE")
	private String startDate;

	@Column(name = "END_DATE")
	private String endDate;

	@Column(name = "START_TIME")
	private String startTime;

	@Column(name = "END_TIME")
	private String endTime;

	@Type(type = "yes_no")
	@Column(name = "PLANNED")
	private Boolean planned;

	@Column(name = "CHANGE_NR")
	private String changeNumber;

	@Column(name = "INCIDENT_NR")
	private String incidentNumber;

	@Type(type = "yes_no")
	@Column(name = "NETWORK_POWER_OUTAGE")
	private Boolean networkPowerOutage;

	@Column(name = "TOTAL_DOWNTIME")
	private String totalDowntime;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "chordiantAvailability")
	List<AttachmentEntity> attachmentEntities = new ArrayList<>();

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the planned
	 */
	public Boolean getPlanned() {
		return planned;
	}

	/**
	 * @param planned
	 *            the planned to set
	 */
	public void setPlanned(Boolean planned) {
		this.planned = planned;
	}

	/**
	 * @return the changeNumber
	 */
	public String getChangeNumber() {
		return changeNumber;
	}

	/**
	 * @param changeNumber
	 *            the changeNumber to set
	 */
	public void setChangeNumber(String changeNumber) {
		this.changeNumber = changeNumber;
	}

	/**
	 * @return the incidentNumber
	 */
	public String getIncidentNumber() {
		return incidentNumber;
	}

	/**
	 * @param incidentNumber
	 *            the incidentNumber to set
	 */
	public void setIncidentNumber(String incidentNumber) {
		this.incidentNumber = incidentNumber;
	}

	/**
	 * @return the networkPowerOutage
	 */
	public Boolean getNetworkPowerOutage() {
		return networkPowerOutage;
	}

	/**
	 * @param networkPowerOutage
	 *            the networkPowerOutage to set
	 */
	public void setNetworkPowerOutage(Boolean networkPowerOutage) {
		this.networkPowerOutage = networkPowerOutage;
	}

	/**
	 * @return the totalDowntime
	 */
	public String getTotalDowntime() {
		return totalDowntime;
	}

	/**
	 * @param totalDowntime
	 *            the totalDowntime to set
	 */
	public void setTotalDowntime(String totalDowntime) {
		this.totalDowntime = totalDowntime;
	}

	/**
	 * @return the attachmentEntities
	 */
	public List<AttachmentEntity> getAttachmentEntities() {
		return attachmentEntities;
	}

	/**
	 * @param attachmentEntities
	 *            the attachmentEntities to set
	 */
	public void setAttachmentEntities(List<AttachmentEntity> attachmentEntities) {
		this.attachmentEntities = attachmentEntities;
	}

	public boolean addAttachmentEntity(AttachmentEntity attachmentEntity) {
		attachmentEntity.setChordiantAvailability(this);
		return attachmentEntities.add(attachmentEntity);
	}

}
