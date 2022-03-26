package com.architecturehexagonale.tests.functionaltests.glue.trainstations.builders;

import com.architecturehexagonale.infrastructure.apis.idf.IdfTrainStationNextDeparturesResponse;
import com.architecturehexagonale.presentation.io.trainstations.TrainStationInput;
import com.architecturehexagonale.presentation.io.trainstations.TrainStationOutput;
import com.architecturehexagonale.presentation.io.trainstations.TrainStationWithNextDeparturesOutput;
import com.architecturehexagonale.tests.functionaltests.glue.commons.TestJackson;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainStationBuilder implements Serializable {
    private String code;
    private String label;
    private List<NextDeparture> nextDepartures;

    public String getCode() {
        return code;
    }

    private static class NextDeparture {
        private final String destination;
        private final LocalDateTime departureTime;

        private NextDeparture(String destination, LocalDateTime departureTime) {
            this.destination = destination;
            this.departureTime = departureTime;
        }
    }

    public TrainStationBuilder reset() {
        code = "TRN_ABC";
        label = "Station Abc";
        nextDepartures = new ArrayList<>();
        return this;
    }

    public TrainStationBuilder withCode(String code) {
        this.code = code;
        return this;
    }

    public TrainStationBuilder withLabel(String label) {
        this.label = label;
        return this;
    }

    public TrainStationBuilder withNextDeparture(
            String destination,
            Integer departureHour
    ) {
        LocalDateTime departureTime = LocalDateTime.now()
                .withHour(departureHour)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
        nextDepartures.add(
                new NextDeparture(
                        destination,
                        departureTime
                )
        );
        return this;
    }

    public TrainStationInput buildTrainStationInput() {
        return new TrainStationInput(
                code,
                label
        );
    }

    public TrainStationOutput buildTrainStationOutput() {
        return new TrainStationOutput(
                code,
                label
        );
    }

    public TrainStationWithNextDeparturesOutput buildTrainStationWithNextDeparturesOutput() {
        return new TrainStationWithNextDeparturesOutput(
                code,
                label,
                nextDepartures.stream().map(nextDeparture ->
                        new TrainStationWithNextDeparturesOutput.NextDeparture(
                                nextDeparture.destination,
                                nextDeparture.departureTime
                        )
                ).collect(Collectors.toList())
        );
    }

    public String buildIdfTrainStationNextDeparturesJsonResponse() {
        IdfTrainStationNextDeparturesResponse idfTrainStationNextDeparturesResponse = new IdfTrainStationNextDeparturesResponse(
                nextDepartures.stream().map(nextDeparture ->
                        new IdfTrainStationNextDeparturesResponse.NextDeparture(
                                nextDeparture.destination,
                                nextDeparture.departureTime
                        )
                ).collect(Collectors.toList())
        );
        return TestJackson.write(idfTrainStationNextDeparturesResponse);
    }
}
