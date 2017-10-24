package com.vodacom.chordiant.reporting.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;











import com.vodacom.chordiant.reporting.data.mapping.ChordiantAvailabilityEntity;
import com.vodacom.chordiant.reporting.data.mapping.J4UOfferStatsEntity;
import com.vodacom.chordiant.reporting.data.mapping.TransactionCountStatisticalDataDailyEntity;
import com.vodacom.chordiant.reporting.data.mapping.TransactionCountStatisticalDataHalfHourEntity;
import com.vodacom.chordiant.reporting.data.mapping.TransactionCountStatisticalDataHourlyEntity;
import com.vodacom.chordiant.reporting.data.mapping.TransactionCountStatisticalDataTpsEntity;
import com.vodacom.chordiant.reporting.data.repository.ChordiantAvailabilityRepository;
import com.vodacom.chordiant.reporting.data.repository.DefaultDataRepository;
import com.vodacom.chordiant.reporting.data.repository.J4UOfferStatsRepository;
import com.vodacom.chordiant.reporting.data.repository.PortStatusRepository;
import com.vodacom.chordiant.reporting.data.repository.TransactionCountDailyStatsRepository;
import com.vodacom.chordiant.reporting.data.repository.TransactionCountHalfHourlyStatsRepository;
import com.vodacom.chordiant.reporting.data.repository.TransactionCountHourlyStatsRepository;
import com.vodacom.chordiant.reporting.data.repository.TransactionCountTpsStatsRepository;
import com.vodacom.chordiant.reporting.data.repository.TransactionStatisticsRepository;
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
import com.vodacom.chordiant.reporting.service.ReportingService;
import com.vodacom.chordiant.reporting.utils.ReportingUtils;

import dm.com.vodafone.dm.ws.ProductArray;
import dm.com.vodafone.dm.ws.Request;
import dm.vodafone.com.DecisionControllerWS_Service;

/**
 * Default reporting service class
 * 
 * @author Gaurav Soni
 * 
 */
@Component
@Transactional
public class DefaultReportingService implements ReportingService {

	@Autowired
	DefaultDataRepository repository;

	@Autowired
	TransactionCountDailyStatsRepository dailyStatsRepository;

	@Autowired
	TransactionCountHalfHourlyStatsRepository transactionCountHalfHourlyStatsRepository;

	@Autowired
	TransactionCountHourlyStatsRepository transactionCountHourlyStatsRepository;

	@Autowired
	TransactionCountTpsStatsRepository tpsStatsRepository;

	@Autowired
	ChordiantAvailabilityRepository chordiantAvailabilityRepository;
	
	@Autowired
	J4UOfferStatsRepository j4uOfferStatsRepository;
	
	@Autowired
	TransactionStatisticsRepository transactionStatisticsRepository;
	
	@Autowired
	PortStatusRepository portStatusRepository;
	
	@Value("${car.tableList}")
	String carTablesAsString;
	
	@Value("${msisdn}")
	private String msisdn;
	
