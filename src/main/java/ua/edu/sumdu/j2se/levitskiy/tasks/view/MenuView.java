package ua.edu.sumdu.j2se.levitskiy.tasks.view;

import ua.edu.sumdu.j2se.levitskiy.tasks.controller.TaskController;
import ua.edu.sumdu.j2se.levitskiy.tasks.model.AbstractTaskList;

import java.io.IOException;
import java.util.Scanner;

public class MenuView {
    CalendarView calendarView = new CalendarView();
    TaskListView taskListView = new TaskListView();

    public void showMenu(TaskController controller) throws IOException {
        AbstractTaskList taskList = controller.getTaskList();

        controller.loadFromJson(taskList);

        int choose = 0;
        while (choose != -1){
            commandsMenu();

            choose = scanAction(controller);

            System.out.print("");


            switch (choose){
                case 1:
                    controller.addTask(taskList);
                    break;
                case 2:
                    controller.changeTask(taskList);
                    break;
                case 3:
                    controller.deleteTask(taskList);
                    break;
                case 4:
                    taskListView.showTaskList(taskList);
                    break;
                case 5:
                    calendarView.showCalendar(taskList);
            }
            if(choose == -1)
                break;
        }

        controller.loadToJson(taskList);

        controller.getLogger().info("App was closed");
        System.out.println("Thank you for using");
    }

    private void commandsMenu() {
        System.out.println("=== TASK MANAGER ===");
        System.out.println("1: Create new task");
        System.out.println("2: Change task");
        System.out.println("3: Delete task");
        System.out.println("4: View task");
        System.out.println("5: View calendar");
        System.out.println("Exit (-1)");
        System.out.print("Enter action number: ");
    }

    public int scanAction(TaskController controller){
        int action = 0;
        try {
            Scanner sc = new Scanner(System.in);

            controller.getLogger().info("Action was picked successfully");

            action =  Integer.parseInt(sc.nextLine());
        } catch (Exception e){
            controller.getLogger().info(e.getMessage());
        }
        return action;
    }
}
