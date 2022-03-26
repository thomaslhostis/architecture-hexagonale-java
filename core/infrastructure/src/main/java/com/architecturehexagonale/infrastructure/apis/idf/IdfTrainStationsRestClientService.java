package com.architecturehexagonale.infrastructure.apis.idf;

import com.architecturehexagonale.domain.trainstations.services.TrainStationsService;
import com.architecturehexagonale.domain.trainstations.views.TrainStationWithNextDepartures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class IdfTrainStationsRestClientService implements TrainStationsService {
    private final RestTemplate idfTrainStationsRestTemplate;

    @Autowired
    public IdfTrainStationsRestClientService(RestTemplate idfTrainStationsRestTemplate) {
        this.idfTrainStationsRestTemplate = idfTrainStationsRestTemplate;
    }

    @Override
    public List<TrainStationWithNextDepartures.NextDeparture> findTrainStationNextDepartures(String trainStationCode) {

        ResponseEntity<IdfTrainStationNextDeparturesResponse> idfNextDeparturesResponse = idfTrainStationsRestTemplate.getForEntity(
                "https://api.idf.fr/gares?code=$trainStationCode",
                IdfTrainStationNextDeparturesResponse.class
        );

        return Optional.ofNullable(idfNextDeparturesResponse.getBody())
                .map(IdfTrainStationNextDeparturesResponse::toDomainNextDepartures)
                .orElseGet(Collections::emptyList);
    }
}
