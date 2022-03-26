package com.architecturehexagonale.tests.functionaltests.glue.trainstations.builders;

import com.architecturehexagonale.presentation.io.trainstations.TrainStationOutput;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainStationBuilderHistory {
    private List<TrainStationBuilder> trainStationBuilders;

    public void reset() {
        trainStationBuilders = new ArrayList<>();
    }

    public void addTrainStationBuilder(TrainStationBuilder trainStationBuilder) {
        trainStationBuilders.add(SerializationUtils.clone(trainStationBuilder));
    }

    public List<TrainStationOutput> buildTrainStationOutputs() {
        return trainStationBuilders.stream()
                .map(TrainStationBuilder::buildTrainStationOutput)
                .collect(Collectors.toList());
    }
}
