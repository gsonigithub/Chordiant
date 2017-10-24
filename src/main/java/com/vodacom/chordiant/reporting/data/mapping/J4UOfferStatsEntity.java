package com.vodacom.chordiant.reporting.data.mapping;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SequenceGenerator(name = "j4u_stats_id_gen", sequenceName = "j4u_stats_id_gen")
@Entity
@Table(name = "j4u_offer_stats")
public class J4UOfferStatsEntity {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "j4u_stats_id_gen")
	@Id
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_CREATED")
	private Date dateCreated;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "OFFER_EXISTS_IND")
	private String offerExistsInd;

	@Column(name = "RESPONSE")
	private String response;

	@Column(name = "FULFILMENT_IND")
	private String fulfilmentInd;

	@Column(name = "NUM_RECORDS")
	private long numRecords;

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
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the offerExistsInd
	 */
	public String getOfferExistsInd() {
		return offerExistsInd;
	}

	/**
	 * @param offerExistsInd
	 *            the offerExistsInd to set
	 */
	public void setOfferExistsInd(String offerExistsInd) {
		this.offerExistsInd = offerExistsInd;
	}

	/**
	 * @return the response
	 */
	public String getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(String response) {
		this.response = response;
	}

	/**
	 * @return the fulfilmentInd
	 */
	public String getFulfilmentInd() {
		return fulfilmentInd;
	}

	/**
	 * @param fulfilmentInd
	 *            the fulfilmentInd to set
	 */
	public void setFulfilmentInd(String fulfilmentInd) {
		this.fulfilmentInd = fulfilmentInd;
	}

	/**
	 * @return the numRecords
	 */
	public long getNumRecords() {
		return numRecords;
	}

	/**
	 * @param numRecords
	 *            the numRecords to set
	 */
	public void setNumRecords(long numRecords) {
		this.numRecords = numRecords;
	}

}
