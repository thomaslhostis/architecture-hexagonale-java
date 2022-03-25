package com.github.thomaslhostis.architecturehexagonalejava.core.presentation.io.trainstations;

import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.entities.TrainStation;

import javax.validation.constraints.NotEmpty;

public class TrainStationInput {
    @NotEmpty
    private final String code;
    @NotEmpty
    private final String label;

    public TrainStationInput(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public TrainStation toDomainInstance() {
        return new TrainStation(
                code,
                label
        );
    }
}
