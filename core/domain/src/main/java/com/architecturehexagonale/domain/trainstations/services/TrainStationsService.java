package com.architecturehexagonale.domain.trainstations.services;

import com.architecturehexagonale.domain.trainstations.views.TrainStationWithNextDepartures;

import java.util.List;

public interface TrainStationsService {
    List<TrainStationWithNextDepartures.NextDeparture> findTrainStationNextDepartures(String trainStationCode);
}
