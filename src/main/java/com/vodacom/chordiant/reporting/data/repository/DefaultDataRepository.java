package com.vodacom.chordiant.reporting.data.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;








import com.vodacom.chordiant.reporting.mvc.view.DatabaseStatusView;
import com.vodacom.chordiant.reporting.mvc.view.DeploymentView;
import com.vodacom.chordiant.reporting.mvc.view.KPIHourlyCountView;
import com.vodacom.chordiant.reporting.mvc.view.MDMCountView;
import com.vodacom.chordiant.reporting.mvc.view.MDMDailyView;
import com.vodacom.chordiant.reporting.mvc.view.MDMStatsCriteriaView;
import com.vodacom.chordiant.reporting.mvc.view.MaterialisedView;
import com.vodacom.chordiant.reporting.mvc.view.OfferDBCountView;
import com.vodacom.chordiant.reporting.mvc.view.OfferDailyView;
import com.vodacom.chordiant.reporting.mvc.view.OfferView;
import com.vodacom.chordiant.reporting.mvc.view.OffersSelectionCriteria;
import com.vodacom.chordiant.reporting.mvc.view.TPSHourlyCountView;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountView;
import com.vodacom.chordiant.reporting.mvc.view.OfferCountView;

/**
 * Default Repository class that will make use of Spring Jdbc to make calls to
 * DB and retrieve data for the various views
 * 
 * @author Gaurav Soni
 * 
 */
@Component
public class DefaultDataRepository {

	/**
	 * Data source object that will be used to create the Spring Jdbc Template
	 */
	@Qualifier("reportingSchemaDataSource")
	@Autowired
	private DataSource reportingSchemaDataSource;

	/**
	 * Data source object that will be used to create the Spring Jdbc Template
	 */
	@Qualifier("standByDatabaseDataSource")
	@Autowired
	private DataSource standByDatabaseDataSource;
	
	/**
	 * Data source object that will be used to create the Spring Jdbc Template
	 */
	@Qualifier("interimDatabaseDataSource")
	@Autowired
	private DataSource interimDatabaseDataSource;

	/**
	 * Variables that will be used in the queries
	 * 
	 * @fixme: to used NameParameterJdbcTemplate instead
	 */

	String sql = "";

	String offerType = "";

	/**
	 * Retrieves the transaction count statistics for a certain period
	 * 
	 * @param startDate
	 * @param endDate
	 * @param interval
	 * @param startTime
	 * @param endTime
	 * @param offerType
	 * @return
	 */
	public List<TransactionCountView> getTransactionCountStatisticsForPeriod(
			String startDate, String endDate, String interval,
			String startTime, String endTime, String offerType) {

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				reportingSchemaDataSource);

		String sql = getSQLAndIntervalColumn(interval, offerType);

		RowMapper<TransactionCountView> rowMapper = getRowMapperForTransactionCount(interval);

		Map<String, String> namedParameters = new HashMap<>();

		namedParameters.put("startDate", startDate);
		namedParameters.put("endDate", endDate);
		namedParameters.put("offerType", "%" + offerType + "%");
		namedParameters.put("startTime", startTime);
		namedParameters.put("endTime", endTime);		


		List<TransactionCountView> list = template.query(sql, namedParameters,
				rowMapper);

