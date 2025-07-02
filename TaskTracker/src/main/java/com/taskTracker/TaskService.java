package com.taskTracker;

import java.time.LocalDateTime;
import java.util.List;

public class TaskService {
    private final List<Task> tasks;
    private final TaskRepository taskRepository = new FileTaskRepository();


    public TaskService() {
       this.tasks = taskRepository.loadTasks();
    }

    public Long addTask(String description){
        Long id = (long) tasks.size() + 1;
        Task task = new Task(id, description, TaskStatus.TO_DO, LocalDateTime.now(), LocalDateTime.now());
        tasks.add(task);
        taskRepository.saveTasks(tasks);
        return id;
    }
}
