package ua.edu.sumdu.j2se.levitskiy.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable, Serializable {
    private String title;
    private LocalDateTime time;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int repeatInterval;
    private boolean isActive;
    private boolean isRepeated;

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setRepeatInterval(int repeatInterval) {
        this.repeatInterval = repeatInterval;
    }

    public Task(String title, LocalDateTime time) {
        this.title = title;
        this.time = time;
        isRepeated = false;
        if (time == null) {
            throw new IllegalArgumentException("Time can not be null");
        }
    }

    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) {
        this.title = title;
        this.startTime = start;
        this.endTime = end;
        this.repeatInterval = interval;
        isRepeated = true;
        if (start == null || end == null) {
            throw new IllegalArgumentException("Time can not be less than 0");
        }
        if (interval < 0) {
            throw new IllegalArgumentException("Interval cant be less than 0");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getTime() {
        if(isRepeated){
            return this.startTime;
        }
        else{
            return this.time;
        }
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
        this.isRepeated = false;
        this.startTime = null;
        this.endTime = null;
        this.repeatInterval = 0;
    }

    public void setTime(LocalDateTime startTime, LocalDateTime endTime, int repeatInterval){
        this.time = null;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isRepeated = true;
        this.repeatInterval = repeatInterval;
    }

    public LocalDateTime getStartTime() {
        if(isRepeated){
            return startTime;
        }
        else{
            return time;
        }
    }

    public LocalDateTime getEndTime() {
        if(isRepeated){
            return endTime;
        }
        else {
            return time;
        }
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (isActive()) {
            if (!isRepeated()) {
                if (current.isBefore(getTime())) {
                    return getTime();
                }
                return null;
            } else if (isRepeated()) {
                if (current.isBefore(getStartTime())) {
                    return getStartTime();
                } else if (getStartTime().isBefore(current) || getStartTime().isEqual(current)) {
                    LocalDateTime nextTime = getStartTime();
                    while (nextTime.isBefore(current) || nextTime.isEqual(current)) {
                        nextTime = nextTime.plusSeconds(getRepeatInterval());
                    }
                    if (nextTime.isAfter(getEndTime())) {
                        return null;
                    }
                    return nextTime;
                }
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, startTime, time, endTime, repeatInterval, isActive, isRepeated);
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass() != obj.getClass()) return false;
        if(this == null) return false;
        if(this == obj) return true;
        Task task = (Task) obj;

        return this.startTime == task.startTime && this.isRepeated == task.isRepeated
                && this.time == task.time && this.repeatInterval == task.repeatInterval
                && this.endTime == task.endTime && this.isActive == task.isActive
                && this.title == task.title;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Task cloned =  (Task) super.clone();
        cloned.setTitle(this.getTitle());
        return cloned;
    }

    @Override
    public String toString() {
        if(this.isRepeated){
            return "Task{" +
                    "title =' " + title + '\'' +
                    " , startTime = " + startTime +
                    ", endTime = " + endTime +
                    ", repeatInterval = " + repeatInterval +
                    ", isRepeated = " + isRepeated +
                    ", isActive = " + isActive +
                    '}';
        } else {
            return "Task{" +
                    "title =' " + title + '\'' +
                    ", time = " + time +
                    ", isActive = " + isActive +
                    ", isRepeated = " + isRepeated +
                    '}';
        }
    }

    public int getRepeatInterval() {
        return repeatInterval;
    }
}
