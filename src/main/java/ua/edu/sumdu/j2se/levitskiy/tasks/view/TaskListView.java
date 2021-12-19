package ua.edu.sumdu.j2se.levitskiy.tasks.view;

import ua.edu.sumdu.j2se.levitskiy.tasks.model.AbstractTaskList;

public class TaskListView {
    public void showTaskList(AbstractTaskList taskList) {
        if(taskList.size() != 0){
            for(int i = 0; i < taskList.size(); i++){
                System.out.println("Task " + i + 1 + ": ");
                System.out.println(taskList.getTask(i).toString());
            }
        } else {
            System.out.println("Task list is empty");
        }
    }
}
