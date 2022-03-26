package com.architecturehexagonale.tests.functionaltests.glue.trainstations.steps;

import com.architecturehexagonale.presentation.io.trainstations.TrainStationInput;
import com.architecturehexagonale.tests.functionaltests.glue.commons.TestContext;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.TrainStationsClient;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.builders.TrainStationBuilder;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.builders.TrainStationBuilderHistory;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Lorsque;
import io.cucumber.java.fr.Étantdonnée;
import io.cucumber.java8.Fr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class CreerUneGare implements Fr {
    @Autowired
    private TrainStationBuilder trainStationBuilder;
    @Autowired
    private TrainStationBuilderHistory trainStationBuilderHistory;
    @Autowired
    private TestContext testContext;
    @Autowired
    private TrainStationsClient trainStationsClient;

    private void createTrainStation() {
        createTrainStation(null);
    }

    private void createTrainStation(String tryTo) {
        TrainStationInput trainStationInput = trainStationBuilder.buildTrainStationInput();
        ResponseEntity<?> responseEntity = trainStationsClient.createTrainStation(trainStationInput, tryTo);
        testContext.setResponseEntity(responseEntity);
        if (tryTo == null) {
            trainStationBuilderHistory.addTrainStationBuilder(trainStationBuilder);
        }
    }

    @Étantdonnée(
            "^(?:l'existence d')?une gare" +
                    "( à créer)?" +
                    "(?: avec le libellé \"([^\"]*)\")?" +
                    "(?: (?:avec|et)? le code \"([^\"]*)\")?$"
    )
    public void uneGare(
            String toCreate,
            String trainStationLabel,
            String trainStationCode
    ) {
        trainStationBuilder.reset();
        if (trainStationLabel != null) {
            trainStationBuilder.withLabel(trainStationLabel);
        }
        if (trainStationCode != null) {
            trainStationBuilder.withCode(trainStationCode);
        }
        if (toCreate == null) {
            createTrainStation();
        }
    }

    @Lorsque("^je( tente de)? créer? cette gare$")
    public void jeCreeCetteGare(String tryTo) {
        createTrainStation(tryTo);
    }

    @Alors("cette gare est créée avec succès")
    public void cetteGareEstCreeeAvecSucces() {
        testContext.assertCreated();
    }
}
