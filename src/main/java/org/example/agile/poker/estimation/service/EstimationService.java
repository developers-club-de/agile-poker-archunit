package org.example.agile.poker.estimation.service;


import org.example.agile.poker.estimation.service.model.Estimation;
import org.example.agile.poker.estimation.service.model.EstimationSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EstimationService {

    private final EstimationSessionRepository estimationSessionRepository;

    public EstimationService(EstimationSessionRepository estimationSessionRepository) {
        this.estimationSessionRepository = estimationSessionRepository;
    }

    public EstimationSession createSession() {
        EstimationSession estimationSession = EstimationSession.createNew();
        estimationSessionRepository.saveNew(estimationSession);
        return estimationSession;
    }

    public void addEstimationToSession(UUID sessionId, Estimation estimation) {
        EstimationSession session = estimationSessionRepository.findByUuid(sessionId).orElseThrow();
        List<Estimation> estimations = new ArrayList<>(session.getEstimations());
        estimations.add(Estimation.createFromString(estimation.getValue()));
        estimationSessionRepository.update(new EstimationSession(session.getUuid(), estimations));
    }

    public Optional<EstimationSession> findSession(UUID sessionId) {
        return estimationSessionRepository.findByUuid(sessionId);
    }

}
