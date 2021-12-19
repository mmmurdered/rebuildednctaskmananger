package ua.edu.sumdu.j2se.levitskiy.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.stream.Stream;

public abstract class AbstractTaskList extends TaskListFactory implements Iterable<Task>, Cloneable, Serializable {
    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    public abstract ListTypes.types getType();

    public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to){
        AbstractTaskList tempList = createTaskList(getType());
        this.getStream().filter(task -> task.nextTimeAfter(from).isBefore(to) && task.nextTimeAfter(from) != null && task != null).forEach(tempList::add);
        return tempList;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        for(Iterator<Task> it = iterator(); it.hasNext(); ){
            hash += it.next().hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null) return false;
        if(!(obj instanceof AbstractTaskList)) return false;
        AbstractTaskList list = (AbstractTaskList) obj;
        if(this.size() != list.size()) return false;
        for(int i = 0; i < size(); i++){
            if(!this.getTask(i).equals(list.getTask(i))) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("List: [");
        for(int i = 0; i < size(); i++){
            if(i == size() - 1){
                sb.append(getTask(i).getTitle());
                continue;
            }
            sb.append(getTask(i).getTitle());
            sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AbstractTaskList list = createTaskList(getType());

        for(Task task : this) {
            list.add(task);
        }

        return list;
    }

    public abstract Stream<Task> getStream();
}
