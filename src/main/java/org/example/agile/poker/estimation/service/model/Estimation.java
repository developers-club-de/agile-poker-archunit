package org.example.agile.poker.estimation.service.model;

import lombok.Data;

@Data
public class Estimation {
    private final String value;

    public static Estimation createFromString(String value) {
        return new Estimation(value);
    }

}
