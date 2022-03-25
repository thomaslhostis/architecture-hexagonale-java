package com.github.thomaslhostis.architecturehexagonalejava.core.infrastructure.mongo.trainstations;

import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.entities.TrainStation;
import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.exceptions.TrainStationNotFoundException;
import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.projectionrepositories.TrainStationsProjectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MongoTrainStationsProjectionRepository implements TrainStationsProjectionRepository {
    private final MongoTrainStationsRepository trainStationsRepository;

    @Autowired
    public MongoTrainStationsProjectionRepository(MongoTrainStationsRepository trainStationsRepository) {
        this.trainStationsRepository = trainStationsRepository;
    }

    @Override
    public Boolean existsByCode(String trainStationCode) {
        return trainStationsRepository.existsByCode(trainStationCode);
    }

    @Override
    public void createTrainStation(TrainStation trainStation) {
        TrainStationDocument trainStationDocument = new TrainStationDocument(trainStation);
        trainStationsRepository.save(trainStationDocument);
    }

    @Override
    public Optional<TrainStation> findByCode(String trainStationCode) {
        return trainStationsRepository.findByCode(trainStationCode)
                .map(TrainStationDocument::toDomainInstance);
    }

    @Override
    public void updateTrainStation(TrainStation trainStation) {
        TrainStationDocument existingTrainStation = trainStationsRepository.findByCode(trainStation.getCode())
                .orElseThrow(() -> new TrainStationNotFoundException(trainStation.getCode()));
        existingTrainStation.setLabel(trainStation.getLabel());
        trainStationsRepository.save(existingTrainStation);
    }

    @Override
    public List<TrainStation> findAllTrainStations() {
        return trainStationsRepository.findAll()
                .stream()
                .map(TrainStationDocument::toDomainInstance)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTrainStation(String trainStationCode) {
        trainStationsRepository.deleteByCode(trainStationCode);
    }
}
