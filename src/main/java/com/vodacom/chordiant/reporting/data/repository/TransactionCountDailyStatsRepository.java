package com.vodacom.chordiant.reporting.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vodacom.chordiant.reporting.data.mapping.TransactionCountStatisticalDataDailyEntity;

/**
 * Repository class to retrieve daily stats
 * 
 * @author Gaurav Soni
 * 
 */
public interface TransactionCountDailyStatsRepository extends
		JpaRepository<TransactionCountStatisticalDataDailyEntity, Long> {

	@Query(value="select dt from TransactionCountStatisticalDataDailyEntity dt where dt.requestDate >= :startDate and dt.requestDate <= :endDate and dt.url like :offerType order by dt.requestDate ASC")
	List<TransactionCountStatisticalDataDailyEntity> findForPeriod(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("offerType") String offerType);

	@Query(value="select dt from TransactionCountStatisticalDataDailyEntity dt where dt.requestDate >= :startDate and dt.requestDate <= :endDate order by dt.requestDate ASC")
	List<TransactionCountStatisticalDataDailyEntity> findForPeriod(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate);

}
