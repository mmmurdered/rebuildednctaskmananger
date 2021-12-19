package ua.edu.sumdu.j2se.levitskiy.tasks.model;

public class TaskListFactory {
    static String temp;

    public static AbstractTaskList createTaskList(ListTypes.types type){
        temp = type.name();

        AbstractTaskList list = null;
        switch (type){
            case ARRAY:
                list = new ArrayTaskList();
                break;
            case LINKED:
                list = new LinkedTaskList();
                break;
        }

        return list;
    }

}