		return list;

	}

	private RowMapper<TransactionCountView> getRowMapperForTransactionCount(
			final String interval) {

		return new RowMapper<TransactionCountView>() {

			@Override
			public TransactionCountView mapRow(ResultSet rs, int arg1)
					throws SQLException {
				
				TransactionCountView view = new TransactionCountView();
				
				if(StringUtils.equalsIgnoreCase("daily", interval)) {					
					view.setInterval(rs.getString("REQUEST_DATE"));					
					view.setRequestDate(rs.getString("REQUEST_DATE"));
				} else {
					view.setInterval(rs.getString("INTERVAL"));
				}
				
				view.setAverageLatency(rs.getFloat("average_latency"));
				view.setTransactionCount(rs.getLong("TRN_COUNT"));
				view.setUrl(rs.getString("URL"));
				
				view.setCountSlaUnder100(rs.getFloat("CNT_UNDER_100"));				
				view.setCountSla100To200(rs.getFloat("CNT_100_TO_200"));
				view.setCountSla200To300(rs.getFloat("CNT_200_TO_300"));
				view.setCountSla300To400(rs.getFloat("CNT_300_TO_400"));
				view.setCountSla400To500(rs.getFloat("CNT_400_TO_500"));
				view.setCountSla500To600(rs.getFloat("CNT_500_TO_600"));
				view.setCountSla600To700(rs.getFloat("CNT_600_TO_700"));				
				view.setCountSla700To800(rs.getFloat("CNT_700_TO_800"));
				view.setCountSla800To900(rs.getFloat("CNT_800_TO_900"));
				view.setCountSla900To1000(rs.getFloat("CNT_900_TO_1000"));
				view.setCountSlaOver1000(rs.getFloat("CNT_OVER_1000"));
				
				view.setInSlaPerc400(rs.getFloat("SLA_400_PERC"));
				view.setInSlaPerc500(rs.getFloat("SLA_500_PERC"));
				view.setInSlaPerc600(rs.getFloat("SLA_600_PERC"));
				view.setInSlaPerc700(rs.getFloat("SLA_700_PERC"));
				view.setInSlaPerc800(rs.getFloat("SLA_800_PERC"));
				view.setInSlaPerc900(rs.getFloat("SLA_900_PERC"));
				view.setInSlaPerc1000(rs.getFloat("SLA_1000_PERC"));
				view.setInSlaPerc1000Plus(rs.getFloat("SLA_1000_PLUS_PERC"));
				
				
				view.setSlaTarget(95f);
				

				if(StringUtils.equalsIgnoreCase("tps", interval)) {
					view.setTps110(rs.getFloat("TPS_110"));
				}
					
				return view;
					
				}

				
		};

				

	}
		
	



	private String getSQLAndIntervalColumn(String interval, String offerType) {

		String tableName = "";
		String sql = "";

		if (StringUtils.equalsIgnoreCase("external", offerType) || StringUtils.equalsIgnoreCase("assessment", offerType)) {
			tableName = "auto_master_wls_transac_counts";
		} else {
			tableName = "auto_master_wls_vm_counts";
		}

		if (StringUtils.equalsIgnoreCase("minute", interval)) {
			sql = getMinuteIntervalSQL(tableName);
		}

		if (StringUtils.equalsIgnoreCase("halfHour", interval)) {
			sql = getHalfHourSQL(tableName);
		}

		if (StringUtils.equalsIgnoreCase("hourly", interval)) {
			sql = getOneHourSQL(tableName);
		}

		if (StringUtils.equalsIgnoreCase("daily", interval)) {
			sql = getDailtySQL(tableName);
		}
				
		if (StringUtils.equalsIgnoreCase("monthly", interval)) {
			sql = getMonthlySQL();
		}
		
		if (StringUtils.equalsIgnoreCase("yearly", interval)) {
			sql = getYearlySQL();
		}
		
		if (StringUtils.equalsIgnoreCase("tps", interval)) {
			sql = getTpsSQL(tableName);
		}
		
		return sql;
  

	}

	private String getYearlySQL() {

		String sql = "select"  +
					 " round(sum(trn_count)) num_records" +
					 " , round(avg(sla_perc),2) IN_SLA_PERC" +
					 " , round(avg(sla2_perc),2) SLA_VALUE2" +
					 " , round(avg(sla3_perc),2) SLA_VALUE3" + 
					 " , round(avg(sla4_perc),2) SLA_VALUE4" + 
					 " ,to_char((to_date(view_date,'yyyy-mm-dd')), 'YYYY') as \"view_date\"" +
					 " from trncount_stats_daily tsd" +
					 " where " +
					 " channel like :offerType " +
					 " group by" +
					 " to_char((to_date(view_date,'yyyy-mm-dd')), 'YYYY')" +
					 " order by " +
					 " to_char((to_date(view_date,'yyyy-mm-dd')), 'YYYY') asc";

		
		return sql;
	}

	private String getMonthlySQL() {
				
		String sql = "select "
						+" round(sum(trn_count)) num_records "
						+" , url "
						+" , round(sum(TRN_COUNT),2) TRN_COUNT "
						+" , round(avg(AVG_LATENCY),2) average_latency "
						+" , round(avg(CNT_UNDER_100),2) CNT_UNDER_100 "
						+" , round(avg(CNT_100_TO_200),2) CNT_100_TO_200 "
						+" , round(avg(CNT_200_TO_300),2) CNT_200_TO_300 "
						+" , round(avg(CNT_300_TO_400),2) CNT_300_TO_400 "
						+" , round(avg(CNT_400_TO_500),2) CNT_400_TO_500 "
						+" , round(avg(CNT_500_TO_600),2) CNT_500_TO_600 "
						+" , round(avg(CNT_600_TO_700),2) CNT_600_TO_700 "
						+" , round(avg(CNT_700_TO_800),2) CNT_700_TO_800 "
						+" , round(avg(CNT_800_TO_900),2) CNT_800_TO_900 "
						+" , round(avg(CNT_900_TO_1000),2) CNT_900_TO_1000 "
						+" , round(avg(CNT_OVER_1000),2) CNT_OVER_1000 "
						+" , round(avg(SLA_400_PERC),2) SLA_400_PERC "
						+" , round(avg(SLA_500_PERC),2) SLA_500_PERC "
						+" , round(avg(SLA_600_PERC),2)SLA_600_PERC  "
						+" , round(avg(SLA_700_PERC),2) SLA_700_PERC "
						+" , round(avg(SLA_800_PERC),2) SLA_800_PERC "
						+" , round(avg(SLA_900_PERC),2) SLA_900_PERC "
						+" , round(avg(SLA_1000_PERC),2) SLA_1000_PERC "
						+" , round(avg(SLA_1000_PLUS_PERC),2) SLA_1000_PLUS_PERC "
						+" , round(avg(SLA_PERC),2) SLA_PERC "
						+" ,to_char((to_date(REQUEST_DATE,'yyyy-mm-dd')), 'YYYY-MM') as \"INTERVAL\" "
						+" from trncount_stats_daily "
						+" where  "
						+" REQUEST_DATE >= :startDate  "
						+" and  "
						+" REQUEST_DATE <= :endDate "
						+" and url like :offerType  "
						+" group by "
						+" to_char((to_date(REQUEST_DATE,'yyyy-mm-dd')), 'YYYY-MM') "
						+" , url "
						+" order by "
						+" to_char((to_date(REQUEST_DATE,'yyyy-mm-dd')), 'YYYY-MM') asc";

		String sql2 = "select"  +
					 " round(sum(trn_count)) num_records" +
					 " , round(avg(sla_perc),2) IN_SLA_PERC" +
					 " , round(avg(sla2_perc),2) SLA_VALUE2" +
					 " , round(avg(sla3_perc),2) SLA_VALUE3" + 
					 " , round(avg(sla4_perc),2) SLA_VALUE4" + 
					 " ,to_char((to_date(view_date,'yyyy-mm-dd')), 'YYYY-MM') as \"view_date\"" +
					 " from trncount_stats_daily tsd" +
					 " where " +
					 " view_date >= :startDate " +
					 " and " +
					 " view_date <= :endDate " +
					 " and url like :offerType " +
					 " group by" +
					 " to_char((to_date(view_date,'yyyy-mm-dd')), 'YYYY-MM')" +
					 " order by " +
					 " to_char((to_date(view_date,'yyyy-mm-dd')), 'YYYY-MM') asc";

		
		return sql;
	}

	private String getTpsSQL(String tableName) {
		
		String sql = "select  "
						+" to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/10,12))*10/1440), 'YYYYMMDD HH24:MI:SS') as \"INTERVAL\"  "
						+" ,url "
						+" ,count(*) TRN_COUNT  "
						+" ,round(avg(latency),2) average_latency " 
						+" , sum(CNT_UNDER_100) CNT_UNDER_100  "
						+" , sum(CNT_100_TO_200) CNT_100_TO_200  "
						+" , sum(CNT_200_TO_300) CNT_200_TO_300  "
						+" , sum(CNT_300_TO_400) CNT_300_TO_400  "
						+" , sum(CNT_400_TO_500) CNT_400_TO_500  "
						+" , sum(CNT_500_TO_600) CNT_500_TO_600  "
						+" , sum(CNT_600_TO_700) CNT_600_TO_700  "
						+" , sum(CNT_700_TO_800) CNT_700_TO_800  "
						+" , sum(CNT_800_TO_900) CNT_800_TO_900  "
						+" , sum(CNT_900_TO_1000) CNT_900_TO_1000 " 
						+" , sum(CNT_OVER_1000) CNT_OVER_1000  "
						+" , round((sum(CNT_UNDER_100) / count(*))*100,2) SLA_100_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200) / count(*))*100,2) SLA_200_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300) / count(*))*100,2) SLA_300_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400) / count(*))*100,2) SLA_400_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500) / count(*))*100,2) SLA_500_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600) / count(*))*100,2) SLA_600_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700) / count(*))*100,2) SLA_700_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700 + CNT_700_TO_800) / count(*))*100,2) SLA_800_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700 + CNT_700_TO_800 + CNT_800_TO_900) / count(*))*100,2) SLA_900_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700 + CNT_700_TO_800 + CNT_800_TO_900 + CNT_900_TO_1000) / count(*))*100,2) SLA_1000_PERC "
						+" , round((sum(CNT_OVER_1000) / count(*))*100,2) SLA_1000_PLUS_PERC "
						+" , round((count(*) / 600) * 1.1) TPS_110 " 
						+" from " 
						+" (  "
						+" select  "
						+" request_date  "
						+" , request_time " 
						+" , url  "
						+" ,latency  "
						+" ,case when latency < 0.1 then 1 else 0 end CNT_UNDER_100  "
						+" ,case when latency >= 0.1 and latency < 0.2 then 1 else 0 end CNT_100_TO_200   "
						+" ,case when latency >= 0.2 and latency < 0.3 then 1 else 0 end CNT_200_TO_300 "
						+" ,case when latency >= 0.3 and latency < 0.4 then 1 else 0 end CNT_300_TO_400 "
						+" ,case when latency >= 0.4 and latency < 0.5 then 1 else 0 end CNT_400_TO_500 "
						+" ,case when latency >= 0.5 and latency < 0.6 then 1 else 0 end CNT_500_TO_600 "
						+" ,case when latency >= 0.6 and latency < 0.7 then 1 else 0 end CNT_600_TO_700 "
						+" ,case when latency >= 0.7 and latency < 0.8 then 1 else 0 end CNT_700_TO_800 "
						+" ,case when latency >= 0.8 and latency < 0.9 then 1 else 0 end CNT_800_TO_900 "
						+" ,case when latency >= 0.9 and latency < 1.0 then 1 else 0 end CNT_900_TO_1000 "
						+" ,case when latency >= 1.0 then 1 else 0 end CNT_OVER_1000 "
						+" from  "
						+tableName
						+" where "
						+" request_date >= :startDate " 
						+" and  "
						+" request_date <= :endDate " 
						+" and url like :offerType  "
						+" and request_time >= :startTime  "
						+" and request_time <= :endTime " 
						+" )  "
						+" group by  "
						+" to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/10,12))*10/1440), 'YYYYMMDD HH24:MI:SS') " 
						+" ,url  "
						+" order by  "
						+" to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/10,12))*10/1440), 'YYYYMMDD HH24:MI:SS') " 
						+" ,url";
		
		String sql3 = "select "
	               +"to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/10,12))*10/1440), 'YYYYMMDD HH24:MI:SS') as \"10MIN_BUCKET\" " 
	            		   +",              url  "
	               +",              count(*) num_records "
	               +",              round(avg(latency),2) average_latency "
	               +",               sum(OUT_SLA_400) OUT_SLA_400 "
	               +",               sum(IN_SLA_400) IN_SLA_400 "
	               +",               sum(OUT_SLA_500) OUT_SLA_500 "
	               +",               sum(IN_SLA_500) IN_SLA_500 "
	               +",               sum(OUT_SLA_600) OUT_SLA_600 "
	               +",               sum(IN_SLA_600) IN_SLA_600 "
	               +",               sum(OUT_SLA_700) OUT_SLA_700 "
	               +",               sum(IN_SLA_700) IN_SLA_700 "
	               +",               sum(OUT_SLA_800) OUT_SLA_800 "
	               +",               sum(IN_SLA_800) IN_SLA_800 "
	               +",               sum(OUT_SLA_900) OUT_SLA_900 "
	               +",               sum(IN_SLA_900) IN_SLA_900 "
	               +",               sum(OUT_SLA_1000) OUT_SLA_1000 "
	               +",               sum(IN_SLA_1000) IN_SLA_1000 "
	               +",               round((sum(IN_SLA_400) / count(*))*100,2) IN_SLA_PERC_400 "
	               +",               round((sum(IN_SLA_500) / count(*))*100,2) IN_SLA_PERC_500 "
	               +",               round((sum(IN_SLA_600) / count(*))*100,2) IN_SLA_PERC_600 "
	               +",               round((sum(IN_SLA_700) / count(*))*100,2) IN_SLA_PERC_700 "
	               +",               round((sum(IN_SLA_800) / count(*))*100,2) IN_SLA_PERC_800 "
	               +",               round((sum(IN_SLA_900) / count(*))*100,2) IN_SLA_PERC_900 "
	               +",               round((sum(IN_SLA_1000) / count(*))*100,2) IN_SLA_PERC_1000 "
	               +",               round((count(*) / 600) * 1.1) TPS_110 "
	               +",               round(100 - 5) SLA_TARGET "
	               +"from "
	               +"( "
	               +"  select "
	               +"    request_date "
	               +"  , request_time "
	               +"  , url "
	               +"  ,latency "
	               +"  , case when latency >  0.4 then 1 else 0 end OUT_SLA_400 "
	               +"  , case when latency <= 0.4 then 1 else 0 end IN_SLA_400  "
	               +"  , case when latency >  0.5 then 1 else 0 end OUT_SLA_500 "
	               +"  , case when latency <= 0.5 then 1 else 0 end IN_SLA_500  "
	               +"  , case when latency >  0.6 then 1 else 0 end OUT_SLA_600 "
	               +"  , case when latency <= 0.6 then 1 else 0 end IN_SLA_600  "
	               +"  , case when latency >  0.7 then 1 else 0 end OUT_SLA_700 "
	               +"  , case when latency <= 0.7 then 1 else 0 end IN_SLA_700  "
	               +"  , case when latency >  0.8 then 1 else 0 end OUT_SLA_800 "
	               +"  , case when latency <= 0.8 then 1 else 0 end IN_SLA_800  "
	               +"  , case when latency >  0.9 then 1 else 0 end OUT_SLA_900 "
	               +"  , case when latency <= 0.9 then 1 else 0 end IN_SLA_900  "
	               +"  , case when latency >  1.0 then 1 else 0 end OUT_SLA_1000 "
	               +"  , case when latency <= 1.0 then 1 else 0 end IN_SLA_1000  "
	               +"from "
	                 + tableName 
	                 + " tc "
	               +"  where "
	               +"  request_date >= :startDate "
	               +"  and request_date <= :endDate "
					+ " and request_time >= :startTime "
					+ " and request_time <= :endTime "
					+ " and url like :offerType "
	            +"   ) "
	             +"  group by "
	              +"     to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/10,12))*10/1440), 'YYYYMMDD HH24:MI:SS') "
	               +",   url "
	               +"order by "
	               +"to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/10,12))*10/1440), 'YYYYMMDD HH24:MI:SS') "
	               +", url";
		
		
		
		
		
		String sql2 = "select "
	               + "to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/10,12))*10/1440), 'YYYYMMDD HH24:MI:SS') as \"10MIN_BUCKET\" " 
	               +", url  "
	               +", count(*) num_records "
	               +", round(avg(latency),2) average_latency "
	               +", sum(OUT_SLA_400) OUT_SLA_400 "
	               +", sum(IN_SLA_400) IN_SLA_400 "
	               +", sum(OUT_SLA_500) OUT_SLA_500 "
	               +", sum(IN_SLA_500) IN_SLA_500 "
	               +", sum(OUT_SLA_600) OUT_SLA_600 "
	               +", sum(IN_SLA_600) IN_SLA_600 "
	               +", sum(OUT_SLA_700) OUT_SLA_700 "
	               +", sum(IN_SLA_700) IN_SLA_700 "
	               +", sum(OUT_SLA_800) OUT_SLA_800 "
	               +", sum(IN_SLA_800) IN_SLA_800 "
	               +", sum(OUT_SLA_900) OUT_SLA_900 "
	               +", sum(IN_SLA_900) IN_SLA_900 "
	               +", sum(OUT_SLA_1000) OUT_SLA_1000 "
	               +", sum(IN_SLA_1000) IN_SLA_1000 "
	               +", round((sum(IN_SLA_400) / count(*))*100,2) IN_SLA_PERC_400 "
	               +", round((sum(IN_SLA_500) / count(*))*100,2) IN_SLA_PERC_500 "
	               +", round((sum(IN_SLA_600) / count(*))*100,2) IN_SLA_PERC_600 "
	               +", round((sum(IN_SLA_700) / count(*))*100,2) IN_SLA_PERC_700 "
	               +", round((sum(IN_SLA_800) / count(*))*100,2) IN_SLA_PERC_800 "
	               +", round((sum(IN_SLA_900) / count(*))*100,2) IN_SLA_PERC_900 "
	               +", round((sum(IN_SLA_1000) / count(*))*100,2) IN_SLA_PERC_1000 "
	               +", round((count(*) / 600) * 1.1) TPS_110 "
	               +", round(100 - 5) SLA_TARGET "
	               +"from "
	               +"( "
	                 +"select "
	                   +"request_date "
	                 +", request_time "
	                 +", url "
	                 +",latency "
	                 +", case when latency >  0.4 then 1 else 0 end OUT_SLA_400 "
	                 +", case when latency <= 0.4 then 1 else 0 end IN_SLA_400 "
	                 +", case when latency >  0.5 then 1 else 0 end OUT_SLA_500 "
	                 +", case when latency <= 0.5 then 1 else 0 end IN_SLA_500 "
	                 +", case when latency >  0.6 then 1 else 0 end OUT_SLA_600 "
	                 +", case when latency <= 0.6 then 1 else 0 end IN_SLA_600 "
	                 +", case when latency >  0.7 then 1 else 0 end OUT_SLA_700 "
	                 +", case when latency <= 0.7 then 1 else 0 end IN_SLA_700 "
	                 +", case when latency >  0.8 then 1 else 0 end OUT_SLA_800 "
	                 +", case when latency <= 0.8 then 1 else 0 end IN_SLA_800 "
	                 +", case when latency >  0.9 then 1 else 0 end OUT_SLA_900 "
	                 +", case when latency <= 0.9 then 1 else 0 end IN_SLA_900 "
	                 +", case when latency >  1.0 then 1 else 0 end OUT_SLA_1000 "
	                 +", case when latency <= 1.0 then 1 else 0 end IN_SLA_1000 "	                 
	                 +"from "
	                 + tableName 
	                 + " tc "
	                      //     --auto_master_wls_transac_counts tc
	                 +"where "
	                 +"request_date >= :startDate "
	               +"and request_date =< :endDate "
	               +"and url like :offerType "
	               +") "
	               +"group by "
	                 +"to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/10,12))*10/1440), 'YYYYMMDD HH24:MI:SS') "
	               +", url "
	               +"order by "
	                 +"to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/10,12))*10/1440), 'YYYYMMDD HH24:MI:SS') "
	               +",url";
				
				
		return sql;
	}

	private String getDailtySQL(String tableName) {
		
		String sql = "select   "
						+" request_date   "
						+" , url   "
						+" , count(*) trn_count  "
						+" ,round(avg(latency),2) average_latency  "
						+" , sum(CNT_UNDER_100) CNT_UNDER_100  "
						+" , sum(CNT_100_TO_200) CNT_100_TO_200  "
						+" , sum(CNT_200_TO_300) CNT_200_TO_300  "
						+" , sum(CNT_300_TO_400) CNT_300_TO_400  "
						+" , sum(CNT_400_TO_500) CNT_400_TO_500  "
						+" , sum(CNT_500_TO_600) CNT_500_TO_600  "
						+" , sum(CNT_600_TO_700) CNT_600_TO_700  "
						+" , sum(CNT_700_TO_800) CNT_700_TO_800  "
						+" , sum(CNT_800_TO_900) CNT_800_TO_900  "
						+" , sum(CNT_900_TO_1000) CNT_900_TO_1000 " 
						+" , sum(CNT_OVER_1000) CNT_OVER_1000  "
						+" , round((sum(CNT_UNDER_100) / count(*))*100,2) SLA_100_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200) / count(*))*100,2) SLA_200_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300) / count(*))*100,2) SLA_300_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400) / count(*))*100,2) SLA_400_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500) / count(*))*100,2) SLA_500_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600) / count(*))*100,2) SLA_600_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700) / count(*))*100,2) SLA_700_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700 + CNT_700_TO_800) / count(*))*100,2) SLA_800_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700 + CNT_700_TO_800 + CNT_800_TO_900) / count(*))*100,2) SLA_900_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700 + CNT_700_TO_800 + CNT_800_TO_900 + CNT_900_TO_1000) / count(*))*100,2) SLA_1000_PERC "
						+" , round((sum(CNT_OVER_1000) / count(*))*100,2) SLA_1000_PLUS_PERC "
						+" from  "
						+" (   "
						+" select   "
						+" request_date  "
						+" ,url  "
						+" ,latency  "
						+" ,case when latency < 0.1 then 1 else 0 end CNT_UNDER_100  "
						+" ,case when latency >= 0.1 and latency < 0.2 then 1 else 0 end CNT_100_TO_200   "
						+" ,case when latency >= 0.2 and latency < 0.3 then 1 else 0 end CNT_200_TO_300 "
						+" ,case when latency >= 0.3 and latency < 0.4 then 1 else 0 end CNT_300_TO_400 "
						+" ,case when latency >= 0.4 and latency < 0.5 then 1 else 0 end CNT_400_TO_500 "
						+" ,case when latency >= 0.5 and latency < 0.6 then 1 else 0 end CNT_500_TO_600 "
						+" ,case when latency >= 0.6 and latency < 0.7 then 1 else 0 end CNT_600_TO_700 "
						+" ,case when latency >= 0.7 and latency < 0.8 then 1 else 0 end CNT_700_TO_800 "
						+" ,case when latency >= 0.8 and latency < 0.9 then 1 else 0 end CNT_800_TO_900 "
						+" ,case when latency >= 0.9 and latency < 1.0 then 1 else 0 end CNT_900_TO_1000 "
						+" ,case when latency >= 1.0 then 1 else 0 end CNT_OVER_1000 "
						+" from     "
						+tableName
						+" where   "
						+" request_date >= :startDate  "
						+" and  "
						+" request_date <= :endDate  "
						+" and url like :offerType  "
						+" and request_time >= :startTime  "
						+" and request_time <= :endTime   "
						+" )   	 "
						+" group by request_date "  	
						+" , url    "	 
						+" order by "
						+" request_date";
		
		
		String sql3 = "select  "
				+" request_date  "
				+" , url  "
				+" , count(*) trn_count "
				+" , round((sum(VALUE_1) / count(*))*100,2) SLA_400  "
				+" , round((sum(VALUE_2) / count(*))*100,2) SLA_500   "
				+" , round((sum(VALUE_3) / count(*))*100,2) SLA_600  "
				+" , round((sum(VALUE_4) / count(*))*100,2) SLA_700  "
				+" , round((sum(VALUE_5) / count(*))*100,2) SLA_800  "
				+" , round((sum(VALUE_6) / count(*))*100,2) SLA_900  "
				+" , round((sum(VALUE_7) / count(*))*100,2) SLA_1000  "
				+" , round((sum(VALUE_8) / count(*))*100,2) SLA_1000_PLUS "
				+" from " 
				+" (  "
				+"  select  "
				+"  request_date " 
				+"  , url  "
				+" , case when latency <= '0.4' then 1 else 0 end VALUE_1  "
				+" , case when latency <= '0.5' then 1 else 0 end VALUE_2  "
				+" , case when latency <= '0.6' then 1 else 0 end VALUE_3  "
				+" , case when latency <= '0.7' then 1 else 0 end VALUE_4  "
				+" , case when latency <= '0.8' then 1 else 0 end VALUE_5  "
				+" , case when latency <= '0.9' then 1 else 0 end VALUE_6  "
				+" , case when latency <= '1.0' then 1 else 0 end VALUE_7  "
				+" , case when latency >= '1.0' then 1 else 0 end VALUE_8 "  
				+" from    "
				+ tableName
				+" where  "
				+" request_date >= :startDate  "
				+" and  "
				+" request_date <= :endDate  "
				+" and url like :offerType  "
				+" and request_time >= :startTime "
				+" and request_time <= :endTime  "  
				+" )   	 "
				+" group by request_date "   	
				+" , url   	 "
				+" order by " 
				+" request_date ";

		String sql2 = "select "
				+ "request_date "
				+ ", url "
				+ ", count(*) num_records"
				+ ", round((sum(VALUE_1) / count(*))*100,2) IN_SLA_PERC"
				+ ", round((sum(VALUE_2) / count(*))*100,2) SLA_VALUE2 "
				+ ", round((sum(VALUE_3) / count(*))*100,2) SLA_VALUE3 "
				+ ", round((sum(VALUE_4) / count(*))*100,2) SLA_VALUE4 "
				+ "from "
				+ "( "
				+ " select "
				+ " request_date "
				+ " , url "
				+ "  , case when latency <= :latencySla then 1 else 0 end VALUE_1 "
				+ "  , case when latency <= :latencySla2 then 1 else 0 end VALUE_2 "
				+ "  , case when latency <= :latencySla3 then 1 else 0 end VALUE_3 "
				+ "  , case when latency <= :latencySla4 then 1 else 0 end VALUE_4 "
				+ "from " + tableName + " tc " + "where "
				+ "request_date >= :startDate and "
				+ "request_date <= :endDate " + "and url like :offerType "
				+ "and request_time >= :startTime "
				+ "and request_time <= :endTime " + "	) " + "	group by "
				+ "	  request_date " + "	, url " + "	order by "
				+ "	  request_date ";

		String sql1 = "select request_date , url, count(*) num_records ,round(avg(latency),2) average_latency , sum(OUT_SLA) OUT_SLA , sum(IN_SLA) IN_SLA "
				+ ", round((sum(OUT_SLA) / count(*))*100,2) OUT_SLA_PERC , round((sum(IN_SLA) / count(*))*100,2) IN_SLA_PERC "
				+ "from (select request_date , url,latency, case when latency >  :latencySla then 1 else 0 end OUT_SLA "
				+ ", case when latency <= :latencySla then 1 else 0 end IN_SLA  "
				+ "from "
				+ tableName
				+ " tc "
				+ "where "
				+ "request_date >= :startDate and "
				+ "request_date <= :endDate "
				+ "and url like :offerType "
				+ "and request_time >= :startTime "
				+ "and request_time <= :endTime "
				+ ") "
				+ "group by "
				+ "request_date " + ", url " + "order by " + "request_date";
		return sql;

	}

	private String getOneHourSQL(String tableName) {
		
		String sql = "select   "
						+" to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/60,12))*60/1440), 'YYYYMMDD HH24:MI:SS') as \"INTERVAL\"  "  
						+" , url  "
						+" , count(*) trn_count  "
						+" , round(avg(latency),2) average_latency  "
						+" , sum(CNT_UNDER_100) CNT_UNDER_100  "
						+" , sum(CNT_100_TO_200) CNT_100_TO_200  "
						+" , sum(CNT_200_TO_300) CNT_200_TO_300  "
						+" , sum(CNT_300_TO_400) CNT_300_TO_400  "
						+" , sum(CNT_400_TO_500) CNT_400_TO_500  "
						+" , sum(CNT_500_TO_600) CNT_500_TO_600  "
						+" , sum(CNT_600_TO_700) CNT_600_TO_700  "
						+" , sum(CNT_700_TO_800) CNT_700_TO_800  "
						+" , sum(CNT_800_TO_900) CNT_800_TO_900  "
						+" , sum(CNT_900_TO_1000) CNT_900_TO_1000 " 
						+" , sum(CNT_OVER_1000) CNT_OVER_1000  "
						+" , round((sum(CNT_UNDER_100) / count(*))*100,2) SLA_100_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200) / count(*))*100,2) SLA_200_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300) / count(*))*100,2) SLA_300_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400) / count(*))*100,2) SLA_400_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500) / count(*))*100,2) SLA_500_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600) / count(*))*100,2) SLA_600_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700) / count(*))*100,2) SLA_700_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700 + CNT_700_TO_800) / count(*))*100,2) SLA_800_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700 + CNT_700_TO_800 + CNT_800_TO_900) / count(*))*100,2) SLA_900_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700 + CNT_700_TO_800 + CNT_800_TO_900 + CNT_900_TO_1000) / count(*))*100,2) SLA_1000_PERC "
						+" , round((sum(CNT_OVER_1000) / count(*))*100,2) SLA_1000_PLUS_PERC "
						+"  from   "
						+"  (  "
						+"  select  " 
						+"  request_date  " 
						+"  , request_time   "
						+"  , url   "
						+"  , latency   "
						+" ,case when latency < 0.1 then 1 else 0 end CNT_UNDER_100  "
						+" ,case when latency >= 0.1 and latency < 0.2 then 1 else 0 end CNT_100_TO_200   "
						+" ,case when latency >= 0.2 and latency < 0.3 then 1 else 0 end CNT_200_TO_300 "
						+" ,case when latency >= 0.3 and latency < 0.4 then 1 else 0 end CNT_300_TO_400 "
						+" ,case when latency >= 0.4 and latency < 0.5 then 1 else 0 end CNT_400_TO_500 "
						+" ,case when latency >= 0.5 and latency < 0.6 then 1 else 0 end CNT_500_TO_600 "
						+" ,case when latency >= 0.6 and latency < 0.7 then 1 else 0 end CNT_600_TO_700 "
						+" ,case when latency >= 0.7 and latency < 0.8 then 1 else 0 end CNT_700_TO_800 "
						+" ,case when latency >= 0.8 and latency < 0.9 then 1 else 0 end CNT_800_TO_900 "
						+" ,case when latency >= 0.9 and latency < 1.0 then 1 else 0 end CNT_900_TO_1000 "
						+" ,case when latency >= 1.0 then 1 else 0 end CNT_OVER_1000 "
						+" from     "
						+tableName
						+" where   "
						+" request_date >= :startDate  "
						+" and  "
						+" request_date <= :endDate  "
						+" and url like :offerType  "
						+" and request_time >= :startTime  "
						+" and request_time <= :endTime  " 
						+"  )  "
						+"  group by   "
						+"  to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/60,12))*60/1440), 'YYYYMMDD HH24:MI:SS') "  
						+"  , url  "
						+"  order by  "
						+"  to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/60,12))*60/1440), 'YYYYMMDD HH24:MI:SS') " 
						+"  , url";
		
		String sql3 = "select  "
				+" to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/60,12))*60/1440), 'YYYYMMDD HH24:MI:SS') as \"VIEW_DATE\"  "
						+" , url "
						+" , count(*) trn_count "
				+" , round(avg(latency),2) average_latency "
				+" , round((sum(VALUE_1) / count(*))*100,2) SLA_400  "
				+" , round((sum(VALUE_2) / count(*))*100,2) SLA_500  " 
				+" , round((sum(VALUE_3) / count(*))*100,2) SLA_600  "
				+" , round((sum(VALUE_4) / count(*))*100,2) SLA_700  "
				+" , round((sum(VALUE_5) / count(*))*100,2) SLA_800  "
				+" , round((sum(VALUE_6) / count(*))*100,2) SLA_900  "
				+" , round((sum(VALUE_7) / count(*))*100,2) SLA_1000  "
				+" , round((sum(VALUE_8) / count(*))*100,2) SLA_1000_PLUS  "
				+" from  "
				+" ( "
				+" select  "
				+" request_date  "
				+" , request_time  "
				+" , url  "
				+" , latency  "
				+" , case when latency <= '0.4' then 1 else 0 end VALUE_1  "
				+" , case when latency <= '0.5' then 1 else 0 end VALUE_2  "
				+" , case when latency <= '0.6' then 1 else 0 end VALUE_3  "
				+" , case when latency <= '0.7' then 1 else 0 end VALUE_4  "
				+" , case when latency <= '0.8' then 1 else 0 end VALUE_5  "
				+" , case when latency <= '0.9' then 1 else 0 end VALUE_6  "
				+" , case when latency <= '1.0' then 1 else 0 end VALUE_7  "
				+" , case when latency >= '1.0' then 1 else 0 end VALUE_8   "
				+" from  "
				+ tableName
				+" where  "
				+" request_date >= :startDate  "
				+" and  "
				+" request_date <= :endDate  "
				+" and url like :offerType  "
				+" and request_time >= :startTime "
				+" and request_time <= :endTime  "
				+" ) "
				+" group by  "
				+" to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/60,12))*60/1440), 'YYYYMMDD HH24:MI:SS') " 
				+" , url  "
				+" order by  "
				+" to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/60,12))*60/1440), 'YYYYMMDD HH24:MI:SS') " 
				+" , url ";

		String sql2 = "select "
				+ "to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/60,12))*60/1440), 'YYYYMMDD HH24:MI:SS') as \"60MIN_BUCKET\""
				+ ", url"
				+ ", count(*) num_records"
				+ ", round(avg(latency),2) average_latency"
				+ ", sum(OUT_SLA) OUT_SLA"
				+ ", sum(IN_SLA) IN_SLA "
				+ ",  round((sum(OUT_SLA) / count(*))*100,2) OUT_SLA_PERC"
				+ ",  round((sum(IN_SLA) / count(*))*100,2) IN_SLA_PERC "
				+ "from "
				+ "("
				+ "select "
				+ "request_date "
				+ ", request_time "
				+ ", url "
				+ ",latency "
				+ ", case when latency >  :latencySla then 1 else 0 end OUT_SLA "
				+ ", case when latency <= :latencySla then 1 else 0 end IN_SLA  "
				+ "from "
				+ tableName
				+ " where "
				+ "request_date >= :startDate "
				+ "and "
				+ "request_date <= :endDate "
				+ "and url like :offerType "
				+ "and request_time >= :startTime "
				+ "and request_time <= :endTime "
				+ ")"
				+ "group by "
				+ "to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/60,12))*60/1440), 'YYYYMMDD HH24:MI:SS') "
				+ ", url "
				+ "order by "
				+ "to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/60,12))*60/1440), 'YYYYMMDD HH24:MI:SS') "
				+ ", url";
		return sql;
	}

	private String getHalfHourSQL(String tableName) {
		
		String sql = " select   "
						+" to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/30,12))*30/1440), 'YYYYMMDD HH24:MI:SS') as \"INTERVAL\"  "  
						+" , url   "
						+" , count(*) trn_count  " 
						+" , round(avg(latency),2) average_latency   "
						+" , sum(CNT_UNDER_100) CNT_UNDER_100  "
						+" , sum(CNT_100_TO_200) CNT_100_TO_200  "
						+" , sum(CNT_200_TO_300) CNT_200_TO_300  "
						+" , sum(CNT_300_TO_400) CNT_300_TO_400  "
						+" , sum(CNT_400_TO_500) CNT_400_TO_500  "
						+" , sum(CNT_500_TO_600) CNT_500_TO_600  "
						+" , sum(CNT_600_TO_700) CNT_600_TO_700  "
						+" , sum(CNT_700_TO_800) CNT_700_TO_800  "
						+" , sum(CNT_800_TO_900) CNT_800_TO_900  "
						+" , sum(CNT_900_TO_1000) CNT_900_TO_1000 " 
						+" , sum(CNT_OVER_1000) CNT_OVER_1000  "
						+" , round((sum(CNT_UNDER_100) / count(*))*100,2) SLA_100_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200) / count(*))*100,2) SLA_200_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300) / count(*))*100,2) SLA_300_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400) / count(*))*100,2) SLA_400_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500) / count(*))*100,2) SLA_500_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600) / count(*))*100,2) SLA_600_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700) / count(*))*100,2) SLA_700_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700 + CNT_700_TO_800) / count(*))*100,2) SLA_800_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700 + CNT_700_TO_800 + CNT_800_TO_900) / count(*))*100,2) SLA_900_PERC "
						+" , round((sum(CNT_UNDER_100 + CNT_100_TO_200 + CNT_200_TO_300+ CNT_300_TO_400 + CNT_400_TO_500 + CNT_500_TO_600 + CNT_600_TO_700 + CNT_700_TO_800 + CNT_800_TO_900 + CNT_900_TO_1000) / count(*))*100,2) SLA_1000_PERC "
						+" , round((sum(CNT_OVER_1000) / count(*))*100,2) SLA_1000_PLUS_PERC "
						+"  from   "
						+"  (  "
						+"  select   "
						+"  request_date   "
						+"  , request_time   "
						+"  , url   "
						+"  ,latency   "
						+" ,case when latency < 0.1 then 1 else 0 end CNT_UNDER_100  "
						+" ,case when latency >= 0.1 and latency < 0.2 then 1 else 0 end CNT_100_TO_200   "
						+" ,case when latency >= 0.2 and latency < 0.3 then 1 else 0 end CNT_200_TO_300 "
						+" ,case when latency >= 0.3 and latency < 0.4 then 1 else 0 end CNT_300_TO_400 "
						+" ,case when latency >= 0.4 and latency < 0.5 then 1 else 0 end CNT_400_TO_500 "
						+" ,case when latency >= 0.5 and latency < 0.6 then 1 else 0 end CNT_500_TO_600 "
						+" ,case when latency >= 0.6 and latency < 0.7 then 1 else 0 end CNT_600_TO_700 "
						+" ,case when latency >= 0.7 and latency < 0.8 then 1 else 0 end CNT_700_TO_800 "
						+" ,case when latency >= 0.8 and latency < 0.9 then 1 else 0 end CNT_800_TO_900 "
						+" ,case when latency >= 0.9 and latency < 1.0 then 1 else 0 end CNT_900_TO_1000 "
						+" ,case when latency >= 1.0 then 1 else 0 end CNT_OVER_1000 "
						+" from     "
						+tableName
						+" where  "
						+" request_date >= :startDate " 
						+" and "
						+" request_date <= :endDate "
						+" and url like :offerType "
						+" and request_time >= :startTime "
						+" and request_time <= :endTime   "
						+"  )  "
						+"  group by  "
						+"  to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/30,12))*30/1440), 'YYYYMMDD HH24:MI:SS') " 
						+"  , url  "
						+"  order by  "
						+"  to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/30,12))*30/1440), 'YYYYMMDD HH24:MI:SS') " 
						+" , url";
		
		
		String sql3 = "select  "
				+" to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/30,12))*30/1440), 'YYYYMMDD HH24:MI:SS') as \"VIEW_DATE\"  " 
				+" , url  "
				+" , count(*) trn_count  "
				+" , round(avg(latency),2) average_latency  "
				+" , round((sum(VALUE_1) / count(*))*100,2) SLA_400  "
				+" , round((sum(VALUE_2) / count(*))*100,2) SLA_500   "
				+" , round((sum(VALUE_3) / count(*))*100,2) SLA_600  "
				+" , round((sum(VALUE_4) / count(*))*100,2) SLA_700  "
				+" , round((sum(VALUE_5) / count(*))*100,2) SLA_800  "
				+" , round((sum(VALUE_6) / count(*))*100,2) SLA_900  "
				+" , round((sum(VALUE_7) / count(*))*100,2) SLA_1000  "
				+" , round((sum(VALUE_8) / count(*))*100,2) SLA_1000_PLUS  "
				+" from  "
				+" ( "
				+" select  "
				+" request_date  "
				+" , request_time  "
				+" , url  "
				+" ,latency  "
				+" , case when latency <= '0.4' then 1 else 0 end VALUE_1  "
				+" , case when latency <= '0.5' then 1 else 0 end VALUE_2  "
				+" , case when latency <= '0.6' then 1 else 0 end VALUE_3  "
				+" , case when latency <= '0.7' then 1 else 0 end VALUE_4 " 
				+" , case when latency <= '0.8' then 1 else 0 end VALUE_5  "
				+" , case when latency <= '0.9' then 1 else 0 end VALUE_6  "
				+" , case when latency <= '1.0' then 1 else 0 end VALUE_7  "
				+" , case when latency >= '1.0' then 1 else 0 end VALUE_8  " 
				+" from  "
				+ tableName
				+" where  "
				+" request_date >= :startDate  "
				+" and  "
				+" request_date <= :endDate  "
				+" and url like :offerType  "
				+" and request_time >= :startTime  "
				+" and request_time <= :endTime  "
				+" )  "
				+" group by  "
				+" to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/30,12))*30/1440), 'YYYYMMDD HH24:MI:SS') " 
				+" , url  "
				+" order by  "
				+" to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/30,12))*30/1440), 'YYYYMMDD HH24:MI:SS') " 
				+" 	, url";
		

		String sql2 = "select "
				+ "to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/30,12))*30/1440), 'YYYYMMDD HH24:MI:SS') as \"30MIN_BUCKET\" "
				+ ", url "
				+ ", count(*) num_records "
				+ ", round(avg(latency),2) average_latency "
				+ ", sum(OUT_SLA) OUT_SLA "
				+ ", sum(IN_SLA) IN_SLA "
				+ ", round((sum(OUT_SLA) / count(*))*100,2) OUT_SLA_PERC "
				+ ", round((sum(IN_SLA) / count(*))*100,2) IN_SLA_PERC "
				+ "from "
				+ "("
				+ "select "
				+ "request_date "
				+ ", request_time "
				+ ", url "
				+ ",latency "
				+ ", case when latency >  :latencySla then 1 else 0 end OUT_SLA "
				+ ", case when latency <= :latencySla then 1 else 0 end IN_SLA  "
				+ "from "
				+ tableName
				+ " where "
				+ "request_date >= :startDate "
				+ "and "
				+ "request_date <= :endDate "
				+ "and url like :offerType "
				+ "and request_time >= :startTime "
				+ "and request_time <= :endTime "
				+ ") "
				+ "group by "
				+ "to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/30,12))*30/1440), 'YYYYMMDD HH24:MI:SS') "
				+ ", url "
				+ "order by "
				+ "to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/30,12))*30/1440), 'YYYYMMDD HH24:MI:SS') "
				+ ", url";
		
		
		
		return sql;
	}

	private String getMinuteIntervalSQL(String tableName) {
		String sql = "select "
				+ " request_date "
				+ ",to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/1,12))*1/1440), 'YYYYMMDD HH24:MI:SS') as \"1MIN_BUCKET\" "
				// +"--,           server_name"
				+ ",url "
				+ ", count(*) num_records "
				+ ", round(avg(latency),2) average_latency "
				+ ", sum(OUT_SLA) OUT_SLA "
				+ ", sum(IN_SLA) IN_SLA "
				+ ", round((sum(OUT_SLA) / count(*))*100,2) OUT_SLA_PERC "
				+ ", round((sum(IN_SLA) / count(*))*100,2) IN_SLA_PERC "
				+ "from "
				+ "("
				+ " select "
				+ "request_date "
				+ ",request_time "
				+ ", url "
				+ ",latency "
				+ ", case when latency >  :latencySla then 1 else 0 end OUT_SLA "
				+ ", case when latency <= :latencySla then 1 else 0 end IN_SLA  "
				+ "from "
				+ tableName
				+ " where "
				+ "request_date >= :startDate "
				+ "and "
				+ "request_date <= :endDate "
				+ "and url like :offerType "
				+ "and request_time >= :startTime "
				+ "and request_time <= :endTime "
				+ ")"
				+ " group by "
				+ "request_date "
				+ ",to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/1,12))*1/1440), 'YYYYMMDD HH24:MI:SS') "
				// +"--, server_name"
				+ ", url "
				+ "order by "
				+ "request_date "
				+ ",to_char((to_date('19700101','yyyymmdd') + floor(round((to_date(request_date || ' ' || request_time, 'yyyy-mm-dd hh24:mi:ss') - to_date('19700101','yyyymmdd'))*1440/1,12))*1/1440), 'YYYYMMDD HH24:MI:SS') "
				// +"--,           server_name"
				+ ",url";
		return sql;
	}

	public List<MDMCountView> getMDMStatisticsForPeriod(MDMStatsCriteriaView criteriaView) {

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				interimDatabaseDataSource);

		sql = getSQLForMDM(criteriaView.getInterval(), criteriaView.getProcessName());

		RowMapper<MDMCountView> rowMapper = getRowMapperForMDM();

		Map<String, String> namedParameters = new HashMap<>();

		namedParameters.put("startDate", criteriaView.getStartDate());
		namedParameters.put("endDate", criteriaView.getEndDate());
		namedParameters.put("processName", "%" + criteriaView.getProcessName() + "%");

		List<MDMCountView> list = template.query(sql, namedParameters,
				rowMapper);

		return list;

	}

	private RowMapper<MDMCountView> getRowMapperForMDM() {
		return new RowMapper<MDMCountView>() {
			@Override
			public MDMCountView mapRow(ResultSet rs, int arg1)
					throws SQLException {
				MDMCountView view = new MDMCountView();
				// view.setProcessName(rs.getString("process_name"));
				view.setProcessedDate(rs.getString("processed_date"));
				view.setLoadedRecords(rs.getLong("loaded_records"));
				view.setRunTime(rs.getLong("run_time"));
				return view;
			}
		};
	}

	private String getSQLForMDM(String interval, String processName) {
		
		if(StringUtils.equalsIgnoreCase(interval, "daily")) {
			return getMdmDailySQL(processName);
		} else {
			return getMdmMonthlySQL(processName);
		}
			
	}

	private String getMdmMonthlySQL(String processName) {
		String sql = 
				"select" +
				" TO_CHAR(processed_date, 'YYYY-MM') as \"processed_date\" " +
				" , sum(loaded_records) loaded_records, " +
				" max(timestamp_diff(start_time, end_time,'SS')) run_time " +
				" from car.cdt_history_table " +
				" where " +
				" to_char(processed_date,'yyyy-mm-dd') >= :startDate " +
				" and " +
				" to_char(processed_date,'yyyy-mm-dd') <= :endDate " +
				" and process_name like :processName " +
				" and loaded_records > 0  " +
				" group by  " +
				" TO_CHAR(processed_date, 'YYYY-MM') " +
				" order by  " +
				" TO_CHAR(processed_date, 'YYYY-MM')";
		return sql;
	}

	private String getMdmDailySQL(String processName) {
		String sql = 
				"select TO_CHAR(processed_date, 'yyyy-mm-dd') processed_date, sum(loaded_records) loaded_records, max(timestamp_diff(start_time, end_time,'SS')) run_time "
				+ "from cdt_history_table "
				+ "where TO_CHAR(processed_date, 'yyyy-mm-dd') >= :startDate  "
				+ "and TO_CHAR(processed_date, 'yyyy-mm-dd') <= :endDate  "
				+ "and process_name like :processName "
				+ "and loaded_records > 0 "
				+ "group by TO_CHAR(processed_date, 'yyyy-mm-dd') , process_name "
				+ "order by processed_date";
		
		return sql;
	}

	/**
	 * Returns current offer logic available and their status.
	 * 
	 * @return
	 */
	public List<DeploymentView> getOfferLogicDeployments() {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				standByDatabaseDataSource);

		sql = getSQLForOfferLogicDeployments();

		RowMapper<DeploymentView> rowMapper = getRowMapperForOfferLogicDeployment();

		Map<String, String> namedParameters = new HashMap<>();

		List<DeploymentView> list = template.query(sql, namedParameters,
				rowMapper);

		return list;
	}

	/**
	 * Returns a DeploymentView row mapper
	 * 
	 * @return
	 */
	private RowMapper<DeploymentView> getRowMapperForOfferLogicDeployment() {
		return new RowMapper<DeploymentView>() {
			@Override
			public DeploymentView mapRow(ResultSet rs, int arg1)
					throws SQLException {
				DeploymentView view = new DeploymentView();
				view.setContainerPackageId(rs.getInt("CONTAINER_PACKAGE_ID"));
				view.setPackageFamily(rs.getInt("PACKAGE_FAMILY"));
				view.setPackageId(rs.getInt("PACKAGE_ID"));
				view.setProject(rs.getString("PROJECT"));
				view.setVersion(rs.getInt("VERSION"));
				view.setStatus(rs.getString("STATUS"));
				return view;
			}
		};
	}

	/**
	 * Returns the query string to retrieve number of offer logic deployment
	 * available
	 * 
	 * @return
	 */
	private String getSQLForOfferLogicDeployments() {
		String sql = "SELECT PACKAGE_ID, PACKAGE_FAMILY, PROJECT, VERSION, CONTAINER_PACKAGE_ID, STATUS "
				+ "FROM CDM_ADS.ADS_PACKAGE ads";
		// +"where STATUS <> 'deployed'";
		return sql;
	}

	/**
	 * Retrieves the count of offers presented for the CC&R channel
	 * 
	 * @return
	 */
	public OfferDailyView getCCROfferCount() {
		return getOffers("CCR");
	}

	/**
	 * Retrieves the count of offers presented for the PPFE channel
	 * 
	 * @return
	 */
	public OfferDailyView getPPFECount() {
		return getOffers("PPFE");
	}

	/**
	 * Retrieves the count of offers presented for the USSD channel
	 * 
	 * @return
	 */
	public OfferDailyView getUSSDOfferCount() {
		return getOffers("USSD");
	}
	
	

	/**
	 * Retrieves the offer count over the last 15 min for specified channel
	 * 
	 * @param string
	 * @return
	 */
	private OfferDailyView getOffers(String channel) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				standByDatabaseDataSource);

		sql = getSQLForOffersPresented(channel);

		RowMapper<OfferDailyView> rowMapper = getRowMapperForOffersPresented(channel);

		Map<String, String> namedParameters = new HashMap<>();

		List<OfferDailyView> list = template.query(sql, namedParameters,
				rowMapper);

		return list != null && !list.isEmpty() ? list.get(0)
				: new OfferDailyView();
	}

	/**
	 * Returns the SQL used to retrieve offers presented in the last 15 min per
	 * channel
	 * 
	 * @param channel
	 * @return
	 */
	private String getSQLForOffersPresented(String channel) {
		String sql = "select count(*) OFFERS_COUNT " + "from "
				+ "cdm_is.IS_PROPOSITION_HISTORY isph "
				+ ",   cdm_is.IS_PROPOSITION isp "
				+ "where " + "isph.proposition_id = isp.proposition_id "
				+ "and isph.response_id = isr.response_id "
				+ "and isph.user_id = isu.user_id ";

		if (StringUtils.equalsIgnoreCase("USSD", channel)) {
			sql = sql + "and username = 'USSD' ";

		} else if (StringUtils.equalsIgnoreCase("PPFE", channel)) {
			sql = sql + "and username = 'PPFE' ";
		} else {
			sql = sql + "and username <> 'USSD' " + "and username <> 'PPFE' ";

		}

		sql = sql + "and isph.offered_on >= (sysdate - 1/1440) "
				+ "order by offered_on desc ";

		return sql;

	}

	/**
	 * Returns a OfferDailyView row mapper
	 * 
	 * @return
	 */
	private RowMapper<OfferDailyView> getRowMapperForOffersPresented(
			final String channel) {
		return new RowMapper<OfferDailyView>() {
			@Override
			public OfferDailyView mapRow(ResultSet rs, int arg1)
					throws SQLException {
				OfferDailyView view = new OfferDailyView();

				return view;
			}
		};
	}

	/**
	 * Retrieves number of records loaded for each car over the last 4 days
	 * including today
	 * 
	 * @return
	 */
	public List<MDMDailyView> getMDMDailyViews() {

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				interimDatabaseDataSource);

		sql = getSQLForMDMLastFourDays();

		RowMapper<MDMDailyView> rowMapper = getRowMapperForMDMDailyView();

		Map<String, String> namedParameters = new HashMap<>();

		List<MDMDailyView> list = template.query(sql, namedParameters,
				rowMapper);

		return list;
	}

	/**
	 * Returns the SQL used to retrieve MDM car processes that have ran over the
	 * last 4 days
	 * 
	 * 
	 * @param
	 * @return
	 */
	private String getSQLForMDMLastFourDays() {
		String sql = "select PROCESS_NAME , loaded_records_today, loaded_records_one_day_ago , loaded_records_two_days_ago, loaded_records_three_days_ago, loaded_records_four_days_ago "
				+ "from ( "
				+ "SELECT DECODE ( curday.PROCESS_NAME, NULL, DECODE (Daymin1.PROCESS_NAME, NULL,DECODE (Daymin2.PROCESS_NAME, NULL,DECODE (Daymin3.PROCESS_NAME, NULL,DECODE (Daymin4.PROCESS_NAME, NULL,'N/A',Daymin4.PROCESS_NAME ),Daymin3.PROCESS_NAME ),Daymin2.PROCESS_NAME ),Daymin1.PROCESS_NAME ), curday.PROCESS_NAME)PROCESS_NAME, "
				+ "NVL(curday.loaded_records, -1) loaded_records_today, "
				+ "NVL(Daymin1.loaded_records, -1) loaded_records_one_day_ago , "
				+ "NVL(Daymin2.loaded_records, -1) loaded_records_two_days_ago, "
				+ "NVL(Daymin3.loaded_records, -1) loaded_records_three_days_ago, "
				+ "NVL(Daymin4.loaded_records, -1) loaded_records_four_days_ago "
				+ "FROM "
				+ "(select  PROCESS_NAME, LOADED_RECORDS, END_TIME  from (select PROCESS_NAME, max(LOADED_RECORDS) LOADED_RECORDS,max(END_TIME) END_TIME  from "
				+ "CAR.cdt_history_table "
				+ "where INPUT_FILE like '%'||TO_CHAR(sysdate-1,'yyyymmdd')||'%' "
				+ "group by PROCESS_NAME) "
				+ ") curday "
				+ "FULL OUTER JOIN "
				+ "(select  PROCESS_NAME, LOADED_RECORDS, END_TIME  from (select PROCESS_NAME, max(LOADED_RECORDS) LOADED_RECORDS,max(END_TIME) END_TIME  from "
				+ "CAR.cdt_history_table "
				+ "where INPUT_FILE like '%'||TO_CHAR(sysdate-2,'yyyymmdd')||'%' "
				+ "group by PROCESS_NAME) "
				+ ") Daymin1 "
				+ "ON curday.PROCESS_NAME = Daymin1.PROCESS_NAME "
				+ "FULL OUTER JOIN "
				+ "(select  PROCESS_NAME, LOADED_RECORDS, END_TIME  from (select PROCESS_NAME, max(LOADED_RECORDS) LOADED_RECORDS,max(END_TIME) END_TIME  from "
				+ "CAR.cdt_history_table "
				+ "where INPUT_FILE like '%'||TO_CHAR(sysdate-3,'yyyymmdd')||'%' "
				+ "group by PROCESS_NAME) "
				+ ")Daymin2 "
				+ "ON Daymin1.PROCESS_NAME = Daymin2.PROCESS_NAME "
				+ "FULL OUTER JOIN "
				+ "(select  PROCESS_NAME, LOADED_RECORDS, END_TIME  from (select PROCESS_NAME, max(LOADED_RECORDS) LOADED_RECORDS,max(END_TIME) END_TIME  from "
				+ "CAR.cdt_history_table "
				+ "where INPUT_FILE like '%'||TO_CHAR(sysdate-4,'yyyymmdd')||'%' "
				+ "group by PROCESS_NAME) "
				+ ")Daymin3 "
				+ "ON Daymin2.PROCESS_NAME = Daymin3.PROCESS_NAME "
				+ "FULL OUTER JOIN "
				+ "(select  PROCESS_NAME, LOADED_RECORDS, END_TIME  from (select PROCESS_NAME, max(LOADED_RECORDS) LOADED_RECORDS,max(END_TIME) END_TIME  from "
				+ "CAR.cdt_history_table "
				+ "where INPUT_FILE like '%'||TO_CHAR(sysdate-5,'yyyymmdd')||'%' "
				+ "group by PROCESS_NAME) "
				+ ")Daymin4 "
				+ "ON Daymin3.PROCESS_NAME = Daymin4.PROCESS_NAME ) "
				+ "where PROCESS_NAME like 'SP%'";

		return sql;

	}

	/**
	 * Returns a MDMDailyView row mapper
	 * 
	 * @return
	 */
	private RowMapper<MDMDailyView> getRowMapperForMDMDailyView() {
		return new RowMapper<MDMDailyView>() {
			@Override
			public MDMDailyView mapRow(ResultSet rs, int arg1)
					throws SQLException {
				MDMDailyView view = new MDMDailyView();
				view.setProcessName(rs.getString("process_name"));
				view.setLoadedRecordToday(rs.getInt("loaded_records_today"));
				view.setLoadedRecordOneDayAgo(rs
						.getInt("loaded_records_one_day_ago"));
				view.setLoadedRecordTwoDayAgo(rs
						.getInt("loaded_records_two_days_ago"));
				view.setLoadedRecordThreeDayAgo(rs
						.getInt("loaded_records_three_days_ago"));
				view.setLoadedRecordFourDayAgo(rs
						.getInt("loaded_records_four_days_ago"));
				return view;
			}
		};
	}

	public OfferDailyView getOffersDailyView(int interval) {
		return getOffersCountForInterval(interval);
	}

	private OfferDailyView getOffersCountForInterval(int interval) {
		
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				standByDatabaseDataSource);

		sql = getSQLForOffersPresented(interval);

		Map<String, String> namedParameters = new HashMap<>();

		return template.queryForObject(sql, namedParameters, getOfferDailyViewMapper());

	}

	private RowMapper<OfferDailyView> getOfferDailyViewMapper() {
		
		return new RowMapper<OfferDailyView>() {
			@Override
			public OfferDailyView mapRow(ResultSet rs, int arg1)
					throws SQLException {
				OfferDailyView view = new OfferDailyView();
				
				view.setCcrAcceptedCount(rs.getInt("ccr_accepted"));
				view.setCcrLaterCount(rs.getInt("ccr_later"));
				view.setCcrShownCount(rs.getInt("ccr_shown"));
				
				view.setPpfeAcceptedCount(rs.getInt("ppfe_accepted"));
				view.setPpfeLaterCount(rs.getInt("ppfe_later"));
				view.setPpfeShownCount(rs.getInt("ppfe_shown"));
				
				view.setUssdAcceptedCount(rs.getInt("ussd_accepted"));
				view.setUssdLaterCount(rs.getInt("ussd_later"));
				view.setUssdShownCount(rs.getInt("ussd_shown"));

				return view;
			}
		};
		
		
	}

	private String getSQLForOffersPresented(int interval) {
		String sql = "select "
				  +"sum(ussd_shown) ussd_shown "
				  +", sum(ussd_later) ussd_later "
				  +", sum(ussd_accepted) ussd_accepted "
				  +", sum(ppfe_shown) ppfe_shown "
				  +", sum(ppfe_later) ppfe_later "
				  +", sum(ppfe_accepted) ppfe_accepted "
				  +", sum(ccr_shown) ccr_shown "
				  +", sum(ccr_later) ccr_later "
				  +", sum(ccr_accepted) ccr_accepted "
				  +"from "
				  +"( "
				  +"select "
				  +"  case when (username = 'USSD' and response = 'shown')  then 1 else 0 end ussd_shown "
				  +", case when (username = 'USSD' and response = 'later')  then 1 else 0 end ussd_later "
				  +", case when (username = 'USSD' and response = 'accepted')  then 1 else 0 end ussd_accepted "
				  +", case when (username = 'PPFE' and response = 'shown')  then 1 else 0 end ppfe_shown "
				  +", case when (username = 'PPFE' and response = 'later')  then 1 else 0 end ppfe_later "
				  +", case when (username = 'PPFE' and response = 'accepted')  then 1 else 0 end ppfe_accepted "
				  +", case when (username <> 'PPFE' and username <> 'USSD' and response = 'shown')  then 1 else 0 end ccr_shown "
				  +", case when (username <> 'PPFE' and username <> 'USSD' and response = 'later')  then 1 else 0 end ccr_later "
				  +", case when (username <> 'PPFE' and username <> 'USSD' and response = 'accepted')  then 1 else 0 end ccr_accepted "
				  +"from "
				   +"   cdm_is.IS_PROPOSITION_HISTORY isph "
				  +",   cdm_is.IS_PROPOSITION isp "
				  +",   cdm_is.IS_RESPONSE isr "
				  +",   cdm_is.IS_ISUSER isu "
				  +"where "
				  +"    isph.proposition_id = isp.proposition_id "
				  +"and isph.response_id = isr.response_id "
				  +"and isph.user_id = isu.user_id "
				  +"and isph.offered_on >= (sysdate - 15/1440) "
				  +")";

		return sql;
	}

	public List<OfferView> getOffers(OffersSelectionCriteria criteria) {

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				reportingSchemaDataSource);

		String sql = getSQLForOffers(criteria);

		RowMapper<OfferView> rowMapper = getOffersRowMapper();

		Map<String, String> namedParameters = new HashMap<>();

		namedParameters.put("startDate", criteria.getDateReportingOn());

		List<OfferView> list = template.query(sql, namedParameters,
				rowMapper);

		return list;

	}
	
	public List<OfferView> getOffersLastFourHours() {

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				standByDatabaseDataSource);

		String sql = getSQLForOffersLastFourHours();

		RowMapper<OfferView> rowMapper = getOffersRowMapper();

		Map<String, String> namedParameters = new HashMap<>();

		List<OfferView> list = template.query(sql, namedParameters,
				rowMapper);

		return list;

	}


	private String getSQLForOffersLastFourHours() {
		
		String sql = "select " +
				" b.username " +
				" ,case when c.name = 'No Offer' then 'No Offer' else 'Offer Exists' end as offer_exists_ind " +
				" ,upper(e.response) as response " +
				" ,case when h.proposition_history_id is not null then 'Y' else 'N' end as fulfilment_ind " +
				" , count(*) num_records " +
				" from cdm_is.is_proposition_history a " +
				" inner join cdm_is.is_isuser b " +
				" on a.user_id = b.user_id " +
				" inner join cdm_is.is_proposition c " +
				" on a.proposition_id = c.proposition_id " +
				" inner join cdm_is.is_response e " +
				" on a.response_id = e.response_id " +
				" left outer join cdm_is.is_proposition_history_ext g " +
				" on a.id = g.proposition_history_id and g.category_id = 23 " +
				" left outer join cdm_is.is_proposition_history_ext@cdmprod h " +
				" on a.id = h.proposition_history_id and h.category_id = 61 and h.value_text = 'true' " +
				" where  " +
				" to_char(a.offered_on,'yyyy-mm-dd') >= (systimestamp - 120/1440) " +
				" and g.value_text = 'J4' " +
				" group by  " +
				" b.username " +
				" ,case when c.name = 'No Offer' then 'No Offer' else 'Offer Exists' end " +
				" ,e.response " +
				" ,case when h.proposition_history_id is not null then 'Y' else 'N' end ";
						
		return sql;
	}

	private String getSQLForOffers(OffersSelectionCriteria criteria) {
		
		String sql = "select "
				+" username "
				+" , offer_exists_ind "
				+" , response "
				+" , fulfilment_ind "
				+" , sum(num_records) num_records "
				+" from j4u_offer_stats "
				+" where "
				+" to_char(date_created,'yyyy-mm-dd') >= :startDate "
				+" group by username, offer_exists_ind, response, fulfilment_ind";
		
		String sql2 = "select " +
				" b.username " +
				" ,case when c.name = 'No Offer' then 'No Offer' else 'Offer Exists' end as offer_exists_ind " +
				" ,upper(e.response) as response " +
				" ,case when h.proposition_history_id is not null then 'Y' else 'N' end as fulfilment_ind " +
				" , count(*) num_records " +
				" from cdm_is.is_proposition_history a " +
				" inner join cdm_is.is_isuser b " +
				" on a.user_id = b.user_id " +
				" inner join cdm_is.is_proposition c " +
				" on a.proposition_id = c.proposition_id " +
				" inner join cdm_is.is_response e " +
				" on a.response_id = e.response_id " +
				" left outer join cdm_is.is_proposition_history_ext g " +
				" on a.id = g.proposition_history_id and g.category_id = 23 " +
				" left outer join cdm_is.is_proposition_history_ext@cdmprod h " +
				" on a.id = h.proposition_history_id and h.category_id = 61 and h.value_text = 'true' " +
				" where  " +
				" to_char(a.offered_on,'yyyy-mm-dd') >= :startDate " +
				" and g.value_text = 'J4' " +
				" group by  " +
				" b.username " +
				" ,case when c.name = 'No Offer' then 'No Offer' else 'Offer Exists' end " +
				" ,e.response " +
				" ,case when h.proposition_history_id is not null then 'Y' else 'N' end ";
		
		return sql;
		
	}

	private RowMapper<OfferView> getOffersRowMapper() {
		
		return new RowMapper<OfferView>() {

			@Override
			public OfferView mapRow(ResultSet rs, int arg1)
					throws SQLException {
				
				OfferView offerView = new OfferView();
				
				offerView.setUsername(rs.getString("USERNAME"));
				offerView.setResponse(rs.getString("RESPONSE"));
				offerView.setOfferExistsInd(rs.getString("OFFER_EXISTS_IND"));
				offerView.setNumRecords(rs.getLong("NUM_RECORDS"));
				offerView.setFulfilmentInd(rs.getString("FULFILMENT_IND"));
				
				return offerView;
			}
		};
		
	}

	public List<MaterialisedView> getViewDetails(String viewname) {

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				interimDatabaseDataSource);

		String sql = getSQLForMV();

		RowMapper<MaterialisedView> rowMapper = getMVRowMapper();

		Map<String, String> namedParameters = new HashMap<>();
		
		namedParameters.put("viewName", "%"+viewname+"%");


		List<MaterialisedView> list = template.query(sql, namedParameters,
				rowMapper);

		return list;

	}

	private RowMapper<MaterialisedView> getMVRowMapper() {
		return new RowMapper<MaterialisedView>() {

			@Override
			public MaterialisedView mapRow(ResultSet rs, int arg1)
					throws SQLException {
				
				MaterialisedView view = new MaterialisedView();
				
				view.setLastDdl(rs.getString("last_ddl_time"));
				view.setObjectName(rs.getString("object_name"));
				view.setObjectType(rs.getString("object_type"));
				
				return view;
			}
		};
	}

	private String getSQLForMV() {
		String sql = "SELECT object_name, object_type, last_ddl_time "
						  +" FROM all_objects "
						  +" WHERE object_name like :viewName ";
		return sql;
	}

	public DatabaseStatusView queryCarTable(String tablename, String database) {
		
		String sql = "select 1 from " + tablename + " WHERE ROWNUM=1";
		
		JdbcTemplate template = null;
		
		if(StringUtils.equalsIgnoreCase("prod", database)) {
			template = new JdbcTemplate(
					standByDatabaseDataSource);			
		} else if (StringUtils.equalsIgnoreCase("standby", database)) {
			template = new JdbcTemplate(
					standByDatabaseDataSource);
		} else {
			template = new JdbcTemplate(
					interimDatabaseDataSource);
		}
		
		DateTime start = new DateTime();
		
		int dataRow = template.queryForObject(sql, Integer.class);
		
		DateTime end = new DateTime();
		
		DatabaseStatusView view = new DatabaseStatusView();
		
		view.setDataAvailable(dataRow == 1 ? true:false);
		view.setTableName(tablename);
		view.setTimeTaken(end.getMillis() - start.getMillis());
		
		
		
		
		return view;
	}

	public List<OfferView> getOffersForLastHour() {

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				standByDatabaseDataSource);

		String sql = getSQLForLastHourOffers();

		RowMapper<OfferView> rowMapper = getOffersRowMapper();

		Map<String, String> namedParameters = new HashMap<>();

		List<OfferView> list = template.query(sql, namedParameters,
				rowMapper);

		return list;

	}

	private String getSQLForLastHourOffers() {
		String sql = "select " +
				" b.username " +
				" ,case when c.name = 'No Offer' then 'No Offer' else 'Offer Exists' end as offer_exists_ind " +
				" ,upper(e.response) as response " +
				" ,case when h.proposition_history_id is not null then 'Y' else 'N' end as fulfilment_ind " +
				" , count(*) num_records " +
				" from cdm_is.is_proposition_history a " +
				" inner join cdm_is.is_isuser b " +
				" on a.user_id = b.user_id " +
				" inner join cdm_is.is_proposition c " +
				" on a.proposition_id = c.proposition_id " +
				" inner join cdm_is.is_response e " +
				" on a.response_id = e.response_id " +
				" left outer join cdm_is.is_proposition_history_ext g " +
				" on a.id = g.proposition_history_id and g.category_id = 23 " +
				" left outer join cdm_is.is_proposition_history_ext@cdmprod h " +
				" on a.id = h.proposition_history_id and h.category_id = 61 and h.value_text = 'true' " +
				" where  " +
				" a.offered_on >= (sysdate - 60/1440) " +
				" and g.value_text = 'J4' " +
				" group by  " +
				" b.username " +
				" ,case when c.name = 'No Offer' then 'No Offer' else 'Offer Exists' end " +
				" ,e.response " +
				" ,case when h.proposition_history_id is not null then 'Y' else 'N' end ";
		
		return sql;
		
	}

	public int getRechargeAndGetCount(int i) {
		String sql = getSQLForRechargeAndGetCount(i);
		
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				interimDatabaseDataSource);

		Map<String, String> namedParameters = new HashMap<>();

		int count = template.queryForObject(sql, namedParameters, Integer.class);
		
		return count;
	}

	private String getSQLForRechargeAndGetCount(int i) {
		String sql = "select count(cre.update_date) as UPDATE_COUNT "
				+ " from car.cust_remaining_effort_vw cre "
				+ " where cre.update_date > SYSDATE - INTERVAL '" +  i + "' HOUR";
		return sql;
	}
	//sonigau
	public List<KPIHourlyCountView> getKPIHourlyCount(String tableName){
		String sql = getSQLForKPIHourlyCount(tableName);
		
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				reportingSchemaDataSource);
		RowMapper<KPIHourlyCountView> rowMapper = getKPIHourlyCountRowMapper();

		Map<String, String> namedParameters = new HashMap<>();

		List<KPIHourlyCountView> list = template.query(sql, namedParameters,
				rowMapper);
		return list;
		
		
	}
	
	private String getSQLForKPIHourlyCount(String tableName){
		String sql =
				"WITH COUNT_TABLE AS                                                 "+
				"  (SELECT HOST,                                                     "+
				"    INSTANCEID,                                                     "+
				"    COUNT(*) AS TRA                                                 "+
				"  FROM CDM_KPI."+
				tableName		+
				"  WHERE TS >= systimestamp - 1/24     "+
				"  GROUP BY host,                                                    "+
				"    INSTANCEID                                                      "+
				"  ORDER BY host,                                                    "+
				"    INSTANCEID                                                      "+
				"  )                                                                 "+
				"SELECT CHANNEL_ID_LOOKUP.NAME CHANNEL,                              "+
				"  HOST_ID_LOOKUP.HOST SERVER_NAME,                                  "+
				"  COALESCE(RTDS1,0) RTDS1,                                          "+
				"  COALESCE(RTDS2,0) RTDS2,                                          "+
				"  COALESCE(RTDS3,0) RTDS3,                                          "+
				"  COALESCE(RTDS4,0) RTDS4,                                          "+
				"  COALESCE(RTDS5,0) RTDS5,                                          "+
				"  COALESCE(RTDS6,0) RTDS6,                                          "+
				"  COALESCE(RTDS7,0) RTDS7,                                          "+
				"  COALESCE(RTDS8,0) RTDS8,                                          "+
				"  COALESCE(RTDS9,0) RTDS9                                           "+
				"FROM CDM_KPI.HOST_ID_LOOKUP                                         "+
				"FULL OUTER JOIN                                                     "+
				"  (SELECT HOST, TRA RTDS1 FROM COUNT_TABLE WHERE INSTANCEID = 1     "+
				"  ) INST1                                                           "+
				"ON INST1.HOST = HOST_ID_LOOKUP.HOST                                 "+
				"FULL OUTER JOIN                                                     "+
				"  (SELECT HOST, TRA RTDS2 FROM COUNT_TABLE WHERE INSTANCEID = 2     "+
				"  ) INST2                                                           "+
				"ON INST1.HOST = INST2.HOST                                          "+
				"FULL OUTER JOIN                                                     "+
				"  (SELECT HOST, TRA RTDS3 FROM COUNT_TABLE WHERE INSTANCEID = 3     "+
				"  ) INST3                                                           "+
				"ON INST2.HOST = INST3.HOST                                          "+
				"FULL OUTER JOIN                                                     "+
				"  (SELECT HOST, TRA RTDS4 FROM COUNT_TABLE WHERE INSTANCEID = 4     "+
				"  ) INST4                                                           "+
				"ON INST3.HOST = INST4.HOST                                          "+
				"FULL OUTER JOIN                                                     "+
				"  (SELECT HOST, TRA RTDS5 FROM COUNT_TABLE WHERE INSTANCEID = 5     "+
				"  ) INST5                                                           "+
				"ON INST4.HOST = INST5.HOST                                          "+
				"FULL OUTER JOIN                                                     "+
				"  (SELECT HOST, TRA RTDS6 FROM COUNT_TABLE WHERE INSTANCEID = 6     "+
				"  ) INST6                                                           "+
				"ON INST5.HOST = INST6.HOST                                          "+
				"FULL OUTER JOIN                                                     "+
				"  (SELECT HOST, TRA RTDS7 FROM COUNT_TABLE WHERE INSTANCEID = 7     "+
				"  ) INST7                                                           "+
				"ON INST6.HOST = INST7.HOST                                          "+
				"FULL OUTER JOIN                                                     "+
				"  (SELECT HOST, TRA RTDS8 FROM COUNT_TABLE WHERE INSTANCEID = 8     "+
				"  ) INST8                                                           "+
				"ON INST7.HOST = INST8.HOST                                          "+
				"FULL OUTER JOIN                                                     "+
				"  (SELECT HOST, TRA RTDS9 FROM COUNT_TABLE WHERE INSTANCEID = 9     "+
				"  ) INST9                                                           "+
				"ON INST8.HOST = INST9.HOST                                          "+
				"INNER JOIN CDM_KPI.CHANNEL_ID_LOOKUP                                "+
				"ON HOST_ID_LOOKUP.CHANNELID = CHANNEL_ID_LOOKUP.ID                  "+
				"ORDER BY CHANNEL_ID_LOOKUP.ID DESC,                                 "+
				"  HOST_ID_LOOKUP.HOST                                              ";
		
		
		return sql;
	}
	
	private RowMapper<KPIHourlyCountView> getKPIHourlyCountRowMapper() {
		
		return new RowMapper<KPIHourlyCountView>() {

			@Override
			public KPIHourlyCountView mapRow(ResultSet rs, int arg1)
					throws SQLException {
				
				KPIHourlyCountView hourlyCountView = new KPIHourlyCountView(rs.getString("CHANNEL"),rs.getString("SERVER_NAME"),rs.getInt("RTDS1"),rs.getInt("RTDS2"),rs.getInt("RTDS3"),rs.getInt("RTDS4"),rs.getInt("RTDS5"),rs.getInt("RTDS6"),rs.getInt("RTDS7"),rs.getInt("RTDS8"),rs.getInt("RTDS9"));
				
				return hourlyCountView;
			}
		};
		
	}

	public List<TPSHourlyCountView> getTPSHourlyCount(String channelName) {
		String sql = getSQLForTPSHourlyCount(channelName);
		
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				reportingSchemaDataSource);
		RowMapper<TPSHourlyCountView> rowMapper = getTPSHourlyCountRowMapper();

		Map<String, String> namedParameters = new HashMap<>();

		List<TPSHourlyCountView> list = template.query(sql, namedParameters,
				rowMapper);
		return list;
		
	}

	private RowMapper<TPSHourlyCountView> getTPSHourlyCountRowMapper() {
		return new RowMapper<TPSHourlyCountView>() {
			@Override
			public TPSHourlyCountView mapRow(ResultSet rs, int arg1)
					throws SQLException {
								
				TPSHourlyCountView tpsCountView = new TPSHourlyCountView();
				tpsCountView.setTs(rs.getTimestamp("TRANS_TIME"));
				tpsCountView.setChannelName(rs.getString("NAME"));
				tpsCountView.setType(rs.getString("TYPE"));
				tpsCountView.setAmount(rs.getInt("AMOUNT"));
				tpsCountView.setTps(rs.getFloat("TPS"));
				tpsCountView.setL100(rs.getFloat("L100"));
				tpsCountView.setL200(rs.getFloat("L200"));
				tpsCountView.setL300(rs.getFloat("L300"));
				tpsCountView.setL400(rs.getFloat("L400"));
				tpsCountView.setL500(rs.getFloat("L500"));
				tpsCountView.setL600(rs.getFloat("L600"));
				tpsCountView.setL700(rs.getFloat("L700"));
				tpsCountView.setL800(rs.getFloat("L800"));
				tpsCountView.setL900(rs.getFloat("L900"));
				tpsCountView.setL1000(rs.getFloat("L1000"));
				tpsCountView.setSla_target(rs.getInt("SLA_TARGET"));
				
				return tpsCountView;
			}
		};
		
	}

	private String getSQLForTPSHourlyCount(String channelName) {
		String sql = 
				"SELECT                                                                                    "+
				"  a.ts TRANS_TIME , c.name NAME   ,  d.type TYPE,  a.amount AMOUNT,  a.tps TPS ,  a.l100 L100          "+
				" ,  a.l200 L200,  a.l300 L300,  a.l400 L400, "+
				"  a.l500 L500,  a.l600 L600,  a.l700 L700,  a.l800 L800,  a.l900 L900,  a.l1000 L1000 "+
				" ,  ROUND(100 - 5) SLA_TARGET "+
				"FROM cdm_kpi.access_event_channel_min a                                                   "+
				"LEFT JOIN cdm_kpi.channel_id_lookup c                                                     "+
				"ON (a.channel_id = c.id)                                                                  "+
				"LEFT JOIN cdm_kpi.event_type d                                                            "+
				"ON (a.TYPE_ID = d.key)                                                                    "+
				"WHERE a.ts   >= systimestamp - 1/24                         "+
				"AND c.name = '"+ channelName+				
				"' AND d.type = 'Combined'                                                                   "+
				"ORDER BY a.tps desc, 			                                                                       "+
				"  a.ts DESC,                                                                              "+
				"  c.id,                                                                                   "+
				"  d.key";
		return sql;
	}

	public OfferCountView getOfferCount15min() {
		
		
		String sql = getSQLForOfferCount15min();
		
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				standByDatabaseDataSource);
		
		RowMapper<OfferCountView> rowMapper = getOfferCountRowMapper();

		Map<String, String> namedParameters = new HashMap<>();

		List<OfferCountView> list = template.query(sql, namedParameters,
				rowMapper);
		OfferCountView offerCounts = list.get(0);
		return offerCounts;		
	}
	
	private RowMapper<OfferCountView> getOfferCountRowMapper() {
		return new RowMapper<OfferCountView>() {
			@Override
			public OfferCountView mapRow(ResultSet rs, int arg1)
					throws SQLException {
								
				OfferCountView offerCountView = new OfferCountView();
				offerCountView.setUssdShown(rs.getInt("ussd_shown"));
				offerCountView.setUssdAccepted(rs.getInt("ussd_accepted"));
				offerCountView.setPpfeShown(rs.getInt("ppfe_shown"));
				offerCountView.setCcrShown(rs.getInt("ccr_shown"));
				offerCountView.setCcrAccepted(rs.getInt("ccr_accepted"));
				
				return offerCountView ;
			}
		};
	}

	private String getSQLForOfferCount15min(){
		String sql = 
				"SELECT SUM(ussd_shown) ussd_shown ,             "+
				"SUM(ussd_accepted) ussd_accepted ,              "+
				"SUM(ppfe_shown) ppfe_shown ,                    "+
				"SUM(ccr_shown) ccr_shown ,                      "+
				"SUM(ccr_accepted) ccr_accepted                  "+
				"FROM                                            "+
				"(SELECT                                         "+
				"  CASE                                          "+
				"	WHEN (username = 'USSD'                      "+
				"	AND response   = 'shown')                    "+
				"	THEN 1                                       "+
				"	ELSE 0                                       "+
				"  END ussd_shown ,                              "+
				"  CASE                                          "+
				"	WHEN (username = 'USSD'                      "+
				"	AND response   = 'accepted')                 "+
				"	THEN 1                                       "+
				"	ELSE 0                                       "+
				"  END ussd_accepted ,                           "+
				"  CASE                                          "+
				"	WHEN (username = 'PPFE'                      "+
				"	AND response   = 'shown')                    "+
				"	THEN 1                                       "+
				"	ELSE 0                                       "+
				"  END ppfe_shown ,                              "+
				"  CASE                                          "+
				"	WHEN (username <> 'PPFE'                     "+
				"	AND username   <> 'USSD'                     "+
				"	AND response    = 'shown')                   "+
				"	THEN 1                                       "+
				"	ELSE 0                                       "+
				"  END ccr_shown ,                               "+
				"  CASE                                          "+
				"	WHEN (username <> 'PPFE'                     "+
				"	AND username   <> 'USSD'                     "+
				"	AND response    = 'accepted')                "+
				"	THEN 1                                       "+
				"	ELSE 0                                       "+
				"  END ccr_accepted                              "+
				"FROM cdm_is.IS_PROPOSITION_HISTORY isph ,       "+
				"  cdm_is.IS_PROPOSITION isp ,                   "+
				"  cdm_is.IS_RESPONSE isr ,                      "+
				"  cdm_is.IS_ISUSER isu                          "+
				"WHERE isph.proposition_id = isp.proposition_id  "+
				"AND isph.response_id      = isr.response_id     "+
				"AND isph.user_id          = isu.user_id         "+
				"AND isph.offered_on      >= (sysdate - 15/1440))"
				;
		return sql;
	}

	public List<OfferDBCountView> getOfferDBCount() {
		String sql = getSQLForOfferDBCount();
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				standByDatabaseDataSource);
		RowMapper<OfferDBCountView> rowMapper = getOfferDBCountRowMapper();
		Map<String, String> namedParameters = new HashMap<>();

		List<OfferDBCountView> list = template.query(sql, namedParameters,
				rowMapper);
		return list;
	}

	private RowMapper<OfferDBCountView> getOfferDBCountRowMapper() {
		return new RowMapper<OfferDBCountView>() {
			@Override
			public OfferDBCountView mapRow(ResultSet rs, int arg1)
					throws SQLException {
								
				OfferDBCountView offerDBCountView = new OfferDBCountView();
				offerDBCountView.setOfferedOn(rs.getString("OFFERED_ON"));
				offerDBCountView.setUssd(rs.getInt("USSD"));
				offerDBCountView.setPpfe(rs.getInt("PPFE"));
				offerDBCountView.setMobiApp(rs.getInt("MOBI_APP"));				
				return offerDBCountView ;
			}
		};
	}

	private String getSQLForOfferDBCount() {
		String sql = 
				"SELECT OFFERED_ON ,                                                                                                                             "+
				"  SUM (DECODE (uname, 'USSD', cnt)) AS USSD ,                                                                                                   "+
				"  SUM (DECODE (uname, 'PPFE', cnt)) AS PPFE,                                                                                                    "+
				"  SUM (DECODE (uname, 'APP', cnt))  AS MOBI_APP                                                                                                 "+
				"FROM (SELECT TO_CHAR(TRUNC(isph.OFFERED_ON,'hh24')+(TRUNC(TO_CHAR(isph.OFFERED_ON,'mi')/10)*10)/24/60,'DD-MON-YYYY HH24:MI:SS' ) AS OFFERED_ON ,"+
				"    isu.username uname ,                                                                                                                        "+
				"    COUNT(DISTINCT(isph.conversation_id)) cnt                                                                                                   "+
				"  FROM cdm_is.IS_PROPOSITION_HISTORY isph ,                                                                                                     "+
				"    cdm_is.IS_PROPOSITION isp ,                                                                                                                 "+
				"    cdm_is.IS_RESPONSE isr ,                                                                                                                    "+
				"    cdm_is.IS_ISUSER isu                                                                                                                        "+
				"  WHERE isph.proposition_id = isp.proposition_id                                                                                                "+
				"  AND isph.response_id      = isr.response_id                                                                                                   "+
				"  AND isph.user_id          = isu.user_id                                                                                                       "+
				"  AND isph.offered_on >= (TRUNC(CURRENT_DATE, 'HH24') - interval '1' hour)                                                                      "+
				"    GROUP BY (TRUNC(isph.OFFERED_ON,'hh24')+(TRUNC(TO_CHAR(isph.OFFERED_ON,'mi')/10)*10)/24/60),                                                "+
				"    isu.USERNAME                                                                                                                                "+
				"  )                                                                                                                                             "+
				"GROUP BY OFFERED_ON                                                                                                                             "+
				"ORDER BY OFFERED_ON DESC";
		return sql;
	}
	
	
	
	
}
