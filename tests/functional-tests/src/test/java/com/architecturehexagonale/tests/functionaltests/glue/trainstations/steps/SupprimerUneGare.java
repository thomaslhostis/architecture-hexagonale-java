package com.architecturehexagonale.tests.functionaltests.glue.trainstations.steps;

import com.architecturehexagonale.tests.functionaltests.glue.commons.TestContext;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.TrainStationsClient;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.builders.TrainStationBuilder;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Lorsque;
import io.cucumber.java8.Fr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class SupprimerUneGare implements Fr {
    @Autowired
    private TrainStationBuilder trainStationBuilder;
    @Autowired
    private TestContext testContext;
    @Autowired
    private TrainStationsClient trainStationsClient;

    @Lorsque("je supprime cette gare")
    public void jeSupprimeCetteGare() {
        ResponseEntity<?> responseEntity = trainStationsClient.deleteTrainStation(trainStationBuilder.getCode(), null);
        testContext.setResponseEntity(responseEntity);
    }

    @Lorsque("^je tente de supprimer la gare \"([^\"]*)\"$")
    public void jeTenteDeSupprimerLaGare(String trainStationCode) {
        ResponseEntity<?> responseEntity = trainStationsClient.deleteTrainStation(trainStationCode, "should fail");
        testContext.setResponseEntity(responseEntity);
    }

    @Alors("cette gare est supprimée avec succès")
    public void cetteGareEstSupprimeeAvecSuccess() {
        testContext.assertOk();
    }
}
