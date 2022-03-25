package com.github.thomaslhostis.architecturehexagonalejava.core.infrastructure.apis.idf;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.views.TrainStationWithNextDepartures;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class IdfTrainStationNextDeparturesResponse {
    @JsonProperty("next_departures")
    @Nullable
    private final List<NextDeparture> nextDepartures;

    public IdfTrainStationNextDeparturesResponse(@Nullable List<NextDeparture> nextDepartures) {
        this.nextDepartures = nextDepartures;
    }

    public List<TrainStationWithNextDepartures.NextDeparture> toDomainNextDepartures() {
        return Optional.ofNullable(nextDepartures)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(nextDeparture -> new TrainStationWithNextDepartures.NextDeparture(
                        nextDeparture.destination,
                        nextDeparture.departureTime
                )).collect(Collectors.toList());
    }

    static class NextDeparture {
        @Nullable
        private final String destination;
        @JsonProperty("departure_time")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        @Nullable
        private final LocalDateTime departureTime;

        public NextDeparture(
                @Nullable String destination,
                @Nullable LocalDateTime departureTime
        ) {
            this.destination = destination;
            this.departureTime = departureTime;
        }
    }
}
