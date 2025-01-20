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
        if (this.tasks.isEmpty()) {
            System.out.println("You have no tasks to show.");
        } else {
            for(int i=0; i < this.tasks.size(); i++) {
                Task curr = this.tasks.get(i);
                System.out.println((i + 1) + "." + curr);
            }
        }
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public String printNumberOfTasks() {
        String isPlural = this.tasks.size() != 1
                          ? " tasks in the list."
                          : " task in the list.";
        return "\nNow you have " + String.valueOf(this.tasks.size()) + isPlural;
    }

    public String deleteTask(int index) {
        Task curr = this.tasks.get(index);
        this.tasks.remove(index);
        return "Alright! I've deleted this task:\n" + curr.toString() + printNumberOfTasks();
    }
}
