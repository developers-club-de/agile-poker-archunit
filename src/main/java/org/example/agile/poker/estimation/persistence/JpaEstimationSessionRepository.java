package org.example.agile.poker.estimation.persistence;

import org.example.agile.poker.estimation.persistence.entities.EstimationSessionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaEstimationSessionRepository extends CrudRepository<EstimationSessionEntity, Long> {

    Optional<EstimationSessionEntity> findByUuid(UUID sessionId);
}
