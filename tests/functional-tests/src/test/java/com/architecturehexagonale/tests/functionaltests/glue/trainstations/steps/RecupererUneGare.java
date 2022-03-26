package com.architecturehexagonale.tests.functionaltests.glue.trainstations.steps;

import com.architecturehexagonale.presentation.io.trainstations.TrainStationWithNextDeparturesOutput;
import com.architecturehexagonale.tests.functionaltests.glue.commons.TestContext;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.TrainStationsClient;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.builders.TrainStationBuilder;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Lorsque;
import io.cucumber.java.fr.Étantdonné;
import io.cucumber.java8.Fr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecupererUneGare implements Fr {
    @Autowired
    private TrainStationBuilder trainStationBuilder;
    @Autowired
    private TestContext testContext;
    @Autowired
    private TrainStationsClient trainStationsClient;

    @Étantdonné("^le prochain départ pour \"([^\"]*)\" est à (\\d{1,2})h$")
    public void leProchainDepart(
            String destination,
            Integer departureHour
    ) {
        trainStationBuilder.withNextDeparture(destination, departureHour);
    }

    @Lorsque("je récupère les informations et prochains départs de cette gare")
    public void jeRecupereLesInformationsEtProchainsDepartsDeCetteGare() {
        ResponseEntity<TrainStationWithNextDeparturesOutput> responseEntity =
                trainStationsClient.getTrainStationWithNextDepartures(trainStationBuilder.getCode());
        testContext.setResponseEntity(responseEntity);
    }

    @Alors("je reçois les informations et prochains départs de cette gare")
    public void jeRecoisLesInformationsEtProchainsDepartsDeCetteGare() {
        testContext.assertOk();
        TrainStationWithNextDeparturesOutput expectedTrainStationWithNextDeparturesOutput =
                trainStationBuilder.buildTrainStationWithNextDeparturesOutput();
        TrainStationWithNextDeparturesOutput actualTrainStationWithNextDeparturesOutput =
                testContext.getResponseBody(TrainStationWithNextDeparturesOutput.class);
        assertEquals(expectedTrainStationWithNextDeparturesOutput, actualTrainStationWithNextDeparturesOutput);
    }
}
