package ua.edu.sumdu.j2se.levitskiy.tasks.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.StreamSupport;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        return () -> StreamSupport.stream(tasks.spliterator(), false)
                .filter(task -> task.nextTimeAfter(start) != null && task != null &&
                        (task.nextTimeAfter(start).isBefore(end) || task.nextTimeAfter(start).isEqual(end))).iterator();
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end) {
        SortedMap<LocalDateTime, Set<Task>> calendarMap = new TreeMap<>();

        for (Task task : tasks) {
            /*Начальное время*/
            LocalDateTime time = task.nextTimeAfter(start);

            /*Идем до конца повторяемого таска*/
            while (time != null && !time.isAfter(end)) {
                /*Существует кей - добавлем
                Не существует - создаем новый*/
                if (calendarMap.containsKey(time)) {
                    calendarMap.get(time).add(task);
                } else {
                    Set<Task> taskSet = new HashSet<>();
                    taskSet.add(task);
                    calendarMap.put(time, taskSet);
                }
                time = task.nextTimeAfter(time);
            }
        }
        return calendarMap;
    }

}
