package controller;

public class Entry {
    private String name;
    private boolean isDone;

    public Entry(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        String status = this.isDone
                        ? "[X]"
                        : "[ ]";
        return status + " " + this.name;
    }
}
