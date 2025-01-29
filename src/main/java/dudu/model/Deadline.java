package dudu.model;

import dudu.utils.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    /** Deadline of task **/
    private final LocalDateTime by;

    /**
     * Creates Deadline object.
     *
     * @param name
     * @param by
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    /**
     * Returns deadline task in String format.
     *
     * @return Line with status, title and deadline of task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeParser.checkFormat(by);
        return "[D]" + super.toString() + "(by: " + by.format(formatter) + ")";
    }
}
