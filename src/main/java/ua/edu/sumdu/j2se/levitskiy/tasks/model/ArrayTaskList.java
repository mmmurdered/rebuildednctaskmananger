package ua.edu.sumdu.j2se.levitskiy.tasks.model;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList implements Cloneable {
    private Task[] arrayList = new Task[0];

    @Override
    public void add(Task task){
        Task[] tempArrayList;
        tempArrayList = Arrays.copyOf(arrayList, arrayList.length + 1);
        tempArrayList[arrayList.length] = task;
        arrayList = Arrays.copyOf(tempArrayList, tempArrayList.length);
    }

    @Override
    public boolean remove(Task task){
        int i = 0;
        while(i < arrayList.length && arrayList[i] != task) {
            i++;
        }
        if(i == arrayList.length) {
            return false;
        }

        for(int j = 0; j < arrayList.length; j++){
            if(arrayList[j].equals(task)){
                arrayList[j] = null;
                for(int k = j + 1; k < arrayList.length; k++){
                    arrayList[k - 1] = arrayList[k];
                }
                break;
            }
        }
        Task[] tempArrayList = Arrays.copyOf(arrayList, arrayList.length - 1);
        arrayList = Arrays.copyOf(tempArrayList, tempArrayList.length);
        return true;
    }

    @Override
    public int size(){
        return arrayList.length;
    }

    @Override
    public Task getTask (int index){
        if(index < arrayList.length && index >= 0){
            return arrayList[index];
        } else{
            throw new IndexOutOfBoundsException("Index must be < size and >= 0");
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return new ArrayListIterator(this);
    }

    private class ArrayListIterator implements Iterator<Task>{
        private int currentIndex;
        private int lastIndex;
        private ArrayTaskList list;

        public ArrayListIterator(ArrayTaskList list) {
            this.currentIndex = 0;
            this.lastIndex = -1;
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return (currentIndex < list.size());
        }

        @Override
        public Task next() {
            lastIndex = currentIndex;
            return list.getTask(currentIndex++);
        }

        @Override
        public void remove() {
            if(lastIndex < 0){
                throw new IllegalStateException();
            }
            else{
                list.remove(list.getTask(lastIndex));
                currentIndex = lastIndex;
                lastIndex = -1;
            }
        }
    }

    @Override
    public ListTypes.types getType(){
        return ListTypes.types.ARRAY;
    }

    @Override
    public Stream<Task> getStream() {
        return Stream.of(arrayList);
    }
}
