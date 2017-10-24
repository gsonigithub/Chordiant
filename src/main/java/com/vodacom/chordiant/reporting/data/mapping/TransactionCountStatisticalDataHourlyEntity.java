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
import javax.persistence.Transient;

@SequenceGenerator(name = "hourly_stats_id_gen", sequenceName = "hourly_stats_id_gen")
@Entity
@Table(name = "TRNCOUNT_STATS_HOURLY")
public class TransactionCountStatisticalDataHourlyEntity {

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hourly_stats_id_gen")
	@Id
	private Long id;

	@Column(name = "REQUEST_DATE")
	private String requestDate;

	@Column(name = "URL")
	private String url;

	@Column(name = "TRN_COUNT")
	private long transactionCount;

	@Column(name = "AVG_LATENCY")
	private float averageLatency;

	@Column(name = "CNT_UNDER_100")
	private float countSlaUnder100;

	@Column(name = "CNT_100_TO_200")
	private float countSla100To200;

	@Column(name = "CNT_200_TO_300")
	private float countSla200To300;

	@Column(name = "CNT_300_TO_400")
	private float countSla300To400;

	@Column(name = "CNT_400_TO_500")
	private float countSla400To500;

	@Column(name = "CNT_500_TO_600")
	private float countSla500To600;

	@Column(name = "CNT_600_TO_700")
	private float countSla600To700;

	@Column(name = "CNT_700_TO_800")
	private float countSla700To800;

	@Column(name = "CNT_800_TO_900")
	private float countSla800To900;

	@Column(name = "CNT_900_TO_1000")
	private float countSla900To1000;

	@Column(name = "CNT_OVER_1000")
	private float countSlaOver1000;

	@Column(name = "SLA_400_PERC")
	private float sla400Perc;

	@Column(name = "SLA_500_PERC")
	private float sla500Perc;

	@Column(name = "SLA_600_PERC")
	private float sla600Perc;

	@Column(name = "SLA_700_PERC")
	private float sla700Perc;

	@Column(name = "SLA_800_PERC")
	private float sla800Perc;

	@Column(name = "SLA_900_PERC")
	private float sla900Perc;

	@Column(name = "SLA_1000_PERC")
	private float sla1000Perc;

	@Column(name = "SLA_1000_PLUS_PERC")
	private float sla1000plusPerc;

	@Column(name = "SLA_PERC")
	private float slaPerc;

	@Column(name = "INTERVAL")
	private String interval;

