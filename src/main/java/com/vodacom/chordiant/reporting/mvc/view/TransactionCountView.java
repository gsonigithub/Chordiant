package com.vodacom.chordiant.reporting.mvc.view;


public class TransactionCountView {

	private String requestDate;
	private String url;
	private long transactionCount;
	private float averageLatency;

	private float countSlaUnder100;
	private float countSla100To200;
	private float countSla200To300;
	private float countSla300To400;
	private float countSla400To500;
	private float countSla500To600;
	private float countSla600To700;
	private float countSla700To800;
	private float countSla800To900;
	private float countSla900To1000;
	private float countSlaOver1000;

	private float inSlaPerc400;
	private float inSlaPerc500;
	private float inSlaPerc600;
	private float inSlaPerc700;
	private float inSlaPerc800;
	private float inSlaPerc900;
	private float inSlaPerc1000;
	private float inSlaPerc1000Plus;

	private float slaTarget;
	private String interval;
	private String requestTime;
	private float tps110;

	public TransactionCountView() {

	}

	public TransactionCountView(String requestDate, String url,
			long transactionCount, float averageLatency,
			float countSlaUnder100, float countSla100To200,
			float countSla200To300, float countSla300To400,
			float countSla400To500, float countSla500To600,
			float countSla600To700, float countSla700To800,
			float countSla800To900, float countSla900To1000,
			float countSlaOver1000, float inSlaPerc400, float inSlaPerc500,
			float inSlaPerc600, float inSlaPerc700, float inSlaPerc800,
			float inSlaPerc900, float inSlaPerc1000, float inSlaPerc1000Plus,
			float slaTarget, String interval, String requestTime, float tps110) {
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
		this.inSlaPerc400 = inSlaPerc400;
		this.inSlaPerc500 = inSlaPerc500;
		this.inSlaPerc600 = inSlaPerc600;
		this.inSlaPerc700 = inSlaPerc700;
		this.inSlaPerc800 = inSlaPerc800;
		this.inSlaPerc900 = inSlaPerc900;
		this.inSlaPerc1000 = inSlaPerc1000;
		this.inSlaPerc1000Plus = inSlaPerc1000Plus;
		this.slaTarget = slaTarget;
		this.interval = interval;
		this.requestTime = requestTime;
		this.tps110 = tps110;
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
	 * @return the inSlaPerc400
	 */
	public float getInSlaPerc400() {
		return inSlaPerc400;
	}

	/**
	 * @param inSlaPerc400
	 *            the inSlaPerc400 to set
	 */
	public void setInSlaPerc400(float inSlaPerc400) {
		this.inSlaPerc400 = inSlaPerc400;
	}

	/**
	 * @return the inSlaPerc500
	 */
	public float getInSlaPerc500() {
		return inSlaPerc500;
	}

	/**
	 * @param inSlaPerc500
	 *            the inSlaPerc500 to set
	 */
	public void setInSlaPerc500(float inSlaPerc500) {
		this.inSlaPerc500 = inSlaPerc500;
	}

	/**
	 * @return the inSlaPerc600
	 */
	public float getInSlaPerc600() {
		return inSlaPerc600;
	}

	/**
	 * @param inSlaPerc600
	 *            the inSlaPerc600 to set
	 */
	public void setInSlaPerc600(float inSlaPerc600) {
		this.inSlaPerc600 = inSlaPerc600;
	}

	/**
	 * @return the inSlaPerc700
	 */
	public float getInSlaPerc700() {
		return inSlaPerc700;
	}

	/**
	 * @param inSlaPerc700
	 *            the inSlaPerc700 to set
	 */
	public void setInSlaPerc700(float inSlaPerc700) {
		this.inSlaPerc700 = inSlaPerc700;
	}

	/**
	 * @return the inSlaPerc800
	 */
	public float getInSlaPerc800() {
		return inSlaPerc800;
	}

	/**
	 * @param inSlaPerc800
	 *            the inSlaPerc800 to set
	 */
	public void setInSlaPerc800(float inSlaPerc800) {
		this.inSlaPerc800 = inSlaPerc800;
	}

	/**
	 * @return the inSlaPerc900
	 */
	public float getInSlaPerc900() {
		return inSlaPerc900;
	}

	/**
	 * @param inSlaPerc900
	 *            the inSlaPerc900 to set
	 */
	public void setInSlaPerc900(float inSlaPerc900) {
		this.inSlaPerc900 = inSlaPerc900;
	}

	/**
	 * @return the inSlaPerc1000
	 */
	public float getInSlaPerc1000() {
		return inSlaPerc1000;
	}

	/**
	 * @param inSlaPerc1000
	 *            the inSlaPerc1000 to set
	 */
	public void setInSlaPerc1000(float inSlaPerc1000) {
		this.inSlaPerc1000 = inSlaPerc1000;
	}

	/**
	 * @return the inSlaPerc1000Plus
	 */
	public float getInSlaPerc1000Plus() {
		return inSlaPerc1000Plus;
	}

	/**
	 * @param inSlaPerc1000Plus
	 *            the inSlaPerc1000Plus to set
	 */
	public void setInSlaPerc1000Plus(float inSlaPerc1000Plus) {
		this.inSlaPerc1000Plus = inSlaPerc1000Plus;
	}

	/**
	 * @return the slaTarget
	 */
	public float getSlaTarget() {
		return slaTarget;
	}

	/**
	 * @param slaTarget
	 *            the slaTarget to set
	 */
	public void setSlaTarget(float slaTarget) {
		this.slaTarget = slaTarget;
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

	/**
	 * @return the tps110
	 */
	public float getTps110() {
		return tps110;
	}

	/**
	 * @param tps110
	 *            the tps110 to set
	 */
	public void setTps110(float tps110) {
		this.tps110 = tps110;
	}

}
