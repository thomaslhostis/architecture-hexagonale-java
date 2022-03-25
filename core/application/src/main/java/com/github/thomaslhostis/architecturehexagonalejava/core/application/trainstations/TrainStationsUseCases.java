package com.github.thomaslhostis.architecturehexagonalejava.core.application.trainstations;

import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.entities.TrainStation;
import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.exceptions.TrainStationNotFoundException;
import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.projectionrepositories.TrainStationsProjectionRepository;
import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.services.TrainStationsService;
import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.views.TrainStationWithNextDepartures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrainStationsUseCases {
    private final TrainStationsProjectionRepository trainStationsProjectionRepository;
    private final TrainStationsService trainStationsService;

    @Autowired
    public TrainStationsUseCases(
            TrainStationsProjectionRepository trainStationsProjectionRepository,
            TrainStationsService trainStationsService
    ) {
        this.trainStationsProjectionRepository = trainStationsProjectionRepository;
        this.trainStationsService = trainStationsService;
    }

    public void createTrainStation(TrainStation trainStation) {
        trainStation.validate();
        if (trainStationsProjectionRepository.existsByCode(trainStation.getCode())) {
            throw new IllegalArgumentException("La station de train " + trainStation.getCode() + " existe déjà");
        }
        trainStationsProjectionRepository.createTrainStation(trainStation);
    }

    public TrainStationWithNextDepartures findTrainStationWithNextDepartures(String trainStationCode) {
        TrainStation trainStation = trainStationsProjectionRepository.findByCode(trainStationCode)
                .orElseThrow(() -> new TrainStationNotFoundException(trainStationCode));

        List<TrainStationWithNextDepartures.NextDeparture> nextDepartures =
                trainStationsService.findTrainStationNextDepartures(trainStationCode);

        return new TrainStationWithNextDepartures(
                trainStation.getCode(),
                trainStation.getLabel(),
                nextDepartures
        );
    }

    public void updateTrainStation(TrainStation trainStation) {
        trainStation.validate();
        if (!trainStationsProjectionRepository.existsByCode(trainStation.getCode())) {
            throw new TrainStationNotFoundException(trainStation.getCode());
        }
        trainStationsProjectionRepository.updateTrainStation(trainStation);
    }

    public void deleteTrainStation(String trainStationCode) {
        if (!trainStationsProjectionRepository.existsByCode(trainStationCode)) {
            throw new TrainStationNotFoundException(trainStationCode);
        }
        trainStationsProjectionRepository.deleteTrainStation(trainStationCode);
    }
}
