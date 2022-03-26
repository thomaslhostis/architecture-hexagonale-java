package com.architecturehexagonale.presentation.controllers;

import com.architecturehexagonale.application.trainstations.TrainStationsUseCases;
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
        throw new RuntimeException("Not yet implemented");
        // 1. Transformer l'input en objet du domaine
        // 2. Utiliser `trainStationsUseCases` pour gérer la création
        // 3. Retourner ResponseEntity(OK)
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
        throw new RuntimeException("Not yet implemented");
        // 1. Transformer l'input en objet du domaine
        // 2. Utiliser `trainStationsUseCases` pour mettre à jour
        // 3. Retourner ResponseEntity(OK)
    }

    @GetMapping("/api/train-stations")
    public ResponseEntity<List<TrainStationOutput>> getAllTrainStations() {
        throw new RuntimeException("Not yet implemented");
        // 1. Utiliser `trainStationsProjectionRepository` directement pour récupérer les gares
        // 2. Convertir au format de sortie
        // 3. Retourner le résultat
    }

    //TODO Bonus : suppression d'une gare
}
