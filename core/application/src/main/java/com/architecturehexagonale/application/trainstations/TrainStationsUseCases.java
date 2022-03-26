package com.architecturehexagonale.application.trainstations;

import com.architecturehexagonale.domain.trainstations.entities.TrainStation;
import com.architecturehexagonale.domain.trainstations.projectionrepositories.TrainStationsProjectionRepository;
import com.architecturehexagonale.domain.trainstations.services.TrainStationsService;
import com.architecturehexagonale.domain.trainstations.views.TrainStationWithNextDepartures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        throw new RuntimeException("Not yet implemented");
        // 1. Récupérer la gare stockée en base de données à partir du code
        // 2. Vérifier qu'elle existe, sinon `TrainStationNotFoundException`
        // 3. Appeler le service partenaire pour récupérer les prochains départs de cette gare
        // 4. Agréger et retourner le tout
    }

    public void updateTrainStation(TrainStation trainStation) {
        throw new RuntimeException("Not yet implemented");
        // 1. Vérifier que le format du code de gare commence par "TRN_"
        // 2. Vérifier que la gare existe à partir du code
        // 3. Utiliser `trainStationsProjectionRepository` pour mettre à jour
    }
}
