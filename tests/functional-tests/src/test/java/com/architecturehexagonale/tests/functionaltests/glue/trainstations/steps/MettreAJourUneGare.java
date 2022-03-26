package com.architecturehexagonale.tests.functionaltests.glue.trainstations.steps;

import com.architecturehexagonale.presentation.io.trainstations.TrainStationInput;
import com.architecturehexagonale.tests.functionaltests.glue.commons.TestContext;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.TrainStationsClient;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.builders.TrainStationBuilder;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Lorsque;
import io.cucumber.java8.Fr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class MettreAJourUneGare implements Fr {
    @Autowired
    private TrainStationBuilder trainStationBuilder;
    @Autowired
    private TestContext testContext;
    @Autowired
    private TrainStationsClient trainStationsClient;

    private void updateTrainStation(String tryTo) {
        TrainStationInput trainStationInput = trainStationBuilder.buildTrainStationInput();
        ResponseEntity<?> responseEntity = trainStationsClient.updateTrainStation(trainStationInput, tryTo);
        testContext.setResponseEntity(responseEntity);
    }

    @Lorsque("^je mets à jour cette gare avec le libellé \"([^\"]*)\"$")
    public void jeMetsAJourCetteGare(String trainStationLabel) {
        trainStationBuilder.withLabel(trainStationLabel);
        updateTrainStation(null);
    }

    @Lorsque("^je tente de mettre à jour la gare \"([^\"]*)\"$")
    public void jeTenteDeMettreAJourLaGare(String trainStationCode) {
        trainStationBuilder.withCode(trainStationCode);
        updateTrainStation("should fail");
    }

    @Alors("cette gare est mise à jour avec succès")
    public void cetteGareEstMiseAJourAvecSucces() {
        testContext.assertOk();
    }
}
