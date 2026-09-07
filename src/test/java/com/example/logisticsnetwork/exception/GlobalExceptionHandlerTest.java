package com.example.logisticsnetwork.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    @Test
    void errorResponseCarriesExpectedFields() {
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Invalid request",
                java.time.LocalDateTime.now()
        );

        assertEquals(400, response.status());
        assertEquals("Bad Request", response.error());
        assertEquals("Invalid request", response.message());
        assertNotNull(response.timestamp());
    }
}
