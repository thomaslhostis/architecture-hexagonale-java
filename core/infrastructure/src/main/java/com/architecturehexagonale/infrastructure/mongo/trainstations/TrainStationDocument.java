package com.architecturehexagonale.infrastructure.mongo.trainstations;

import com.architecturehexagonale.domain.trainstations.entities.TrainStation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("trains_station")
public class TrainStationDocument {
    @Id
    private String id;
    private String code;
    private String label;

    public TrainStationDocument() {
    }

    public TrainStationDocument(TrainStation trainStation) {
        id = null;
        code = trainStation.getCode();
        label = trainStation.getLabel();
    }

    public TrainStation toDomainInstance() {
        return new TrainStation(
                code,
                label
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
