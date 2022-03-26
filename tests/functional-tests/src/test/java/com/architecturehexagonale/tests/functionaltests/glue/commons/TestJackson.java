package com.architecturehexagonale.tests.functionaltests.glue.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class TestJackson {

    public static String write(Object value) {
        try {
            return new ObjectMapper()
                    // Les deux prochaines lignes permettent de sérialiser
                    // les dates au format ISO au lieu du format timestamp
                    .registerModule(new JavaTimeModule())
                    .disable(WRITE_DATES_AS_TIMESTAMPS)
                    .writeValueAsString(value);
        } catch (JsonProcessingException jsonProcessingException) {
            throw new RuntimeException("Erreur lors de la sérialisation : " + jsonProcessingException);
        }
    }
}
