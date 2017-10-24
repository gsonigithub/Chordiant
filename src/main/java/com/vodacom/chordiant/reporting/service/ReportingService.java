package com.vodacom.chordiant.reporting.service;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.vodacom.chordiant.reporting.mvc.view.AddAvailabilityForm;
import com.vodacom.chordiant.reporting.mvc.view.AvailabilityCriteriaView;
import com.vodacom.chordiant.reporting.mvc.view.DatabaseStatusView;
import com.vodacom.chordiant.reporting.mvc.view.DeploymentView;
import com.vodacom.chordiant.reporting.mvc.view.GetOffersCriteria;
import com.vodacom.chordiant.reporting.mvc.view.J4UOffersView;
import com.vodacom.chordiant.reporting.mvc.view.KPIHourlyCountView;
import com.vodacom.chordiant.reporting.mvc.view.MDMCountView;
import com.vodacom.chordiant.reporting.mvc.view.MDMDailyView;
import com.vodacom.chordiant.reporting.mvc.view.MDMStatsCriteriaView;
import com.vodacom.chordiant.reporting.mvc.view.MaterialisedView;
import com.vodacom.chordiant.reporting.mvc.view.OfferDBCountView;
import com.vodacom.chordiant.reporting.mvc.view.OfferDailyView;
import com.vodacom.chordiant.reporting.mvc.view.OfferView;
import com.vodacom.chordiant.reporting.mvc.view.OffersSelectionCriteria;
import com.vodacom.chordiant.reporting.mvc.view.PortMonitorView;
import com.vodacom.chordiant.reporting.mvc.view.TPSCountView;
import com.vodacom.chordiant.reporting.mvc.view.TPSHourlyCountView;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountReportingCriteriaView;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountView;
import com.vodacom.chordiant.reporting.mvc.view.WebServiceStatusView;
import com.vodacom.chordiant.reporting.mvc.view.OfferCountView;

/**
 * Reporting Service interface that will be the template for the main reporting
 * service implementation
 * 
 * @author Gaurav Soni
 * 
 */
public interface ReportingService {

	/**
	 * Retrieve the transaction count stats for any specific channel over a
	 * period of time for selected intervals
	 * 
	 * @param startDate
	 * @param endDate
	 * @param interval
	 * @param startTime
	 * @param endTime
	 * @param offerType
	 * @return
	 */
	List<TransactionCountView> getTransactionCountStatisticsForPeriod(
			String startDate, String endDate, String interval,
			String startTime, String endTime, String offerType);

	/**
	 * Retrieve MDM Statistics over a period of time for any specific car
	 * process
	 * 
	 * @param startDate
	 * @param endDate
	 * @param processName
	 * @return
	 */
	List<MDMCountView> getMDMStatisticsForPeriod(
			MDMStatsCriteriaView criteriaView);

	/**
	 * Retrieve offer logic deployments available in DB
	 * 
	 * @return
	 */
	List<DeploymentView> getOfferLogicDeployments();

	/**
	 * Returns count of offers presented during the last 15 min for the CC&R
	 * channel
	 * 
	 * @return
	 */
	OfferDailyView getCCROfferCount();

	/**
	 * Returns count of offers presented during the last 15 min for the PPFE
	 * channel
	 * 
	 * @return
	 */
	OfferDailyView getPPFECount();

	/**
	 * Returns count of offers presented during the last 15 min for the USSD
	 * channel
	 * 
	 * @return
	 */
	OfferDailyView getUSSDOfferCount();

	/**
	 * Returns the number of records loaded for each car process over last 4
	 * days including today
	 * 
	 * @return
	 */
	List<MDMDailyView> d();

	/**
	 * Returns the status of the J4U webservices on each node for hostname
	 * 
	 * @param hostname
	 * @return
	 * @throws MalformedURLException
	 */
	List<WebServiceStatusView> getWebServiceStatus(String hostname)
			throws MalformedURLException;

	/**
	 * Saves the data extracted on a daily level for J4U transaction KPIs
	 * 
	 * @param view
	 * @throws ParseException
	 */
	void saveDailyView(TransactionCountView view) throws ParseException;

