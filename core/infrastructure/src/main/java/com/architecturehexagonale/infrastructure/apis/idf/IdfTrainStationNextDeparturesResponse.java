package com.architecturehexagonale.infrastructure.apis.idf;

import com.architecturehexagonale.domain.trainstations.views.TrainStationWithNextDepartures;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class IdfTrainStationNextDeparturesResponse {
    @JsonProperty("next_departures")
    @Nullable
    private List<NextDeparture> nextDepartures;

    public IdfTrainStationNextDeparturesResponse() {
    }

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

    public static class NextDeparture {
        @JsonProperty("destination")
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
