package com.architecturehexagonale.tests.functionaltests.glue.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Component
public class TestContext {
    private ResponseEntity<?> responseEntity;

    public void setResponseEntity(ResponseEntity<?> responseEntity) {
        this.responseEntity = responseEntity;
    }

    public <T> T getResponseBody(Class<T> responseType) {
        return responseType.cast(responseEntity.getBody());
    }

    public <T> T[] getResponseBodyAsArray() {
        return (T[]) responseEntity.getBody();
    }

    public <T> List<T> getResponseBodyAsList() {
        return Arrays.asList(getResponseBodyAsArray());
    }

    public void assertOk() {
        assertStatus(OK);
    }

    public void assertCreated() {
        assertStatus(CREATED);
    }

    public void assertStatus(HttpStatus expectedHttpStatus) {
        assertStatus(expectedHttpStatus, null);
    }

    public void assertStatus(HttpStatus expectedHttpStatus, @Nullable String expectedMessage) {
        assertEquals(expectedHttpStatus, responseEntity.getStatusCode());
        if (expectedMessage != null) {
            assertEquals(expectedMessage, responseEntity.getBody());
        }
    }

    public static Class getResponseType(String tryTo, Class defaultResponseType) {
        return tryTo == null ? defaultResponseType : String.class;
    }
}
