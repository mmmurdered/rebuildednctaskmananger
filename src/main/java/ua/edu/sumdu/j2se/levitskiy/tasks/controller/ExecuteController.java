package ua.edu.sumdu.j2se.levitskiy.tasks.controller;

import ua.edu.sumdu.j2se.levitskiy.tasks.view.MenuView;

import java.io.IOException;
import java.util.Scanner;

public class ExecuteController {
    TaskController controller = new TaskController();
    MenuView menuView = new MenuView();

    Scanner scanner = new Scanner(System.in);

    public ExecuteController() throws IOException {
    }

    public void executeController() throws IOException {
        menuView.showMenu(controller);

        controller.getLogger().info("Controller was executed");
    }
}
