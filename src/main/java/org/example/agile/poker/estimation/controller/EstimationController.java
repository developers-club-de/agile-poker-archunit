package org.example.agile.poker.estimation.controller;

import org.example.agile.poker.estimation.controller.dto.EstimationDTO;
import org.example.agile.poker.estimation.controller.dto.EstimationSessionDTO;
import org.example.agile.poker.estimation.controller.dto.EstimationsDTO;
import org.example.agile.poker.estimation.service.EstimationService;
import org.example.agile.poker.estimation.service.model.Estimation;
import org.example.agile.poker.estimation.service.model.EstimationSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpResponse;
import java.util.UUID;

public class EstimationController {

    @Autowired
    private EstimationService estimationService;

    @PostMapping("/session")
    public EstimationSessionDTO createSession() {
        EstimationSession estimationSession = estimationService.createSession();

        return new EstimationSessionDTO(estimationSession.getUuid().toString());
    }

    @PostMapping("/session/{sessionId}/estimations")
    public HttpResponse addEstimation(@PathVariable("sessionId") UUID sessionId, EstimationDTO estimationDTO) {
        estimationService.addEstimationToSession(sessionId, new Estimation(estimationDTO.estimation()));
        return null;
    }

    @GetMapping("/session/{sessionId}/estimations")
    public EstimationsDTO getEstimations() {
        return null;
    }

}
