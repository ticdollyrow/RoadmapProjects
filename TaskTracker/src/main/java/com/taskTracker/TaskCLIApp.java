package com.taskTracker;

public class TaskCLIApp {
    private static final String ADD_TASK = "add";
    private static final String UPDATE_TASK = "update";
    private static final String DELETE_TASK = "delete";
    private static final String MARK_IN_PROGRESS = "mark-in-progress";
    private static final String MARK_DONE = "mark-done";
    private static final String LIST = "list";

    public static void main(String[] args) {
        if(args.length  == 0){
            System.out.println("Please specify a command.");
            return;
        }

        TaskService taskService = new TaskService();

        String command = args[0];
        switch (command){
            case ADD_TASK -> {
                if(args.length < 2){
                    System.out.println("Add description to task");
                    break;
                }
                System.out.println("Task added successfully (ID: " + taskService.addTask(args[1]) + ")");
            }
            case UPDATE_TASK -> {
                if(args.length < 3){
                    System.out.println("To update you need Id and new description");
                    break;
                }
                Long id = Long.valueOf(args[1]);
                String description = args[2];
                taskService.update(id,description);
            }
            case DELETE_TASK -> {
                if(args.length < 2) {
                    System.out.println("Command delete needs argument(Task Id)");
                    break;
                }
                Long id = Long.valueOf(args[1]);
                if(!taskService.delete(id)){
                    System.out.println("Error");

                }
            }
            case MARK_IN_PROGRESS -> {
                if(args.length < 2){
                    System.out.println("mark-in-progress <id>");
                    break;
                }
                Long id = Long.valueOf(args[1]);
                taskService.updateStatus(id, TaskStatus.IN_PROGRESS);
            }

            case MARK_DONE -> {
                if(args.length < 2){
                    System.out.println("mark-done <id>");
                    break;
                }
                Long id = Long.valueOf(args[1]);
                taskService.updateStatus(id, TaskStatus.DONE);
            }

            case LIST -> {
                if(args.length < 2){
                    taskService.listTasks();
                }else{
                    TaskStatus taskStatus = TaskStatus.valueOf(args[1].toUpperCase().replace("-", "_"));
                    taskService.listTasks(taskStatus);
                }
            }
            default -> {
                System.out.println("Unknown command.");
            }
        }
    }
}
