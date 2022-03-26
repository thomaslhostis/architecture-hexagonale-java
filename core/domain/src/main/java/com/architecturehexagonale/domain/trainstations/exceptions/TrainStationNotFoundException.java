package com.architecturehexagonale.domain.trainstations.exceptions;

import com.architecturehexagonale.domain.exceptions.NotFoundException;

public class TrainStationNotFoundException extends NotFoundException {

    public TrainStationNotFoundException(String trainStationCode) {
        super("La station de train " + trainStationCode + " n'existe pas");
    }
}
