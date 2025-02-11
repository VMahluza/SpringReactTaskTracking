package com.TaskTrackingBackend.TaskTrackingBackend.domain.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task_lists")
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;
    @Column(name = "created", nullable = false)
    private LocalDateTime created;


    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    @OneToMany(mappedBy = "taskList" , cascade = {
            CascadeType.REMOVE, CascadeType.PERSIST
    })
    private List<Task> tasks;

}
