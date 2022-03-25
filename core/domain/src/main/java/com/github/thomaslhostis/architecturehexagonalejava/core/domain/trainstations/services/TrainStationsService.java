package com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.services;

import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.views.TrainStationWithNextDepartures;

import java.util.List;

public interface TrainStationsService {
    List<TrainStationWithNextDepartures.NextDeparture> findTrainStationNextDepartures(String trainStationCode);
}
