package ua.edu.sumdu.j2se.levitskiy.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.levitskiy.tasks.model.*;
import ua.edu.sumdu.j2se.levitskiy.tasks.view.TaskListView;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;


public class TaskController {
    private AbstractTaskList taskList = new ArrayTaskList();
    private Logger logger = Logger.getLogger(TaskController.class);

    Scanner scanner = new Scanner(System.in);

    public TaskController() throws IOException {
    }

    public AbstractTaskList getTaskList() {
        return taskList;
    }

    public Logger getLogger() {
        return logger;
    }

    public void deleteTask(AbstractTaskList tasks) {
        for(int i = 0; i < tasks.size(); i++){
            System.out.print("Index: "  + i + " ");
            System.out.println(tasks.getTask(i).toString());
        }

        try {
            System.out.print("Enter index of deleting: ");
            int index = Integer.parseInt(scanner.nextLine());
            String name = tasks.getTask(index).getTitle();
            tasks.remove(tasks.getTask(index));
            System.out.println("Task removed");

            logger.info("Task " + name + "was deleted");
        } catch (Exception e){
            logger.info(e.getMessage());
        }
    }

    public void changeTask(AbstractTaskList tasks) {
        //Scanner scanner = new Scanner(System.in);
        System.out.println("What task you want to change (enter index from 0): ");

        for(int i = 0; i < tasks.size(); i++){
            System.out.print("Index = " + i + " ");
            System.out.println(tasks.getTask(i).toString());
        }

        try {

            int index = Integer.parseInt(scanner.nextLine());
            //Task task = tasks.getTask(index);
            switch (getActionOfChanging()){
                case 1:
                    changeTaskTitle(tasks, index);
                    break;
                case 2:
                    changeTaskTimeNonRepetative(tasks, index);
                    break;
                case 3:
                    changeTaskStartEndTimeInterval( tasks, index);
                    break;
                case 4:
                    changeActivity(tasks, index);
                    break;
                default:
                    System.out.println("Wrong action");
                    break;
            }
        } catch (Exception e){
            logger.info("Exception in changing task");
        }
    }

    private int getActionOfChanging() {
        int action = 0;
        try {
            System.out.println("Enter change action: 1 - title; 2 - time; 3 - start, end,interval; 4 - active.");
            action = Integer.parseInt(scanner.nextLine());

            logger.info("Action of changing is " + action);
        } catch (Exception e){
            logger.info(e.getMessage());
        }
        return action;
    }

    private void changeNotification() {
        System.out.println("Task changed");
    }

    private void changeActivity(AbstractTaskList tasks, int index) {
        try {
            if(tasks.getTask(index).isActive()){
                System.out.println("Task is active");
            } else {
                System.out.println("Task is not active");
            }
            System.out.println("Enter new activity: ");
            boolean newIsActive = Boolean.parseBoolean(scanner.nextLine());
            tasks.getTask(index).setActive(newIsActive);
            changeNotification();

            logger.info("Activity for task " + tasks.getTask(index).getTitle() + " was changed to "
                    + tasks.getTask(index).isActive());
        } catch (Exception e){
            logger.info(e.getMessage());
        }
    }

    private void changeTaskStartEndTimeInterval(AbstractTaskList tasks, int index) {
        try {
            System.out.println("Now start time is: " + tasks.getTask(index).getStartTime());
            System.out.print("Enter new start time: ");
            LocalDateTime newStartTime = scanLocalDateTime();
            tasks.getTask(index).setStartTime(newStartTime);

            System.out.println("Now end time is: " + tasks.getTask(index).getStartTime());
            System.out.print("Enter new end time: ");
            LocalDateTime newEndTime = scanLocalDateTime();
            tasks.getTask(index).setStartTime(newEndTime);

            System.out.println("Now repeat interval is: " + tasks.getTask(index).getRepeatInterval());
            System.out.print("Enter new repeat interval: ");
            int newRepeatInterval = Integer.parseInt(scanner.nextLine());
            tasks.getTask(index).setRepeatInterval(newRepeatInterval);
            changeNotification();

            logger.info("Time for task " + tasks.getTask(index).getTitle() + " was changed to"
                    + tasks.getTask(index).getStartTime() + " | " + tasks.getTask(index).getEndTime()
                    + " repeat interval to " + tasks.getTask(index).getRepeatInterval());
        } catch (Exception e){
            logger.info(e.getMessage());
        }
    }

