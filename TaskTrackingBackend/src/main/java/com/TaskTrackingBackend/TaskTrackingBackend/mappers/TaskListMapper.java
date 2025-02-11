package com.TaskTrackingBackend.TaskTrackingBackend.mappers;

import com.TaskTrackingBackend.TaskTrackingBackend.domain.DTOs.TaskListDTO;
import com.TaskTrackingBackend.TaskTrackingBackend.domain.Entities.TaskList;

public interface TaskListMapper {
    TaskList toEntity(TaskListDTO taskListDTO);
    TaskListDTO toDto(TaskList taskList);
}
