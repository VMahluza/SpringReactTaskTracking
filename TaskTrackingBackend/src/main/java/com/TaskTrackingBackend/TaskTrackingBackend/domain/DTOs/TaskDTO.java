package com.TaskTrackingBackend.TaskTrackingBackend.domain.DTOs;

import com.TaskTrackingBackend.TaskTrackingBackend.domain.Enumarisation.TaskPriority;
import com.TaskTrackingBackend.TaskTrackingBackend.domain.Enumarisation.TaskStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDTO(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriority priority,
        TaskStatus status
) {
}


