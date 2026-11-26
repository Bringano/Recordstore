package se.yrgo.orderservice.controllers.exceptions;

import java.time.Instant;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message) {
}
