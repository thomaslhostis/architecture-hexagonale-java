package com.architecturehexagonale.tests.functionaltests.glue.trainstations.steps;

import com.architecturehexagonale.presentation.io.trainstations.TrainStationOutput;
import com.architecturehexagonale.tests.functionaltests.glue.commons.TestContext;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.TrainStationsClient;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.builders.TrainStationBuilderHistory;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Lorsque;
import io.cucumber.java8.Fr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecupererLesGares implements Fr {
    @Autowired
    private TrainStationBuilderHistory trainStationBuilderHistory;
    @Autowired
    private TestContext testContext;
    @Autowired
    private TrainStationsClient trainStationsClient;

    @Lorsque("je récupère la liste des gares")
    public void jeRecupereLaListeDesGares() {
        ResponseEntity<TrainStationOutput[]> responseEntity = trainStationsClient.getAllTrainStations();
        testContext.setResponseEntity(responseEntity);
    }

    @Alors("je reçois toutes les gares")
    public void jeRecoisToutesLesGares() {
        testContext.assertOk();
        List<TrainStationOutput> expectedTrainStationOutputs = trainStationBuilderHistory.buildTrainStationOutputs();
        List<TrainStationOutput> actualTrainStationOutputs = testContext.getResponseBodyAsList();
        assertEquals(expectedTrainStationOutputs, actualTrainStationOutputs);
    }
}
