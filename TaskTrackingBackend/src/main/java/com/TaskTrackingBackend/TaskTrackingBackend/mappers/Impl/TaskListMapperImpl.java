package com.TaskTrackingBackend.TaskTrackingBackend.mappers.Impl;

import com.TaskTrackingBackend.TaskTrackingBackend.domain.DTOs.TaskListDTO;
import com.TaskTrackingBackend.TaskTrackingBackend.domain.Entities.Task;
import com.TaskTrackingBackend.TaskTrackingBackend.domain.Entities.TaskList;
import com.TaskTrackingBackend.TaskTrackingBackend.mappers.TaskListMapper;
import com.TaskTrackingBackend.TaskTrackingBackend.mappers.TaskMapper;
import org.springframework.stereotype.Component;
import com.TaskTrackingBackend.TaskTrackingBackend.domain.Enumarisation.*;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList toEntity(TaskListDTO taskListDTO) {
        return  new TaskList(
                taskListDTO.id(),
                taskListDTO.title(),
                taskListDTO.description(),
                null,
                null,
                Optional.ofNullable(taskListDTO.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toEntity)
                                .toList())
                        .orElse(null)
                );
    }

    @Override
    public TaskListDTO toDto(TaskList taskList) {
        final List<Task> tasks = taskList.getTasks();
        return new TaskListDTO(
                taskList.getId(),
                taskList.getTitle(),
                taskList.getDescription(),
                Optional.ofNullable(tasks)
                        .map(List::size)
                        .orElse(0),
                calculateTaskListProgress(tasks),
                Optional.ofNullable(tasks)
                .map(t -> t.stream()
                                .map(taskMapper::toDto)
                .toList())
                .orElse(null));
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if(null == tasks) {
            return null;
        }

        long closedTaskCount = tasks.stream()
        .filter(task -> TaskStatus.CLOSED == task.getStatus())
        .count();

        return (double) closedTaskCount / tasks.size();
    }

}
