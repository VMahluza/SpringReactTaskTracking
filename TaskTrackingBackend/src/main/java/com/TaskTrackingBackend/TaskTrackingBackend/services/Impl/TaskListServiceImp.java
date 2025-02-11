package com.TaskTrackingBackend.TaskTrackingBackend.services.Impl;

import com.TaskTrackingBackend.TaskTrackingBackend.domain.Entities.TaskList;
import com.TaskTrackingBackend.TaskTrackingBackend.repositories.TaskListRepository;
import com.TaskTrackingBackend.TaskTrackingBackend.services.TaskListService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class TaskListServiceImp implements TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListServiceImp(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskList> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createTaskList(TaskList taskList) {

        LocalDateTime now = LocalDateTime.now();

        if(null != taskList.getId())
            throw new IllegalArgumentException("Task List already has an ID");

        if(null == taskList.getTitle() || taskList.getTitle().isBlank())
            throw new IllegalArgumentException("List title is required");

        return taskListRepository.save(new TaskList(
                null,
                taskList.getTitle(),
                taskList.getDescription(),
                now,
                now,
                null
        ));
    }

    @Override
    public Optional<TaskList> getTaskList(UUID id) {
        return taskListRepository.findById(id);
    }

    @Override
    public TaskList updateTaskList(UUID taskListId, TaskList taskList) {

        if (null == taskList.getId())
            throw new IllegalArgumentException("Task List must have an ID to be Updated");

        if(!Objects.equals(taskList.getId().toString(), taskListId.toString()))
            throw new IllegalArgumentException("Changing Task ID on update is not allowed");

        TaskList existingTaskList = taskListRepository.findById(taskListId)
            .orElseThrow(() ->
                            new IllegalStateException("Task list not found!")
            );

        existingTaskList.setTitle(taskList.getTitle());
        existingTaskList.setDescription(taskList.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
    return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}
