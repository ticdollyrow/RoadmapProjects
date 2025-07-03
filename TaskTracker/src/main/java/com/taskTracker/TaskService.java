package com.taskTracker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class TaskService {
    private final List<Task> tasks;
    private final TaskRepository taskRepository = new FileTaskRepository();


    public TaskService() {
       this.tasks = taskRepository.loadTasks();
    }

    public Long addTask(String description){
        Long id = 1L;
        if (tasks.size() != 0) {
            Task task = tasks.getLast();
            id = task.getId() + 1;
        }

        Task task = new Task(id, description, TaskStatus.TO_DO, LocalDateTime.now(), LocalDateTime.now());
        tasks.add(task);
        taskRepository.saveTasks(tasks);
        return id;
    }

    public boolean delete(Long id){
        final Optional<Task> optionalTask = findById(id);
        if(optionalTask.isPresent()){
            Task task = optionalTask.get();
            final boolean remove = tasks.remove(task);
            if(remove) {
                taskRepository.saveTasks(tasks);
            }
            return remove;
        }else {
            return false;
        }
    }

    public void update(Long id, String description){
        final Optional<Task> optionalTask = findById(id);
        if(optionalTask.isPresent()){
            Task task = optionalTask.get();
            task.setDescription(description);
            task.setUpdatedAt(LocalDateTime.now());
            taskRepository.saveTasks(tasks);
        }
    }

    public void updateStatus(Long id, TaskStatus status){
        final Optional<Task> optionalTask = findById(id);
        if(optionalTask.isPresent()){
            Task task = optionalTask.get();
            task.setUpdatedAt(LocalDateTime.now());
            task.setStatus(status);
            taskRepository.saveTasks(tasks);
        }
    }

    public void listTasks(TaskStatus ... taskStatus){
        if(taskStatus.length == 0) {
            for (Task task : tasks) {
                System.out.println(task.toString());
            }
        }else{
            tasks.stream().filter(task -> task.getStatus() == taskStatus[0]).forEach(System.out::println);
        }
    }

    public Optional<Task> findById(Long id){
        for (Task task: tasks){
            if(id.equals(task.getId())) {
                return Optional.of(task);
            }
        }

        return Optional.empty();
    }
}
