package ua.edu.sumdu.j2se.levitskiy.tasks.model;

import java.util.Iterator;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList implements Cloneable {
    private Node head;

    @Override
    public void add(Task task){
        /* Если нету ни одной ноды то создаем */
        if(head == null){
            head = new Node(task);
            return;
        }

        Node currentNode = head;
        //Идем по циклу до последней ноды
        while(currentNode.next != null){
            currentNode = currentNode.next;
        }
        /* Присваиваем последней ноде значение последующей */
        currentNode.next = new Node(task);
    }

    @Override
    public boolean remove(Task task){
        Node currentNode = head;
        /* Если удаляем первый элемент то просто смещаем вперёд */
        if(currentNode.value.equals(task)){
            head = head.next;
            return true;
        }
        /* Идем по нодам поочередно, если равняется аргументу, то смещаем некст ноду */
        while(currentNode.next != null){
            if(currentNode.next.value == task){
                currentNode.next = currentNode.next.next;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;

    }

    @Override
    public int size(){
        int size = 0;

        Node currentNode = head;
        /*Считаем количество нод*/
        while(currentNode != null){
            currentNode = currentNode.next;
            ++size;
        }
        return size;
    }

    @Override
    public Task getTask(int index){
        int tempIndex = 0;

        Node currentNode = head;

        while(tempIndex != index){
            currentNode = currentNode.next;
            ++tempIndex;
        }
        return currentNode.value;
    }

    @Override
    public Iterator<Task> iterator() {
        return new LinkedListIterator(this);
    }

    private class LinkedListIterator implements Iterator<Task>{
        private Node currentNode;
        private Node lastNode;
        private LinkedTaskList list;

        public LinkedListIterator(LinkedTaskList tasks){
            this.currentNode = tasks.head;
            this.lastNode = null;
            this.list = tasks;
        }
        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public Task next() {
            lastNode = currentNode;
            currentNode = currentNode.next;
            return lastNode.value;
        }

        @Override
        public void remove() {
            if(lastNode == null){
                throw new IllegalStateException();
            }
            else{
                list.remove(lastNode.value);
            }
        }
    }

    @Override
    public ListTypes.types getType(){
        return ListTypes.types.LINKED;
    }

    private static class Node{
        private Task value;
        private Node next;

        public Node(Task value) {
            this.value = value;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Stream<Task> getStream() {
        Task[] list = new Task[this.size()];

        for(int i = 0; i < this.size(); i++){
            list[i] = this.getTask(i);
        }

        return Stream.of(list);
    }
}
