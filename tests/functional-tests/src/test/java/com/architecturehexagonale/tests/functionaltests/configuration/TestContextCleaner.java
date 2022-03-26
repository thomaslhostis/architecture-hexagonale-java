package com.architecturehexagonale.tests.functionaltests.configuration;

import com.architecturehexagonale.tests.functionaltests.glue.trainstations.builders.TrainStationBuilder;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.builders.TrainStationBuilderHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class TestContextCleaner {
    @Autowired
    private RestTemplate testRestTemplate;
    @Autowired
    private TrainStationBuilder trainStationBuilder;
    @Autowired
    private TrainStationBuilderHistory trainStationBuilderHistory;

    public void reset() {
        testRestTemplate.setInterceptors(Collections.emptyList());
        trainStationBuilder.reset();
        trainStationBuilderHistory.reset();
    }
}
