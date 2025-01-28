package model;

import utils.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        DateTimeFormatter fromFormatter = DateTimeParser.checkFormat(from);
        DateTimeFormatter toFormatter = DateTimeParser.checkFormat(to);
        return "[E]" + super.toString() + "(from: " +
                from.format(fromFormatter) + " to: " + to.format(toFormatter) + ")";

    }
}
