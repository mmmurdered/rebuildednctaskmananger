package ua.edu.sumdu.j2se.levitskiy.tasks;

import ua.edu.sumdu.j2se.levitskiy.tasks.controller.ExecuteController;

import java.io.IOException;

public class App {
    public static void startApp() throws IOException {
        ExecuteController controller = new ExecuteController();
        controller.executeController();
    }

    public static void main(String[] args) throws IOException {
        startApp();
    }
}
