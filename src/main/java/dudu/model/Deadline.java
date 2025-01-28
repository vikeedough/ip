package dudu.model;

import dudu.utils.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private final LocalDateTime by;

    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeParser.checkFormat(by);
        return "[D]" + super.toString() + "(by: " + by.format(formatter) + ")";
    }
}
