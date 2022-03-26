package com.architecturehexagonale.presentation.io.trainstations;

import com.architecturehexagonale.domain.trainstations.views.TrainStationWithNextDepartures;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TrainStationWithNextDeparturesOutput {
    private String code;
    private String label;
    private List<NextDeparture> nextDepartures;

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
        private String destination;
        @Nullable
        private LocalDateTime departureTime;

        NextDeparture(TrainStationWithNextDepartures.NextDeparture nextDeparture) {
            destination = nextDeparture.getDestination();
            departureTime = nextDeparture.getDepartureTime();
        }

        // Tout ce qu'il y a sous cette ligne est nécessaire aux tests fonctionnels
        // (sérialisation, comparaison, builders, etc.). Ça fait beaucoup. Une solution
        // serait d'utiliser Lombok, une version plus récente de Java ou Kotlin

        @Nullable
        public String getDestination() {
            return destination;
        }

        @Nullable
        public LocalDateTime getDepartureTime() {
            return departureTime;
        }

        public NextDeparture() {
        }

        public NextDeparture(
                @Nullable String destination,
                @Nullable LocalDateTime departureTime
        ) {
            this.destination = destination;
            this.departureTime = departureTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NextDeparture that = (NextDeparture) o;
            return Objects.equals(destination, that.destination) && Objects.equals(departureTime, that.departureTime);
        }
    }

    public TrainStationWithNextDeparturesOutput() {
    }

    public TrainStationWithNextDeparturesOutput(String code, String label, List<NextDeparture> nextDepartures) {
        this.code = code;
        this.label = label;
        this.nextDepartures = nextDepartures;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public List<NextDeparture> getNextDepartures() {
        return nextDepartures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainStationWithNextDeparturesOutput that = (TrainStationWithNextDeparturesOutput) o;
        return Objects.equals(code, that.code) && Objects.equals(label, that.label) && Objects.equals(nextDepartures, that.nextDepartures);
    }
}