	@Value("${srcSystem}")
	private String srcSystem;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TransactionCountView> getTransactionCountStatisticsForPeriod(
			String startDate, String endDate, String interval,
			String startTime, String endTime, String offerType) {
		return repository.getTransactionCountStatisticsForPeriod(startDate,
				endDate, interval, startTime, endTime, offerType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<MDMCountView> getMDMStatisticsForPeriod(
			MDMStatsCriteriaView criteriaView) {
		return repository.getMDMStatisticsForPeriod(criteriaView);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DeploymentView> getOfferLogicDeployments() {
		return repository.getOfferLogicDeployments();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfferDailyView getCCROfferCount() {
		return repository.getCCROfferCount();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfferDailyView getPPFECount() {
		return repository.getPPFECount();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OfferDailyView getUSSDOfferCount() {
		return repository.getUSSDOfferCount();
	}

	/**
	 * {@inheritDoc}
	 */


	
	// qcho001zafsrh.vodacom.corp
	String urlPart1 = "http://";
	String urlPart2 = ":703";
	String urlPart3 = "/rtds/webservices-adapter/DecisionControllerWS?WSDL";

	/**
	 * {@inheritDoc}
	 * 
	 * @throws MalformedURLException
	 */
	@Override
	public List<WebServiceStatusView> getWebServiceStatus(String hostname)
			throws MalformedURLException {

		List<WebServiceStatusView> list = new ArrayList<>();

		for (int portNum = 1; portNum < 10; portNum++) {

			String url = urlPart1 + hostname + urlPart2 + portNum + urlPart3;

			try {

				DecisionControllerWS_Service service = new DecisionControllerWS_Service(
						new URL(url));

				Request request = new Request();
				request.setMSISDN(msisdn);
				request.setSourceSystem(srcSystem);
				long start = System.currentTimeMillis();
				ProductArray array = service.getDecisionControllerWS()
						.getOffers(request);

				list.add(new WebServiceStatusView(7030+portNum, array.getProducts().size(), true,System.currentTimeMillis()-start ));

			} catch (Exception ex) {
				list.add(new WebServiceStatusView(7030 + portNum, 0, false,0));
			}
			// System.out.println("Num of offers:" +
			// array.getProducts().size());

		}
		return list;

	}

	/**
	 * {@inheritDoc}
	 * @throws ParseException 
	 * 
	 */
	@Override
	public void saveDailyView(TransactionCountView view) throws ParseException {
		dailyStatsRepository
				.save(new TransactionCountStatisticalDataDailyEntity(view
						.getRequestDate(), view.getUrl(), view
						.getTransactionCount(), view.getAverageLatency(), view
						.getCountSlaUnder100(), view.getCountSla100To200(),
						view.getCountSla200To300(), view.getCountSla300To400(),
						view.getCountSla400To500(), view.getCountSla500To600(),
						view.getCountSla600To700(), view.getCountSla700To800(),
						view.getCountSla800To900(),
						view.getCountSla900To1000(),
						view.getCountSlaOver1000(), view.getInSlaPerc400(),
						view.getInSlaPerc500(), view.getInSlaPerc600(), view
								.getInSlaPerc700(), view.getInSlaPerc800(),
						view.getInSlaPerc900(), view.getInSlaPerc1000(), view
								.getInSlaPerc1000Plus(), view.getSlaTarget(),
						view.getInterval(), ReportingUtils.convertToDateTime(view.getRequestDate())));
	}

	@Override
	public void saveHalfHourlyView(TransactionCountView view) throws ParseException {
		transactionCountHalfHourlyStatsRepository
				.save(new TransactionCountStatisticalDataHalfHourEntity(view
						.getRequestDate(), view.getUrl(), view
						.getTransactionCount(), view.getAverageLatency(), view
						.getCountSlaUnder100(), view.getCountSla100To200(),
						view.getCountSla200To300(), view.getCountSla300To400(),
						view.getCountSla400To500(), view.getCountSla500To600(),
						view.getCountSla600To700(), view.getCountSla700To800(),
						view.getCountSla800To900(),
						view.getCountSla900To1000(),
						view.getCountSlaOver1000(), view.getInSlaPerc400(),
						view.getInSlaPerc500(), view.getInSlaPerc600(), view
								.getInSlaPerc700(), view.getInSlaPerc800(),
						view.getInSlaPerc900(), view.getInSlaPerc1000(), view
								.getInSlaPerc1000Plus(), view.getSlaTarget(),
						view.getInterval(), view.getRequestTime(), ReportingUtils.convertToDateTime(view.getRequestDate())));

	}

	@Override
	public List<TransactionCountView> getAggregatedStatsForPeriod(
			String startDate, String endDate, String intervals,
			String startTime, String endTime, String offerType) {

		List<TransactionCountView> views = null;
		
		if (StringUtils.equalsIgnoreCase("daily", intervals)) {
			List<TransactionCountStatisticalDataDailyEntity> dailyEntities = dailyStatsRepository
					.findForPeriod(startDate, endDate, "%" + offerType + "%");

			views = convertToViews(dailyEntities);
		} else if (StringUtils.equalsIgnoreCase("halfHour", intervals)) {
			List<TransactionCountStatisticalDataHalfHourEntity> halfHourlyEntities = transactionCountHalfHourlyStatsRepository
					.findForPeriod(startDate, endDate, "%" + offerType + "%");

			views = convertToHalfHourlyViews(halfHourlyEntities);
		} else if (StringUtils.equalsIgnoreCase("hourly", intervals)) {
			List<TransactionCountStatisticalDataHourlyEntity> hourlyEntities = transactionCountHourlyStatsRepository
					.findForPeriod(startDate, endDate, "%" + offerType + "%");

			views = convertToHourlyViews(hourlyEntities);
		} else if (StringUtils.equalsIgnoreCase("tps", intervals)) {
			List<TransactionCountStatisticalDataTpsEntity> tpsEntities = tpsStatsRepository
					.findForPeriod(startDate, endDate, "%" + offerType + "%");

			views = convertToTpsViews(tpsEntities);
		} else if (StringUtils.equalsIgnoreCase("monthly", intervals)
				|| StringUtils.equalsIgnoreCase("yearly", intervals)) {

			views = repository.getTransactionCountStatisticsForPeriod(
					startDate, endDate, intervals, startTime, endTime,
					offerType);
		}

		return views;
	}

	private List<TransactionCountView> convertToTpsViews(
			List<TransactionCountStatisticalDataTpsEntity> tpsEntities) {

		List<TransactionCountView> views = new ArrayList<>();

		for (TransactionCountStatisticalDataTpsEntity entity : tpsEntities) {
			views.add(new TransactionCountView(entity.getRequestDate(), entity
					.getUrl(), entity.getTransactionCount(), entity
					.getAverageLatency(), entity.getCountSlaUnder100(), entity
					.getCountSla100To200(), entity.getCountSla200To300(),
					entity.getCountSla300To400(), entity.getCountSla400To500(),
					entity.getCountSla500To600(), entity.getCountSla600To700(),
					entity.getCountSla700To800(), entity.getCountSla800To900(),
					entity.getCountSla900To1000(),
					entity.getCountSlaOver1000(), entity.getSla400Perc(),
					entity.getSla500Perc(), entity.getSla600Perc(), entity
							.getSla700Perc(), entity.getSla800Perc(), entity
							.getSla900Perc(), entity.getSla1000Perc(), entity
							.getSla1000plusPerc(), entity.getSlaPerc(), entity
							.getInterval(), entity.getRequestTime(), entity
							.getTps110()));
		}

		return views;
	}

	private List<TransactionCountView> convertToHourlyViews(
			List<TransactionCountStatisticalDataHourlyEntity> hourlyEntities) {

		List<TransactionCountView> views = new ArrayList<>();

		for (TransactionCountStatisticalDataHourlyEntity entity : hourlyEntities) {
			views.add(new TransactionCountView(entity.getRequestDate(), entity
					.getUrl(), entity.getTransactionCount(), entity
					.getAverageLatency(), entity.getCountSlaUnder100(), entity
					.getCountSla100To200(), entity.getCountSla200To300(),
					entity.getCountSla300To400(), entity.getCountSla400To500(),
					entity.getCountSla500To600(), entity.getCountSla600To700(),
					entity.getCountSla700To800(), entity.getCountSla800To900(),
					entity.getCountSla900To1000(),
					entity.getCountSlaOver1000(), entity.getSla400Perc(),
					entity.getSla500Perc(), entity.getSla600Perc(), entity
							.getSla700Perc(), entity.getSla800Perc(), entity
							.getSla900Perc(), entity.getSla1000Perc(), entity
							.getSla1000plusPerc(), entity.getSlaPerc(), entity
							.getInterval(), entity.getRequestTime(), 0f));
		}

		return views;
	}

	private List<TransactionCountView> convertToHalfHourlyViews(
			List<TransactionCountStatisticalDataHalfHourEntity> halfHourlyEntities) {

		List<TransactionCountView> views = new ArrayList<>();

		for (TransactionCountStatisticalDataHalfHourEntity entity : halfHourlyEntities) {
			views.add(new TransactionCountView(entity.getRequestDate(), entity
					.getUrl(), entity.getTransactionCount(), entity
					.getAverageLatency(), entity.getCountSlaUnder100(), entity
					.getCountSla100To200(), entity.getCountSla200To300(),
					entity.getCountSla300To400(), entity.getCountSla400To500(),
					entity.getCountSla500To600(), entity.getCountSla600To700(),
					entity.getCountSla700To800(), entity.getCountSla800To900(),
					entity.getCountSla900To1000(),
					entity.getCountSlaOver1000(), entity.getSla400Perc(),
					entity.getSla500Perc(), entity.getSla600Perc(), entity
							.getSla700Perc(), entity.getSla800Perc(), entity
							.getSla900Perc(), entity.getSla1000Perc(), entity
							.getSla1000plusPerc(), entity.getSlaPerc(), entity
							.getInterval(), entity.getRequestTime(), 0f));
		}

		return views;
	}

	private List<TransactionCountView> convertToViews(
			List<TransactionCountStatisticalDataDailyEntity> dailyEntities) {

		List<TransactionCountView> views = new ArrayList<>();

		for (TransactionCountStatisticalDataDailyEntity entity : dailyEntities) {
			views.add(new TransactionCountView(entity.getRequestDate(), entity
					.getUrl(), entity.getTransactionCount(), entity
					.getAverageLatency(), entity.getCountSlaUnder100(), entity
					.getCountSla100To200(), entity.getCountSla200To300(),
					entity.getCountSla300To400(), entity.getCountSla400To500(),
					entity.getCountSla500To600(), entity.getCountSla600To700(),
					entity.getCountSla700To800(), entity.getCountSla800To900(),
					entity.getCountSla900To1000(),
					entity.getCountSlaOver1000(), entity.getSla400Perc(),
					entity.getSla500Perc(), entity.getSla600Perc(), entity
							.getSla700Perc(), entity.getSla800Perc(), entity
							.getSla900Perc(), entity.getSla1000Perc(), entity
							.getSla1000plusPerc(), entity.getSlaPerc(), entity
							.getInterval(), "", 0f));
		}

		return views;
	}

	@Override
	public void saveHourlyView(TransactionCountView view) throws ParseException {
		transactionCountHourlyStatsRepository
				.save(new TransactionCountStatisticalDataHourlyEntity(view
						.getRequestDate(), view.getUrl(), view
						.getTransactionCount(), view.getAverageLatency(), view
						.getCountSlaUnder100(), view.getCountSla100To200(),
						view.getCountSla200To300(), view.getCountSla300To400(),
						view.getCountSla400To500(), view.getCountSla500To600(),
						view.getCountSla600To700(), view.getCountSla700To800(),
						view.getCountSla800To900(),
						view.getCountSla900To1000(),
						view.getCountSlaOver1000(), view.getInSlaPerc400(),
						view.getInSlaPerc500(), view.getInSlaPerc600(), view
								.getInSlaPerc700(), view.getInSlaPerc800(),
						view.getInSlaPerc900(), view.getInSlaPerc1000(), view
								.getInSlaPerc1000Plus(), view.getSlaTarget(),
						view.getInterval(), view.getRequestTime(), ReportingUtils.convertToDateTime(view.getRequestDate())));
	}

	@Override
	public void saveTpsView(TransactionCountView view, String channel) throws ParseException {
		tpsStatsRepository
				.save(new TransactionCountStatisticalDataTpsEntity(view
						.getRequestDate(), view.getUrl(), view
						.getTransactionCount(), view.getAverageLatency(), view
						.getCountSlaUnder100(), view.getCountSla100To200(),
						view.getCountSla200To300(), view.getCountSla300To400(),
						view.getCountSla400To500(), view.getCountSla500To600(),
						view.getCountSla600To700(), view.getCountSla700To800(),
						view.getCountSla800To900(),
						view.getCountSla900To1000(),
						view.getCountSlaOver1000(), view.getInSlaPerc400(),
						view.getInSlaPerc500(), view.getInSlaPerc600(), view
								.getInSlaPerc700(), view.getInSlaPerc800(),
						view.getInSlaPerc900(), view.getInSlaPerc1000(), view
								.getInSlaPerc1000Plus(), view.getSlaTarget(),
						view.getInterval(), view.getRequestTime(), view
								.getTps110(), ReportingUtils.convertToDateTime(view.getRequestDate())));
	}

	@Override
	public OfferDailyView getOfferDailyView() {
		return repository.getOffersDailyView(1);
	}

	@Override
	public List<OfferView> getOffers(OffersSelectionCriteria criteria) {
		return repository.getOffers(criteria);
	}

	@Override
	public void saveAvailabilityForm(AddAvailabilityForm addAvailabilityForm) {

		ChordiantAvailabilityEntity chordiantAvailabilityEntity = null;

		if (addAvailabilityForm.getId() == null
				|| addAvailabilityForm.getId() == 0) {
			chordiantAvailabilityEntity = new ChordiantAvailabilityEntity();
		} else {
			chordiantAvailabilityEntity = chordiantAvailabilityRepository
					.findOne(addAvailabilityForm.getId());
		}

		chordiantAvailabilityEntity.setStartDate(addAvailabilityForm
				.getStartDate());
		chordiantAvailabilityEntity
				.setEndDate(addAvailabilityForm.getEndDate());
		chordiantAvailabilityEntity.setStartTime(addAvailabilityForm
				.getStartTime());
		chordiantAvailabilityEntity
				.setEndTime(addAvailabilityForm.getEndTime());
		chordiantAvailabilityEntity.setTitle(addAvailabilityForm.getTitle());
		chordiantAvailabilityEntity.setDescription(addAvailabilityForm
				.getDescription());
		chordiantAvailabilityEntity.setTotalDowntime(addAvailabilityForm
				.getTotalDowntime());
		chordiantAvailabilityEntity.setChangeNumber(addAvailabilityForm
				.getChangeNumber());
		chordiantAvailabilityEntity.setIncidentNumber(addAvailabilityForm
				.getIncidentNumber());
		chordiantAvailabilityEntity.setNetworkPowerOutage(addAvailabilityForm
				.getNetworkPowerOutage());
		chordiantAvailabilityEntity
				.setPlanned(addAvailabilityForm.getPlanned());
		// chordiantAvailabilityEntity.setAttachmentEntities(attachmentEntities);

		chordiantAvailabilityRepository.save(chordiantAvailabilityEntity);
	}

	@Override
	public List<AddAvailabilityForm> getChordiantAvailabilityForPeriod(
			AvailabilityCriteriaView criteriaView) {
		List<AddAvailabilityForm> addAvailabilityForms = new ArrayList<>();

		List<ChordiantAvailabilityEntity> entities = chordiantAvailabilityRepository
				.findAvailabilityForPeriod(criteriaView.getStartDate(),
						criteriaView.getStartTime(), criteriaView.getEndDate(),
						criteriaView.getEndTime());

		for (ChordiantAvailabilityEntity entity : entities) {
			addAvailabilityForms.add(convertToAvailabilityForm(entity));
		}

		return addAvailabilityForms;
	}

	private AddAvailabilityForm convertToAvailabilityForm(
			ChordiantAvailabilityEntity entity) {
		AddAvailabilityForm availabilityForm = new AddAvailabilityForm();

		availabilityForm.setChangeNumber(entity.getChangeNumber());
		availabilityForm.setDescription(entity.getDescription());
		availabilityForm.setEndDate(entity.getEndDate());
		availabilityForm.setEndTime(entity.getEndTime());
		availabilityForm.setId(entity.getId());
		availabilityForm.setIncidentNumber(entity.getIncidentNumber());
		availabilityForm.setNetworkPowerOutage(entity.getNetworkPowerOutage());
		availabilityForm.setPlanned(entity.getPlanned());
		availabilityForm.setStartDate(entity.getStartDate());
		availabilityForm.setStartTime(entity.getStartTime());
		availabilityForm.setTitle(entity.getTitle());
		availabilityForm.setTotalDowntime(entity.getTotalDowntime());

		return availabilityForm;
	}

	@Override
	public AddAvailabilityForm findAvailabilityReportById(Long id) {
		return convertToAvailabilityForm(chordiantAvailabilityRepository
				.findOne(id));
	}

	@Override
	public void deleteAvailabilityReportById(Long id) {
		System.out.println("deleting for:" + id + "ww");
		chordiantAvailabilityRepository.delete(id);

		System.out.println("deleted:" + id + "ww");
	}

	@Override
	public List<J4UOffersView> getOffersForCriteria(
			GetOffersCriteria criteriaView) {

		List<J4UOffersView> list = new ArrayList<>();

		for (String port : criteriaView.getPorts().split(",")) {

			String url = urlPart1 + criteriaView.getServer() + ":" + port
					+ urlPart3;

			try {

				DecisionControllerWS_Service service = new DecisionControllerWS_Service(
						new URL(url));

				Request request = new Request();
				request.setMSISDN(criteriaView.getMsisdn());
				request.setSourceSystem(srcSystem);

				ProductArray array = service.getDecisionControllerWS()
						.getOffers(request);

				list.add(new J4UOffersView(port, criteriaView.getServer(),
						array.getProducts()));

			} catch (Exception ex) {
				list.add(new J4UOffersView(port, criteriaView.getServer(), null));
			}
			// System.out.println("Num of offers:" +
			// array.getProducts().size());

		}
		return list;

	}

	@Override
	public List<TransactionCountView> getStatsForPeriod(String startDate,
			String endDate) {
		List<TransactionCountView> views = new ArrayList<>();
		
		List<TransactionCountStatisticalDataDailyEntity> entities = dailyStatsRepository.findForPeriod(startDate, endDate);
		
		for (TransactionCountStatisticalDataDailyEntity entity : entities) {
			views.add(new TransactionCountView(entity.getRequestDate(), convertUrlToChannel(entity
					.getUrl()), entity.getTransactionCount(), entity
					.getAverageLatency(), entity.getCountSlaUnder100(), entity
					.getCountSla100To200(), entity.getCountSla200To300(),
					entity.getCountSla300To400(), entity.getCountSla400To500(),
					entity.getCountSla500To600(), entity.getCountSla600To700(),
					entity.getCountSla700To800(), entity.getCountSla800To900(),
					entity.getCountSla900To1000(),
					entity.getCountSlaOver1000(), entity.getSla400Perc(),
					entity.getSla500Perc(), entity.getSla600Perc(), entity
							.getSla700Perc(), entity.getSla800Perc(), entity
							.getSla900Perc(), entity.getSla1000Perc(), entity
							.getSla1000plusPerc(), entity.getSlaPerc(), entity
							.getInterval(), "", 0f));
		}
		
		
		return views;
	}

	private String convertUrlToChannel(String url) {
		String channel = "";
		
		if(StringUtils.contains(url, "DecisionController")) {
			channel = "J4U";
		} else if(StringUtils.contains(url, "external")) {
			channel = "CCR-external";
		} else if(StringUtils.contains(url, "assessment")) {
			channel = "CCR-assessment";
		}
		
		
		
		return channel;
	}

	@Override
	public List<MaterialisedView> getViewDetails(String viewname) {
		return repository.getViewDetails(viewname);
	}

	@Override
	public List<DatabaseStatusView> getCarDataAvailability(String database) {
		
		String[] carTables = getCarTables(carTablesAsString);
		
		List<DatabaseStatusView> list = new ArrayList<>();
		
		for(String tablename:carTables) {			
			
			DatabaseStatusView view = repository.queryCarTable(tablename, database);
			
			list.add(view);		
			
			
		}
		
		return list;
	}
	
	private String [] getCarTables(String tables) {
		String[] carTables = StringUtils.split(tables, ",");
		
		return carTables;
	}

	@Override
	public List<OfferView> getOffersForLastHour() {
		return repository.getOffersForLastHour();
	}

	@Override
	public void saveOffers(List<OfferView> offers, Date now) {
		for(OfferView offerView:offers) {
			j4uOfferStatsRepository.save(convertToOfferEntity(offerView, now));
		}
		
	}

	private J4UOfferStatsEntity convertToOfferEntity(OfferView offerView, Date now) {
		J4UOfferStatsEntity entity =  new J4UOfferStatsEntity();
		entity.setDateCreated(now);
		entity.setFulfilmentInd(offerView.getFulfilmentInd());
		entity.setNumRecords(offerView.getNumRecords());
		entity.setOfferExistsInd(offerView.getOfferExistsInd());
		entity.setResponse(offerView.getResponse());
		entity.setUsername(offerView.getUsername());
		return entity;
	}

	@Override
	public List<OfferView> getOffersLastFourHours() {
		return repository.getOffersLastFourHours();
	}

	@Override
	public int getRechargeAndGetCount(int i) {
		return repository.getRechargeAndGetCount(i);
	}

	@Override
	public List<MDMDailyView> d() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<KPIHourlyCountView> getKPIHourlyCount(String tableName){
		return repository.getKPIHourlyCount(tableName);
		
	}

	@Override
	public List<TPSHourlyCountView> getTPSHourlyCount(String channelName) {
		return repository.getTPSHourlyCount(channelName);
		
	}

	@Override
	public OfferCountView getOfferCount15min() {
		return repository.getOfferCount15min();
	}

	@Override
	public List<OfferDBCountView> getOfferDBCount() {
		return repository.getOfferDBCount();
	}

	@Override
	public List<MDMDailyView> getMDMDailyViews() {
		return repository.getMDMDailyViews(); 
		
	}

	@Override
	public List<TransactionCountView> getAggregatedStats(
			TransactionCountReportingCriteriaView criteriaView, String channel) {
		return transactionStatisticsRepository.getAggregatedStats(criteriaView, channel);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TPSCountView> getTPSStats(
			TransactionCountReportingCriteriaView criteriaView, String channel) {
		return transactionStatisticsRepository.getTPSStats(criteriaView, channel);
	}

	@Override
	public List<PortMonitorView> getPortStatus(String serverListAsString) {
		return portStatusRepository.getPortStatus(serverListAsString);
		
	};

}
