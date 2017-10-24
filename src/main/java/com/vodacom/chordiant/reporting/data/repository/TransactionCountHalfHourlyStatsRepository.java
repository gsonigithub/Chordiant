package com.vodacom.chordiant.reporting.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vodacom.chordiant.reporting.data.mapping.TransactionCountStatisticalDataHalfHourEntity;

public interface TransactionCountHalfHourlyStatsRepository extends
		JpaRepository<TransactionCountStatisticalDataHalfHourEntity, Long> {

	@Query(value="select tc from TransactionCountStatisticalDataHalfHourEntity tc where tc.requestDate >= :startDate and tc.requestDate <= :endDate and tc.url like :offerType order by tc.interval ASC")
	List<TransactionCountStatisticalDataHalfHourEntity> findForPeriod(
			@Param("startDate") String startDate,
			@Param("endDate") String endDate,
			@Param("offerType") String offerType);

}
