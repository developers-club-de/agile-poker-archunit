package org.example.agile.poker.estimation.persistence;

import org.example.agile.poker.estimation.persistence.entities.EstimationEntity;
import org.example.agile.poker.estimation.persistence.entities.EstimationSessionEntity;
import org.example.agile.poker.estimation.service.EstimationSessionRepository;
import org.example.agile.poker.estimation.service.model.Estimation;
import org.example.agile.poker.estimation.service.model.EstimationSession;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EstimationSessionRepositoryImpl implements EstimationSessionRepository {

    private final JpaEstimationSessionRepository estimationSessionRepository;

    public EstimationSessionRepositoryImpl(JpaEstimationSessionRepository estimationSessionRepository) {
        this.estimationSessionRepository = estimationSessionRepository;
    }

    @Override
    public void saveNew(EstimationSession estimationSession) {
        estimationSessionRepository.save(toEstimationSessionEntity(estimationSession));
    }

    @Override
    public void update(EstimationSession estimationSession) {
        EstimationSessionEntity estimationSessionEntity = estimationSessionRepository
                .findByUuid(estimationSession.getUuid()).orElseThrow();
        estimationSessionEntity.getEstimations().clear();
        estimationSessionEntity.getEstimations().addAll(
                estimationSession.getEstimations().stream()
                        .map(this::toEstimationEntity)
                        .toList());
        estimationSessionRepository.save(estimationSessionEntity);
    }

    @Override
    public Optional<EstimationSession> findByUuid(UUID sessionId) {
        return estimationSessionRepository.findByUuid(sessionId).map(EstimationSessionEntity::toEstimationSession);
    }

    private EstimationSessionEntity toEstimationSessionEntity(EstimationSession estimationSession) {
        return new EstimationSessionEntity(estimationSession.getUuid(),
                estimationSession.getEstimations().stream().map(this::toEstimationEntity).collect(Collectors.toList()));
    }

    private EstimationEntity toEstimationEntity(Estimation estimation) {
        return new EstimationEntity(estimation.getValue());
    }

}
