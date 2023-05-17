package org.example.agile.poker.estimation.persistence.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.agile.poker.estimation.service.model.Estimation;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EstimationEntity extends AbstractPersistable<Long> {

    private String valueAsString;

    Estimation toEstimation() {
        return new Estimation(getValueAsString());
    }
}
