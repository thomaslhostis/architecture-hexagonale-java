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
        if (!code.startsWith(CODE_PREFIX)) {
            throw new IllegalArgumentException("Le code de la station de train doit commencer par " + CODE_PREFIX);
        }
    }
}
