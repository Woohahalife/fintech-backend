package com.core.backend.participant.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.backend.participant.domain.Participant;
import com.core.backend.participant.domain.repository.ParticipantRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ParticipantRepositoryImpl implements ParticipantRepository {

	private final JpaParticipantRepository repository;

	@Override
	public List<Participant> findAllBySettlementId(Long settlementId) {
		return repository.findAllBySettlementId(settlementId);
	}
}