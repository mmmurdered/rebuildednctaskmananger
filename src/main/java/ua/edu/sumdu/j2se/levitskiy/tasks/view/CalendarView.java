package ua.edu.sumdu.j2se.levitskiy.tasks.view;

import ua.edu.sumdu.j2se.levitskiy.tasks.controller.TaskController;
import ua.edu.sumdu.j2se.levitskiy.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.levitskiy.tasks.model.Task;
import ua.edu.sumdu.j2se.levitskiy.tasks.model.Tasks;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class CalendarView {

    public void showCalendar(AbstractTaskList taskList) {
            System.out.println("Enter start date: ");
            LocalDateTime start = TaskController.scanLocalDateTime();
            System.out.println("Enter end time: ");
            LocalDateTime end = TaskController.scanLocalDateTime();

            SortedMap<LocalDateTime, Set<Task>> calendar = Tasks.calendar(taskList, start, end);
            for (Map.Entry entry : calendar.entrySet()) {
                System.out.print(entry.getKey() + " | ");
                System.out.println(entry.getValue());
            }
    }
}
