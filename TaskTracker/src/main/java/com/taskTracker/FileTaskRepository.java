package com.taskTracker;

import java.util.List;

public class FileTaskRepository implements TaskRepository{
    @Override
    public List<Task> loadTasks() {
        return List.of();
    }

    @Override
    public void saveTasks(List<Task> tasks) {

    }
}
