package com.github.thomaslhostis.architecturehexagonalejava.core.presentation.io.trainstations;

import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.entities.TrainStation;

public class TrainStationOutput {
    private final String code;
    private final String label;

    public TrainStationOutput(TrainStation trainStation) {
        code = trainStation.getCode();
        label = trainStation.getLabel();
    }
}
