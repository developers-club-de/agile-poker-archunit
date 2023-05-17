package org.example.agile.poker.estimation.service;

import jakarta.transaction.Transactional;
import org.example.agile.poker.estimation.service.model.Estimation;
import org.example.agile.poker.estimation.service.model.EstimationSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class EstimationServiceTest {

    @Autowired
    private EstimationService estimationService;

    @Test
    void createSessionsWithDifferentIds() {
        EstimationSession session1 = estimationService.createSession();
        EstimationSession session2 = estimationService.createSession();

        assertThat(session1.getUuid()).isNotSameAs(session2.getUuid());
    }

    @Test
    void findCreatedSessionById() {
        EstimationSession newSession = estimationService.createSession();

        Optional<EstimationSession> session = estimationService.findSession(newSession.getUuid());

        assertThat(session).isPresent();
        assertThat(session.get()).isEqualTo(newSession);
    }

    @Test
    void addEstimationToSession() {
        EstimationSession newSession = estimationService.createSession();
        Estimation estimation1 = new Estimation("20");
        Estimation estimation2 = new Estimation("5");

        estimationService.addEstimationToSession(newSession.getUuid(), estimation1);
        estimationService.addEstimationToSession(newSession.getUuid(), estimation2);

        Optional<EstimationSession> session = estimationService.findSession(newSession.getUuid());
        assertThat(session).isNotEmpty();
        assertThat(session.get().getEstimations()).containsExactly(estimation1, estimation2);
    }
}
