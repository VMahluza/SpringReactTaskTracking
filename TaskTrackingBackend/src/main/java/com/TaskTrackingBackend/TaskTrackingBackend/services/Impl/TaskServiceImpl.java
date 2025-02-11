package com.TaskTrackingBackend.TaskTrackingBackend.services.Impl;

import com.TaskTrackingBackend.TaskTrackingBackend.domain.Entities.Task;
import com.TaskTrackingBackend.TaskTrackingBackend.domain.Entities.TaskList;
import com.TaskTrackingBackend.TaskTrackingBackend.domain.Enumarisation.TaskPriority;
import com.TaskTrackingBackend.TaskTrackingBackend.domain.Enumarisation.TaskStatus;
import com.TaskTrackingBackend.TaskTrackingBackend.repositories.TaskListRepository;
import com.TaskTrackingBackend.TaskTrackingBackend.repositories.TaskRepository;
import com.TaskTrackingBackend.TaskTrackingBackend.services.TaskService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class TaskServiceImpl implements TaskService {

    private final TaskListRepository taskListRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskListRepository taskListRepository, TaskRepository taskRepository) {
        this.taskListRepository = taskListRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {

        if (null != task.getId())
            throw new IllegalArgumentException("Task already has ID!");

        if (null == task.getTitle() || task.getTitle().isBlank())
            throw new IllegalArgumentException("Task must have a title");
        TaskPriority priority = Optional.ofNullable(task.getPriority()).orElse(TaskPriority.MEDIUM);
        LocalDateTime now = LocalDateTime.now();

        TaskList taskList = taskListRepository.findById(taskListId)
                .orElseThrow(() ->new IllegalArgumentException("Invalid Task List ID provided"));
        return taskRepository.save(new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                priority,
                TaskStatus.OPEN,
                now, now,
                taskList
                ))
                ;
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
        if(null == task.getId())  throw new IllegalArgumentException("Task must have ID!");
        if(!Objects.equals(taskId, task.getId()))  throw new IllegalArgumentException("Task IDs do not match!");
        if(null == task.getPriority()) throw new IllegalArgumentException("Task must have a valid priority!");
        if(null == task.getStatus())  throw new IllegalArgumentException("Task must have a valid status!");

        Task existingTask = taskRepository.findByTaskListIdAndId(
                taskListId, task.getId()
        ).orElseThrow(() -> new IllegalStateException("Task not found!"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUpdated(LocalDateTime.now());
        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }
}