    private void changeTaskTimeNonRepetative(AbstractTaskList tasks, int index) {
        try {
            System.out.println("Now time is: " + tasks.getTask(index).getTime());
            System.out.print("Enter new time: ");
            LocalDateTime newTime = scanLocalDateTime();
            tasks.getTask(index).setTime(newTime);

            logger.info("Time for task " + tasks.getTask(index).getTitle() + " was changed to "
                    + tasks.getTask(index).getTime());
            changeNotification();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    private void changeTaskTitle(AbstractTaskList tasks, int index) {
        try {
            System.out.println("Now title is: " + tasks.getTask(index).getTitle());
            String tempTitle = tasks.getTask(index).getTitle();
            System.out.print("Enter new title: ");
            String newTitle = scanner.nextLine();
            tasks.getTask(index).setTitle(newTitle);
            changeNotification();

            logger.info("Title of task " + tempTitle + " was changed to "
                    + tasks.getTask(index).getTitle());
        } catch (Exception e){
            logger.info(e.getMessage());
        }
    }

    public static LocalDateTime scanLocalDateTime() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter year: ");
            int Year = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter month: ");
            int Month = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter day: ");
            int DayOfMonth = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter hour: ");
            int Hour = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter minutes: ");
            int Min = Integer.parseInt(scanner.nextLine());
            LocalDateTime time;
            System.out.println("");
            return time = LocalDateTime.of(Year,Month,DayOfMonth,Hour,Min);
    }



    public void addTask(AbstractTaskList taskList) {
        try {
            Scanner scanner = new Scanner(System.in);
            boolean repeatEntering = true;
            while(repeatEntering) {
                boolean isRepetable;
                System.out.print("Enter title of task: ");
                String title;
                title = scanner.nextLine();
                System.out.print("Add repeatable or not (true / false): ");
                isRepetable = Boolean.parseBoolean(scanner.nextLine());
                if (isRepetable) {
                    System.out.println("Enter start time (date), end time (date), repeat interval (int)");
                    System.out.println("(separated lines): ");

                    System.out.println("Enter start time:");
                    LocalDateTime startTime = scanLocalDateTime();
                    System.out.println("Enter end time:");
                    LocalDateTime endTime = scanLocalDateTime();
                    System.out.print("Enter repeat interval: ");
                    int repeatInterval = Integer.parseInt(scanner.nextLine());

                    System.out.println("Is active (true / false): ");
                    boolean isActive = Boolean.parseBoolean(scanner.nextLine());

                    Task task = new Task(title, startTime, endTime, repeatInterval);
                    task.setActive(isActive);

                    taskList.add(task);
                } else {
                    //System.out.println("Enter time: ");
                    LocalDateTime time = scanLocalDateTime();

                    System.out.println("Is active (true / false): ");
                    boolean isActive = Boolean.parseBoolean(scanner.nextLine());

                    Task task = new Task(title, time);
                    task.setActive(isActive);
                    taskList.add(task);
                }

                System.out.println("Task added");

                System.out.println("Do you want to add one more task? true / false");
                repeatEntering = Boolean.parseBoolean(scanner.nextLine());

                logger.info("Task " + taskList.getTask(taskList.size() - 1).getTitle() + " was added");
            }
        } catch (Exception e){
            logger.info(e.getMessage());
        }
    }

    public void loadFromJson(AbstractTaskList taskList) throws FileNotFoundException {
        try {
            TaskIO.read(taskList, new FileReader("tasks.json"));

            logger.info("Tasks were loaded from json file");
        } catch (Exception e){
            logger.info(e.getMessage());
        }
    }

    public void loadToJson(AbstractTaskList taskList) throws IOException {
        try {
            TaskIO.write(taskList, new FileWriter("tasks.json"));

            logger.info("Tasks were loaded to json file");
        } catch (Exception e){
            logger.info(e.getMessage());
        }
    }

}
