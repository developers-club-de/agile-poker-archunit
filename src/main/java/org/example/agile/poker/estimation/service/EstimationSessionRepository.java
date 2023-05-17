package org.example.agile.poker.estimation.service;

import org.example.agile.poker.estimation.service.model.EstimationSession;

import java.util.Optional;
import java.util.UUID;

public interface EstimationSessionRepository {
    void saveNew(EstimationSession estimationSession);

    void update(EstimationSession estimationSession);

    Optional<EstimationSession> findByUuid(UUID sessionId);
}
