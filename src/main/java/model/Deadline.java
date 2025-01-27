package model;

public class Deadline extends Task{

    private final String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
