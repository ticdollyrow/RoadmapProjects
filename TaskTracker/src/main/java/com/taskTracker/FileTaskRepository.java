package com.taskTracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileTaskRepository implements TaskRepository{
    private static final String FILE_NAME = "tasks.json";
    private final Path FILE_PATH = Path.of(FILE_NAME);

    @Override
    public List<Task> loadTasks() {
        List<Task> storedTasks = new ArrayList<>();
        try {
            if (!Files.exists(FILE_PATH) || Files.size(FILE_PATH) == 0) {
                return new ArrayList<>();
            }
        }catch (IOException ioException){
            return new ArrayList<>();
        }

        try{
            String jsonContent = Files.readString(FILE_PATH);
            if (jsonContent.startsWith("[") && jsonContent.endsWith("]")) {
                jsonContent = jsonContent.substring(1, jsonContent.length() - 1).trim();
            }
            String[] items = jsonContent.split("},");

            for(String item:items) {
                item = item.replaceAll("}", "").replaceAll("}", "").replace("\"", "");
                String[] task = item.split(",");
                if(task.length == 0) break;

                Long id = Long.valueOf( task[0].split(":")[1].strip() );
                String description = task[1].split(":")[1].strip();
                TaskStatus taskStatus = TaskStatus.valueOf(task[2].split(":")[1].strip());
                String date = task[3].split("[a-z]:")[1].strip();
                LocalDateTime createdAt = LocalDateTime.parse(date);
                LocalDateTime updatedAt = LocalDateTime.parse(task[4].split("[a-z]:")[1].strip());

                storedTasks.add(new Task(id, description, taskStatus, createdAt,updatedAt));
            }


        }catch (IOException ioException){
            ioException.printStackTrace();
        }

        return storedTasks;
    }

    @Override
    public void saveTasks(List<Task> tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        int step = 0;
        int count = tasks.size()-1;
        stringBuilder.append("[\n");
        for(Task task:tasks){
            String json = Task.toJson(task);
            stringBuilder.append(json);
            if(step != count) {
                stringBuilder.append(",");
            }
            step++;
        }
        stringBuilder.append("\n]");

        String jsonContent = stringBuilder.toString();
        try {
            Files.writeString(FILE_PATH, jsonContent);
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
