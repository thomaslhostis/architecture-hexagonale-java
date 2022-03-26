package com.architecturehexagonale.presentation.io.trainstations;

import com.architecturehexagonale.domain.trainstations.entities.TrainStation;

import javax.validation.constraints.NotEmpty;

public class TrainStationInput {
    @NotEmpty
    private final String code;
    @NotEmpty
    private final String label;

    public TrainStation toDomainInstance() {
        return new TrainStation(
                code,
                label
        );
    }

    // Pour les tests fonctionnels

    public TrainStationInput(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

}