	/**
	 * Saves half hourly stats
	 * 
	 * @param view
	 * @param string
	 * @throws ParseException
	 */
	void saveHalfHourlyView(TransactionCountView view) throws ParseException;

	/**
	 * Retrieves stats from the aggregated tables
	 * 
	 * @param startDate
	 * @param endDate
	 * @param intervals
	 * @param startTime
	 * @param endTime
	 * @param offerType
	 * @return
	 */
	List<TransactionCountView> getAggregatedStatsForPeriod(String startDate,
			String endDate, String intervals, String startTime, String endTime,
			String offerType);

	/**
	 * Retrieves stats from the aggregated tables
	 * 
	 * @param startDate
	 * @param endDate
	 * @param intervals
	 * @param startTime
	 * @param endTime
	 * @param offerType
	 * @return
	 */
	List<TransactionCountView> getStatsForPeriod(String startDate,
			String endDate);

	/**
	 * Saves the view for hourly transaction stats
	 * 
	 * @param view
	 * @param channel
	 * @throws ParseException
	 */
	void saveHourlyView(TransactionCountView view) throws ParseException;

	/**
	 * Saves the view for tps daily stats
	 * 
	 * @param view
	 * @param channel
	 * @throws ParseException
	 */
	void saveTpsView(TransactionCountView view, String channel)
			throws ParseException;

	/**
	 * Returns the daily view we want to see for offer count
	 * 
	 * @return
	 */
	OfferDailyView getOfferDailyView();

	/**
	 * Returns offers that were presented over a certain time period
	 * 
	 * @param criteria
	 * @return
	 */
	List<OfferView> getOffers(OffersSelectionCriteria criteria);

	/**
	 * Save availability form
	 * 
	 * @param addAvailabilityForm
	 */
	void saveAvailabilityForm(AddAvailabilityForm addAvailabilityForm);

	/**
	 * Retrieve chordiant availability stats for a period
	 * 
	 * @param criteriaView
	 * @return
	 */
	List<AddAvailabilityForm> getChordiantAvailabilityForPeriod(
			AvailabilityCriteriaView criteriaView);

	/**
	 * Find an availability report to edit
	 * 
	 * @param id
	 * @return
	 */
	AddAvailabilityForm findAvailabilityReportById(Long id);

	/**
	 * Deletes an availability report
	 * 
	 * @param id
	 */
	void deleteAvailabilityReportById(Long id);

	/**
	 * Retrieves offers as per criteria
	 * 
	 * @param criteriaView
	 * @return
	 */
	List<J4UOffersView> getOffersForCriteria(GetOffersCriteria criteriaView);

	/**
	 * Retrieves last ddl date for MV
	 * 
	 * @param viewname
	 * 
	 * @return
	 */
	List<MaterialisedView> getViewDetails(String viewname);

	/**
	 * This will query the various car tables for the DB specified
	 * 
	 * @return
	 */
	List<DatabaseStatusView> getCarDataAvailability(String database);

	/**
	 * Retrieves J4U offer stats for last hour
	 * 
	 * @return
	 */
	List<OfferView> getOffersForLastHour();

	/**
	 * Saves the J4U offers stats extracted
	 * 
	 * @param offers
	 * @param now
	 */
	void saveOffers(List<OfferView> offers, Date now);

	/**
	 * J4U offer stats for last 4 hours
	 * 
	 * @return
	 */
	List<OfferView> getOffersLastFourHours();

	/**
	 * Get count for number of items that were updated for recharge and get for
	 * last i hours
	 * 
	 * @param i
	 * @return
	 */
	int getRechargeAndGetCount(int i);
	
	List<KPIHourlyCountView> getKPIHourlyCount(String tableName);		//Count of Access logs distributed over all server instances

	List<TPSHourlyCountView> getTPSHourlyCount(String channelName );	//

	OfferCountView getOfferCount15min();

	List<OfferDBCountView> getOfferDBCount();

	List<MDMDailyView> getMDMDailyViews();

	List<TransactionCountView> getAggregatedStats(
			TransactionCountReportingCriteriaView criteriaView, String channel);

	List<TPSCountView> getTPSStats(
			TransactionCountReportingCriteriaView criteriaView, String channel);

	List<PortMonitorView> getPortStatus(String serverListAsString);
	
	

}
