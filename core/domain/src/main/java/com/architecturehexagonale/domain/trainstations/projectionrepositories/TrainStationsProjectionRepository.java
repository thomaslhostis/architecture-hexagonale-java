package com.architecturehexagonale.domain.trainstations.projectionrepositories;

import com.architecturehexagonale.domain.trainstations.entities.TrainStation;

import java.util.List;
import java.util.Optional;

public interface TrainStationsProjectionRepository {

    Boolean existsByCode(String trainStationCode);

    void createTrainStation(TrainStation trainStation);

    Optional<TrainStation> findByCode(String trainStationCode);

    void updateTrainStation(TrainStation trainStation);

    List<TrainStation> findAllTrainStations();

    void deleteTrainStation(String trainStationCode);
}
