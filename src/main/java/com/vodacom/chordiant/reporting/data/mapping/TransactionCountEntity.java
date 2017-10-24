package com.vodacom.chordiant.reporting.data.mapping;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * This entity will represent a table named auto_master_wls_transac_counts in
 * database.
 * 
 * @author Jeeresh.luchmun
 * 
 */

/*@Entity
@Table(name = "auto_master_wls_transac_counts")*/
public class TransactionCountEntity {

	@Transient
	@Id
	private String fakeId;

	@Id
	@Column(name = "request_date")
	private String requestDate;

	@Column(name = "url")
	private String url;

	@Column(name = "latency")
	private String latency;
	
	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLatency() {
		return latency;
	}

	public void setLatency(String latency) {
		this.latency = latency;
	}

	public String getFakeId() {
		return fakeId;
	}

	public void setFakeId(String fakeId) {
		this.fakeId = fakeId;
	}

}