package com.architecturehexagonale.presentation.io.trainstations;

import com.architecturehexagonale.domain.trainstations.entities.TrainStation;

public class TrainStationOutput {
    private final String code;
    private final String label;

    public TrainStationOutput(TrainStation trainStation) {
        code = trainStation.getCode();
        label = trainStation.getLabel();
    }
}
