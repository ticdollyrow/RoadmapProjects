package com.taskTracker;

public class TaskCLIApp {
    private static final String ADD_TASK = "add";
    private static final String UPDATE_TASK = "update";
    private static final String DELETE_TASK = "delete";
    private static final String MARK_IN_PROGRESS = "mark-in-progress";
    private static final String MARK_DONE = "mark-done";

    public static void main(String[] args) {
        if(args.length  == 0){
            System.out.println("Command");
            return;
        }

        TaskService taskService = new TaskService();

        String command = args[0];
        switch (command){
            case ADD_TASK -> {
                System.out.println("Task added successfully (ID: " + taskService.addTask(args[1]) + ")");
            }
            case UPDATE_TASK -> {

            }
            case DELETE_TASK -> {

            }
            case MARK_IN_PROGRESS -> {

            }

            case MARK_DONE -> {

            }
            default -> {
                System.out.println("Unknown command.");
            }
        }
    }
}
