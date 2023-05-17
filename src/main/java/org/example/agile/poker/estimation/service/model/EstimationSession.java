package org.example.agile.poker.estimation.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class EstimationSession {
    private final UUID uuid;
    private final List<Estimation> estimations;

    public static EstimationSession createNew() {
        return new EstimationSession(UUID.randomUUID(), new ArrayList<>());
    }
}
