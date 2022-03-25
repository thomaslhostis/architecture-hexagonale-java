package com.github.thomaslhostis.architecturehexagonalejava.core.infrastructure.mongo.trainstations;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoTrainStationsRepository extends MongoRepository<TrainStationDocument, String> {
    Boolean existsByCode(String trainStationCode);

    Optional<TrainStationDocument> findByCode(String trainStationCode);

    void deleteByCode(String trainStationCode);
}
