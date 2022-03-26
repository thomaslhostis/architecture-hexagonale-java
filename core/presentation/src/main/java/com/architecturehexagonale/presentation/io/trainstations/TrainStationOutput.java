package com.architecturehexagonale.presentation.io.trainstations;

import com.architecturehexagonale.domain.trainstations.entities.TrainStation;

import java.util.Objects;

public class TrainStationOutput {
    private String code;
    private String label;

    public TrainStationOutput(TrainStation trainStation) {
        code = trainStation.getCode();
        label = trainStation.getLabel();
    }

    // Pour les tests fonctionnels

    public TrainStationOutput() {
    }

    public TrainStationOutput(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainStationOutput that = (TrainStationOutput) o;
        return Objects.equals(code, that.code) && Objects.equals(label, that.label);
    }
}
