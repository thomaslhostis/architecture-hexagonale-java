package com.architecturehexagonale.tests.functionaltests.glue.trainstations;

import com.architecturehexagonale.presentation.io.trainstations.TrainStationInput;
import com.architecturehexagonale.presentation.io.trainstations.TrainStationOutput;
import com.architecturehexagonale.presentation.io.trainstations.TrainStationWithNextDeparturesOutput;
import com.architecturehexagonale.tests.functionaltests.glue.trainstations.builders.TrainStationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static com.architecturehexagonale.tests.functionaltests.glue.commons.TestContext.getResponseType;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.MockRestServiceServer.createServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Component
public class TrainStationsClient {
    @Autowired
    private RestTemplate testRestTemplate;
    @Autowired
    private RestTemplate idfTrainStationsRestTemplate;
    @Autowired
    private TrainStationBuilder trainStationBuilder;

    public ResponseEntity<?> createTrainStation(
            TrainStationInput trainStationInput,
            String tryTo
    ) {
        return testRestTemplate.postForEntity(
                "/api/train-station",
                trainStationInput,
                getResponseType(tryTo, HttpStatus.class)
        );
    }

    public ResponseEntity<TrainStationWithNextDeparturesOutput> getTrainStationWithNextDepartures(String trainStationCode) {
        MockRestServiceServer idfMockServer = createServer(idfTrainStationsRestTemplate);
        String jsonResponse = trainStationBuilder.buildIdfTrainStationNextDeparturesJsonResponse();
        idfMockServer.expect(requestTo("https://api.idf.fr/gares?code=" + trainStationCode))
                .andRespond(withSuccess(jsonResponse, APPLICATION_JSON));

        return testRestTemplate.getForEntity(
                "/api/train-station-with-next-departures?train_station_code=" + trainStationCode,
                TrainStationWithNextDeparturesOutput.class
        );
    }

    public ResponseEntity<?> updateTrainStation(
            TrainStationInput trainStationInput,
            String tryTo
    ) {
        return testRestTemplate.exchange(
                "/api/train-station",
                PUT,
                new HttpEntity<>(trainStationInput),
                getResponseType(tryTo, HttpStatus.class)
        );
    }

    public ResponseEntity<TrainStationOutput[]> getAllTrainStations() {
        return testRestTemplate.getForEntity(
                "/api/train-stations",
                TrainStationOutput[].class
        );
    }

    public ResponseEntity<?> deleteTrainStation(
            String trainStationCode,
            String tryTo
    ) {
        return testRestTemplate.exchange(
                "/api/train-station?train_station_code=" + trainStationCode,
                DELETE,
                null,
                getResponseType(tryTo, HttpStatus.class)
        );
    }
}
