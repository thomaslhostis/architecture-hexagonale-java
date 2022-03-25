package com.github.thomaslhostis.architecturehexagonalejava.core.presentation.io.trainstations;

import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.views.TrainStationWithNextDepartures;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TrainStationWithNextDeparturesOutput {
    private final String code;
    private final String label;
    private final List<NextDeparture> nextDepartures;

    public TrainStationWithNextDeparturesOutput(TrainStationWithNextDepartures trainStationWithNextDepartures) {
        code = trainStationWithNextDepartures.getCode();
        label = trainStationWithNextDepartures.getLabel();
        nextDepartures = trainStationWithNextDepartures.getNextDepartures()
                .stream()
                .map(NextDeparture::new)
                .collect(Collectors.toList());
    }

    public static class NextDeparture {
        @Nullable
        private final String destination;
        @Nullable
        private final LocalDateTime departureTime;

        NextDeparture(TrainStationWithNextDepartures.NextDeparture nextDeparture) {
            destination = nextDeparture.getDestination();
            departureTime = nextDeparture.getDepartureTime();
        }
    }
}
