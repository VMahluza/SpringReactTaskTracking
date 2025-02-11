package com.TaskTrackingBackend.TaskTrackingBackend.repositories;

import com.TaskTrackingBackend.TaskTrackingBackend.domain.Entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskListRepository  extends JpaRepository<TaskList, UUID> {
}
