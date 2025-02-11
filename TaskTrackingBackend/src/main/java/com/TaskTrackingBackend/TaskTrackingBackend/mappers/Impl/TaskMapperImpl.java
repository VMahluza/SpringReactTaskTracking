package com.TaskTrackingBackend.TaskTrackingBackend.mappers.Impl;

import com.TaskTrackingBackend.TaskTrackingBackend.domain.DTOs.TaskDTO;
import com.TaskTrackingBackend.TaskTrackingBackend.domain.Entities.Task;
import com.TaskTrackingBackend.TaskTrackingBackend.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task toEntity(TaskDTO taskDTO) {
        return new Task(
                taskDTO.id(),
                taskDTO.title(),
                taskDTO.description(),
                taskDTO.dueDate(),
                taskDTO.priority(),
                taskDTO.status(),
                null,
                null,
                null);
    }

    @Override
    public TaskDTO toDto(Task task) {
        return new TaskDTO( task.getId(),
        task.getTitle(),
        task.getDescription(),
        task.getDueDate(),
        task.getPriority(),
        task.getStatus());
    }
}
