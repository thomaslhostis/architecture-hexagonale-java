package com.architecturehexagonale.presentation.controllers;

import com.architecturehexagonale.application.trainstations.TrainStationsUseCases;
import com.architecturehexagonale.domain.trainstations.entities.TrainStation;
import com.architecturehexagonale.domain.trainstations.projectionrepositories.TrainStationsProjectionRepository;
import com.architecturehexagonale.domain.trainstations.views.TrainStationWithNextDepartures;
import com.architecturehexagonale.presentation.io.trainstations.TrainStationInput;
import com.architecturehexagonale.presentation.io.trainstations.TrainStationOutput;
import com.architecturehexagonale.presentation.io.trainstations.TrainStationWithNextDeparturesOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class TrainStationsController {
    private final TrainStationsUseCases trainStationsUseCases;
    private final TrainStationsProjectionRepository trainStationsProjectionRepository;

    @Autowired
    public TrainStationsController(
            TrainStationsUseCases trainStationsUseCases,
            TrainStationsProjectionRepository trainStationsProjectionRepository
    ) {
        this.trainStationsUseCases = trainStationsUseCases;
        this.trainStationsProjectionRepository = trainStationsProjectionRepository;
    }

    @PostMapping(value = "/api/train-station", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> createTrainStation(
            @RequestBody TrainStationInput trainStationInput
    ) {
        TrainStation trainStation = trainStationInput.toDomainInstance();
        trainStationsUseCases.createTrainStation(trainStation);
        return new ResponseEntity(CREATED);
    }

    @GetMapping("/api/train-station-with-next-departures")
    public ResponseEntity<TrainStationWithNextDeparturesOutput> getStationWithNextDepartures(
            @RequestParam("train_station_code") String trainStationCode
    ) {
        TrainStationWithNextDepartures trainStationWithNextDepartures =
                trainStationsUseCases.findTrainStationWithNextDepartures(trainStationCode);
        TrainStationWithNextDeparturesOutput trainStationWithNextDeparturesOutput =
                new TrainStationWithNextDeparturesOutput(trainStationWithNextDepartures);
        return ResponseEntity.ok(trainStationWithNextDeparturesOutput);
    }

    @PutMapping("/api/train-station")
    public ResponseEntity<HttpStatus> updateTrainStation(
            @RequestBody TrainStationInput trainStationInput
    ) {
        TrainStation trainStation = trainStationInput.toDomainInstance();
        trainStationsUseCases.updateTrainStation(trainStation);
        return new ResponseEntity(OK);
    }

    @GetMapping("/api/train-stations")
    public ResponseEntity<List<TrainStationOutput>> getAllTrainStations() {
        List<TrainStation> allTrainStations = trainStationsProjectionRepository.findAllTrainStations();
        List<TrainStationOutput> allTrainStationOutputs = allTrainStations.stream()
                .map(TrainStationOutput::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(allTrainStationOutputs);
    }

    @DeleteMapping("/api/train-station")
    public ResponseEntity<HttpStatus> deleteTrainStation(
            @RequestParam("train_station_code") String trainStationCode
    ) {
        trainStationsUseCases.deleteTrainStation(trainStationCode);
        return new ResponseEntity(OK);
    }
}
