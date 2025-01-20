package model;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public String addEntry(Task task) {
        this.tasks.add(task);
        return "added: " + task.getName();
    }

    public void printList() {
        for(int i=0; i < this.tasks.size(); i++) {
            Task curr = this.tasks.get(i);
            System.out.println((i + 1) + "." + curr);
        }
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }
}
