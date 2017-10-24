package com.vodacom.chordiant.reporting.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vodacom.chordiant.reporting.data.mapping.J4UOfferStatsEntity;

public interface J4UOfferStatsRepository extends
		JpaRepository<J4UOfferStatsEntity, Long> {

}