	@Column(name = "REQUEST_TIME")
	private String requestTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "REQ_DATE")
	private Date reqDate;

	public TransactionCountStatisticalDataHourlyEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionCountStatisticalDataHourlyEntity(String requestDate,
			String url, long transactionCount, float averageLatency,
			float countSlaUnder100, float countSla100To200,
			float countSla200To300, float countSla300To400,
			float countSla400To500, float countSla500To600,
			float countSla600To700, float countSla700To800,
			float countSla800To900, float countSla900To1000,
			float countSlaOver1000, float sla400Perc, float sla500Perc,
			float sla600Perc, float sla700Perc, float sla800Perc,
			float sla900Perc, float sla1000Perc, float sla1000plusPerc,
			float slaPerc, String interval, String requestTime, Date reqDate) {
		super();
		this.requestDate = requestDate;
		this.url = url;
		this.transactionCount = transactionCount;
		this.averageLatency = averageLatency;
		this.countSlaUnder100 = countSlaUnder100;
		this.countSla100To200 = countSla100To200;
		this.countSla200To300 = countSla200To300;
		this.countSla300To400 = countSla300To400;
		this.countSla400To500 = countSla400To500;
		this.countSla500To600 = countSla500To600;
		this.countSla600To700 = countSla600To700;
		this.countSla700To800 = countSla700To800;
		this.countSla800To900 = countSla800To900;
		this.countSla900To1000 = countSla900To1000;
		this.countSlaOver1000 = countSlaOver1000;
		this.sla400Perc = sla400Perc;
		this.sla500Perc = sla500Perc;
		this.sla600Perc = sla600Perc;
		this.sla700Perc = sla700Perc;
		this.sla800Perc = sla800Perc;
		this.sla900Perc = sla900Perc;
		this.sla1000Perc = sla1000Perc;
		this.sla1000plusPerc = sla1000plusPerc;
		this.slaPerc = slaPerc;
		this.interval = interval;
		this.requestTime = requestTime;
		this.reqDate = reqDate;
	}

	/**
	 * @return the reqDate
	 */
	public Date getReqDate() {
		return reqDate;
	}

	/**
	 * @param reqDate
	 *            the reqDate to set
	 */
	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	/**
	 * @return the countSlaUnder100
	 */
	public float getCountSlaUnder100() {
		return countSlaUnder100;
	}

	/**
	 * @param countSlaUnder100
	 *            the countSlaUnder100 to set
	 */
	public void setCountSlaUnder100(float countSlaUnder100) {
		this.countSlaUnder100 = countSlaUnder100;
	}

	/**
	 * @return the countSla100To200
	 */
	public float getCountSla100To200() {
		return countSla100To200;
	}

	/**
	 * @param countSla100To200
	 *            the countSla100To200 to set
	 */
	public void setCountSla100To200(float countSla100To200) {
		this.countSla100To200 = countSla100To200;
	}

	/**
	 * @return the countSla200To300
	 */
	public float getCountSla200To300() {
		return countSla200To300;
	}

	/**
	 * @param countSla200To300
	 *            the countSla200To300 to set
	 */
	public void setCountSla200To300(float countSla200To300) {
		this.countSla200To300 = countSla200To300;
	}

	/**
	 * @return the countSla300To400
	 */
	public float getCountSla300To400() {
		return countSla300To400;
	}

	/**
	 * @param countSla300To400
	 *            the countSla300To400 to set
	 */
	public void setCountSla300To400(float countSla300To400) {
		this.countSla300To400 = countSla300To400;
	}

	/**
	 * @return the countSla400To500
	 */
	public float getCountSla400To500() {
		return countSla400To500;
	}

	/**
	 * @param countSla400To500
	 *            the countSla400To500 to set
	 */
	public void setCountSla400To500(float countSla400To500) {
		this.countSla400To500 = countSla400To500;
	}

	/**
	 * @return the countSla500To600
	 */
	public float getCountSla500To600() {
		return countSla500To600;
	}

	/**
	 * @param countSla500To600
	 *            the countSla500To600 to set
	 */
	public void setCountSla500To600(float countSla500To600) {
		this.countSla500To600 = countSla500To600;
	}

	/**
	 * @return the countSla600To700
	 */
	public float getCountSla600To700() {
		return countSla600To700;
	}

	/**
	 * @param countSla600To700
	 *            the countSla600To700 to set
	 */
	public void setCountSla600To700(float countSla600To700) {
		this.countSla600To700 = countSla600To700;
	}

	/**
	 * @return the countSla700To800
	 */
	public float getCountSla700To800() {
		return countSla700To800;
	}

	/**
	 * @param countSla700To800
	 *            the countSla700To800 to set
	 */
	public void setCountSla700To800(float countSla700To800) {
		this.countSla700To800 = countSla700To800;
	}

	/**
	 * @return the countSla800To900
	 */
	public float getCountSla800To900() {
		return countSla800To900;
	}

	/**
	 * @param countSla800To900
	 *            the countSla800To900 to set
	 */
	public void setCountSla800To900(float countSla800To900) {
		this.countSla800To900 = countSla800To900;
	}

	/**
	 * @return the countSla900To1000
	 */
	public float getCountSla900To1000() {
		return countSla900To1000;
	}

	/**
	 * @param countSla900To1000
	 *            the countSla900To1000 to set
	 */
	public void setCountSla900To1000(float countSla900To1000) {
		this.countSla900To1000 = countSla900To1000;
	}

	/**
	 * @return the countSlaOver1000
	 */
	public float getCountSlaOver1000() {
		return countSlaOver1000;
	}

	/**
	 * @param countSlaOver1000
	 *            the countSlaOver1000 to set
	 */
	public void setCountSlaOver1000(float countSlaOver1000) {
		this.countSlaOver1000 = countSlaOver1000;
	}

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
	 * @return the requestDate
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate
	 *            the requestDate to set
	 */
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the transactionCount
	 */
	public long getTransactionCount() {
		return transactionCount;
	}

	/**
	 * @param transactionCount
	 *            the transactionCount to set
	 */
	public void setTransactionCount(long transactionCount) {
		this.transactionCount = transactionCount;
	}

	/**
	 * @return the averageLatency
	 */
	public float getAverageLatency() {
		return averageLatency;
	}

	/**
	 * @param averageLatency
	 *            the averageLatency to set
	 */
	public void setAverageLatency(float averageLatency) {
		this.averageLatency = averageLatency;
	}

	/**
	 * @return the sla400Perc
	 */
	public float getSla400Perc() {
		return sla400Perc;
	}

	/**
	 * @param sla400Perc
	 *            the sla400Perc to set
	 */
	public void setSla400Perc(float sla400Perc) {
		this.sla400Perc = sla400Perc;
	}

	/**
	 * @return the sla500Perc
	 */
	public float getSla500Perc() {
		return sla500Perc;
	}

	/**
	 * @param sla500Perc
	 *            the sla500Perc to set
	 */
	public void setSla500Perc(float sla500Perc) {
		this.sla500Perc = sla500Perc;
	}

	/**
	 * @return the sla600Perc
	 */
	public float getSla600Perc() {
		return sla600Perc;
	}

	/**
	 * @param sla600Perc
	 *            the sla600Perc to set
	 */
	public void setSla600Perc(float sla600Perc) {
		this.sla600Perc = sla600Perc;
	}

	/**
	 * @return the sla700Perc
	 */
	public float getSla700Perc() {
		return sla700Perc;
	}

	/**
	 * @param sla700Perc
	 *            the sla700Perc to set
	 */
	public void setSla700Perc(float sla700Perc) {
		this.sla700Perc = sla700Perc;
	}

	/**
	 * @return the sla800Perc
	 */
	public float getSla800Perc() {
		return sla800Perc;
	}

	/**
	 * @param sla800Perc
	 *            the sla800Perc to set
	 */
	public void setSla800Perc(float sla800Perc) {
		this.sla800Perc = sla800Perc;
	}

	/**
	 * @return the sla900Perc
	 */
	public float getSla900Perc() {
		return sla900Perc;
	}

	/**
	 * @param sla900Perc
	 *            the sla900Perc to set
	 */
	public void setSla900Perc(float sla900Perc) {
		this.sla900Perc = sla900Perc;
	}

	/**
	 * @return the sla1000Perc
	 */
	public float getSla1000Perc() {
		return sla1000Perc;
	}

	/**
	 * @param sla1000Perc
	 *            the sla1000Perc to set
	 */
	public void setSla1000Perc(float sla1000Perc) {
		this.sla1000Perc = sla1000Perc;
	}

	/**
	 * @return the sla1000plusPerc
	 */
	public float getSla1000plusPerc() {
		return sla1000plusPerc;
	}

	/**
	 * @param sla1000plusPerc
	 *            the sla1000plusPerc to set
	 */
	public void setSla1000plusPerc(float sla1000plusPerc) {
		this.sla1000plusPerc = sla1000plusPerc;
	}

	/**
	 * @return the slaPerc
	 */
	public float getSlaPerc() {
		return slaPerc;
	}

	/**
	 * @param slaPerc
	 *            the slaPerc to set
	 */
	public void setSlaPerc(float slaPerc) {
		this.slaPerc = slaPerc;
	}

	/**
	 * @return the interval
	 */
	public String getInterval() {
		return interval;
	}

	/**
	 * @param interval
	 *            the interval to set
	 */
	public void setInterval(String interval) {
		this.interval = interval;
	}

	/**
	 * @return the requestTime
	 */
	public String getRequestTime() {
		return requestTime;
	}

	/**
	 * @param requestTime
	 *            the requestTime to set
	 */
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}

}
