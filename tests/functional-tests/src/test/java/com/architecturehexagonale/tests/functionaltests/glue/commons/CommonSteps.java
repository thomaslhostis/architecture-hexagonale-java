package com.architecturehexagonale.tests.functionaltests.glue.commons;

import io.cucumber.java8.Fr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class CommonSteps implements Fr {
    @Autowired
    private TestContext testContext;

    public CommonSteps() {
        Alors("^je reçois une (?:erreur|réponse) (\\d+)(?: avec le message \"([^\"]*)\")?$",
                (Integer httpCode, String message) -> {
                    HttpStatus httpStatus = HttpStatus.valueOf(httpCode);
                    testContext.assertStatus(httpStatus);
                }
        );
    }
}
