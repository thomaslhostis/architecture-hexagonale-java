package com.architecturehexagonale.domain.trainstations.entities;

public class TrainStation {
    private final String code;
    private final String label;

    private static final String CODE_PREFIX = "TRN_";

    public TrainStation(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public void validate() {
        //TODO
    }
}
