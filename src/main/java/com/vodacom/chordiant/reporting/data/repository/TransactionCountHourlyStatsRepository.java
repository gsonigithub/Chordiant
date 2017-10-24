package com.vodacom.chordiant.reporting.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vodacom.chordiant.reporting.data.mapping.TransactionCountStatisticalDataHourlyEntity;

public interface TransactionCountHourlyStatsRepository extends
		JpaRepository<TransactionCountStatisticalDataHourlyEntity, Long> {

	@Query(value = "select tc from TransactionCountStatisticalDataHourlyEntity tc where tc.requestDate >= :startDate and tc.requestDate <= :endDate and tc.url like :offerType order by tc.interval ASC")
	List<TransactionCountStatisticalDataHourlyEntity> findForPeriod(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("offerType") String offerType);

}
