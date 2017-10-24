package com.vodacom.chordiant.reporting.data.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import org.springframework.stereotype.Component;

import com.vodacom.chordiant.reporting.mvc.view.TPSCountView;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountReportingCriteriaView;
import com.vodacom.chordiant.reporting.mvc.view.TransactionCountView;

@Component
public class TransactionStatisticsRepository {
	
	/**
	 * Data source object that will be used to create the Spring Jdbc Template
	 */
	@Qualifier("reportingSchemaDataSource")
	@Autowired
	private DataSource reportingSchemaDataSource;
	
	
	public List<TransactionCountView> getAggregatedStats(
			TransactionCountReportingCriteriaView criteriaView, String channel) {
		String sql = getSQLForAggregatedStats(criteriaView, channel);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				reportingSchemaDataSource);
		RowMapper<TransactionCountView> rowMapper = getAggregatedStatsRowMapper();
		Map<String, String> namedParameters = new HashMap<>();
		
		List<TransactionCountView> list = template.query(sql, namedParameters,
				rowMapper);
		return list;
		
	}
	
	
	private RowMapper<TransactionCountView> getAggregatedStatsRowMapper() {
		return new RowMapper<TransactionCountView>() {
			@Override
			public TransactionCountView mapRow(ResultSet rs, int arg1)
					throws SQLException {
								
				TransactionCountView transactionCountView = new TransactionCountView();
				transactionCountView.setRequestDate(rs.getString("TS"));
				transactionCountView.setTps110(rs.getFloat("TPS"));
				transactionCountView.setTransactionCount(rs.getInt("AMOUNT"));
				transactionCountView.setCountSla100To200(rs.getInt("NL100"));
				transactionCountView.setCountSla200To300(rs.getInt("NL200"));
				transactionCountView.setCountSla300To400(rs.getInt("NL300"));
				transactionCountView.setCountSla400To500(rs.getInt("NL400"));
				transactionCountView.setCountSla500To600(rs.getInt("NL500"));
				transactionCountView.setCountSla600To700(rs.getInt("NL600"));
				transactionCountView.setCountSla700To800(rs.getInt("NL700"));
				transactionCountView.setCountSla800To900(rs.getInt("NL800"));
				transactionCountView.setCountSla900To1000(rs.getInt("NL900"));
				transactionCountView.setCountSlaUnder100(rs.getInt("NL1000"));
				transactionCountView.setCountSlaOver1000(rs.getInt("NG1000"));				
				transactionCountView.setInSlaPerc400(rs.getFloat("L400"));
				transactionCountView.setInSlaPerc500(rs.getFloat("L500"));
				transactionCountView.setInSlaPerc600(rs.getFloat("L600"));
				transactionCountView.setInSlaPerc700(rs.getFloat("L700"));
				transactionCountView.setInSlaPerc800(rs.getFloat("L800"));
				transactionCountView.setInSlaPerc900(rs.getFloat("L900"));
				transactionCountView.setInSlaPerc1000(rs.getFloat("L1000"));
				transactionCountView.setInSlaPerc1000Plus(rs.getFloat("G1000"));
				transactionCountView.setSlaTarget(rs.getFloat("SLA_TARGET"));
				return transactionCountView ;
			}
		};
	}

	private String getSQLForAggregatedStats(TransactionCountReportingCriteriaView criteriaView, String channel) {
		String tableName = null;
		String type = (channel.equals("CCR")) ? "ALL" : "Combined";
		switch (criteriaView.getIntervals()){
		case "monthly":
			tableName = "cdm_kpi.access_event_channel_month";
			break;
		case "daily":
			tableName = "cdm_kpi.access_event_channel_day";
			break;
		case "hourly":
			tableName = "cdm_kpi.access_event_channel_hour";
			break;
		case "minute":
			tableName = "cdm_kpi.access_event_channel_min";
			break;
		}
		
		String sql = 				                                                                                        
			"	SELECT TO_CHAR(a.ts,'DD-MON-YYYY HH24:MI' ) ts  ,    TPS ,                               "+			
			"	  a.amount ,                                                                             "+
			"	  a.l400 ,                                                                               "+
			"	  a.l500 ,                                                                               "+
			"	  a.l600 ,                                                                               "+
			"	  a.l700 ,                                                                               "+
			"	  a.l800 ,                                                                               "+
			"	  a.l900 ,                                                                               "+
			"	  a.l1000 ,                                                                              "+
			"	  a.g1000 ,                                                                              "+
			"	  a.nl100 ,                                                                               "+
			"	  a.nl200 ,                                                                               "+
			"	  a.nl300 ,                                                                               "+
			"	  a.nl400 ,                                                                               "+
			"	  a.nl500 ,                                                                               "+
			"	  a.nl600 ,                                                                               "+
			"	  a.nl700 ,                                                                               "+
			"	  a.nl800 ,                                                                               "+
			"	  a.nl900 ,                                                                               "+
			"	  a.nl1000 ,                                                                              "+
			"	  a.ng1000 ,                                                                              "+			
			"	  ROUND(100 - 5) SLA_TARGET  FROM                                                         "+
			 tableName +
			"	a LEFT JOIN cdm_kpi.channel_id_lookup c                                                    "+
			"	ON (a.channel_id = c.id)                                                                 "+
			"	LEFT JOIN cdm_kpi.event_type d                                                           "+
			"	ON (a.TYPE_ID = d.key)                                                                   "+
			"	WHERE                       "+
			"	  a.ts >= TIMESTAMP '"+criteriaView.getStartDate() +" "+ criteriaView.getStartTime() +":00'      "+
			"  and a.ts < TIMESTAMP '"+criteriaView.getEndDate()+" " +criteriaView.getEndTime() +":59' "+
			"	AND c.name = '"
			+channel +			
			"'	AND d.type = '"+type+"'                                                                  "+
			
			"	ORDER BY                                                                    "+
			"	  a.ts ASC,                                                                             "+
			"	  c.id,                                                                                  "+
			"	  d.key";                                                                                 
				
				;
		return sql;
		
	}


	public List<TPSCountView> getTPSStats(
			TransactionCountReportingCriteriaView criteriaView, String channel) {
		String sql = getSQLForTPSStats(criteriaView, channel);
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(
				reportingSchemaDataSource);
		RowMapper<TPSCountView> rowMapper = getTPSStatsRowMapper();
		Map<String, String> namedParameters = new HashMap<>();
		
		List<TPSCountView> list = template.query(sql, namedParameters,
				rowMapper);
		return list;
	}


	private RowMapper<TPSCountView> getTPSStatsRowMapper() {
		return new RowMapper<TPSCountView>() {
			@Override
			public TPSCountView mapRow(ResultSet rs, int arg1)
					throws SQLException {
								
				TPSCountView tpsCountView = new TPSCountView();
				tpsCountView.setTs(rs.getString("TS"));
				tpsCountView.setTps(rs.getFloat("TPS"));
				tpsCountView.setMaxTps(rs.getFloat("MAX_TPS"));
				tpsCountView.setAmount(rs.getInt("AMOUNT"));				
				return tpsCountView ;
			}
		};
	}


	private String getSQLForTPSStats(
			TransactionCountReportingCriteriaView criteriaView, String channel) {
		String tableName = null;
		String type = (channel.equals("CCR")) ? "ALL" : "Combined";
		switch (criteriaView.getIntervals()){
		case "monthly":
			tableName = "cdm_kpi.access_event_channel_month";
			break;
		case "daily":
			tableName = "cdm_kpi.access_event_channel_day";
			break;
		case "hourly":
			tableName = "cdm_kpi.access_event_channel_hour";
			break;
		case "minute":
			tableName = "cdm_kpi.access_event_channel_min";
			break;
		}
		
		String sql =                                                                 
			"	SELECT TO_CHAR(a.ts,'DD-MON-YYYY HH24:MI' ) ts  ,    TPS ,                               "+			
			"	  a.amount ,                                                                             "+
			 ((criteriaView.getIntervals().equals("minute")||criteriaView.getIntervals().equals("monthly")) ? "TPS MAX_TPS" : "MAX_TPS")+			
			"	   FROM                                                         						"+
			 tableName +
			"	a LEFT JOIN cdm_kpi.channel_id_lookup c                                                    "+
			"	ON (a.channel_id = c.id)                                                                 "+
			"	LEFT JOIN cdm_kpi.event_type d                                                           "+
			"	ON (a.TYPE_ID = d.key)                                                                   "+
			"	WHERE                       "+
			"	  a.ts >= TIMESTAMP '"+criteriaView.getStartDate() +" "+ criteriaView.getStartTime() +":00'      "+
			"  and a.ts < TIMESTAMP '"+criteriaView.getEndDate()+" " +criteriaView.getEndTime() +":59' "+
			"	AND c.name = '"
			+channel +			
			"'	AND d.type = '"+type+"'                                                                  "+
			
			"	ORDER BY                                                                    "+
			"	  a.ts ASC,                                                                             "+
			"	  c.id,                                                                                  "+
			"	  d.key";                                                                                 
				
				;
		return sql;
	}
}
