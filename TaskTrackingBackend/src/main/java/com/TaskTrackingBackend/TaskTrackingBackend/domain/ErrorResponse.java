package com.TaskTrackingBackend.TaskTrackingBackend.domain;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
