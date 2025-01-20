package model;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public String addEntry(Task task) {
        this.tasks.add(task);
        return "Alright! I've added this task:\n" + task.toString() + printNumberOfTasks();
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

    public String printNumberOfTasks() {
        String isPlural = this.tasks.size() > 1
                          ? " tasks in the list."
                          : " task in the list.";
        return "\nNow you have " + String.valueOf(this.tasks.size()) + isPlural;
    }
}
