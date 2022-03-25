package com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.exceptions;

import com.github.thomaslhostis.architecturehexagonalejava.core.domain.exceptions.NotFoundException;

public class TrainStationNotFoundException extends NotFoundException {

    public TrainStationNotFoundException(String trainStationCode) {
        super("La station de train " + trainStationCode + " n'existe pas");
    }
}
