package com.TaskTrackingBackend.TaskTrackingBackend.mappers;

import com.TaskTrackingBackend.TaskTrackingBackend.domain.DTOs.TaskDTO;
import com.TaskTrackingBackend.TaskTrackingBackend.domain.Entities.Task;

public interface TaskMapper {
    Task toEntity(TaskDTO taskDTO);
    TaskDTO toDto(Task task);
}
