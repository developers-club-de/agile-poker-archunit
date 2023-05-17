package org.example.agile.poker.estimation.persistence.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.agile.poker.estimation.service.model.Estimation;
import org.example.agile.poker.estimation.service.model.EstimationSession;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EstimationSessionEntity extends AbstractPersistable<Long> {

    private UUID uuid;

    @OneToMany(targetEntity = EstimationEntity.class, cascade = CascadeType.ALL)
    private Collection<EstimationEntity> estimations;

    public EstimationSession toEstimationSession() {
        List<Estimation> estimations = getEstimations().stream().map(EstimationEntity::toEstimation).toList();
        return new EstimationSession(getUuid(), estimations);
    }

}

