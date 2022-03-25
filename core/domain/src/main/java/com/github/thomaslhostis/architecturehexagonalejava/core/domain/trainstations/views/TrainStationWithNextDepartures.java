package com.github.thomaslhostis.architecturehexagonalejava.core.domain.trainstations.views;

import java.time.LocalDateTime;
import java.util.List;

public class TrainStationWithNextDepartures {
    private final String code;

    private final String label;
    private final List<NextDeparture> nextDepartures;

    public TrainStationWithNextDepartures(String code, String label, List<NextDeparture> nextDepartures) {
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

    public static class NextDeparture {
        private final String destination;
        private final LocalDateTime departureTime;

        public NextDeparture(String destination, LocalDateTime departureTime) {
            this.destination = destination;
            this.departureTime = departureTime;
        }

        public String getDestination() {
            return destination;
        }

        public LocalDateTime getDepartureTime() {
            return departureTime;
        }
    }
}
