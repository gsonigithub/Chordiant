package com.vodacom.chordiant.reporting.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vodacom.chordiant.reporting.data.mapping.ChordiantAvailabilityEntity;

public interface ChordiantAvailabilityRepository extends
		JpaRepository<ChordiantAvailabilityEntity, Long> {

	@Query(value = "select ca from ChordiantAvailabilityEntity ca where ca.startDate >= :startDate and ca.startTime >= :startTime and ca.endDate <= :endDate and ca.endTime <= :endTime")
	List<ChordiantAvailabilityEntity> findAvailabilityForPeriod(
			@Param("startDate") String startDate,
			@Param("startTime") String startTime,
			@Param("endDate") String endDate, @Param("endTime") String endTime);

}
