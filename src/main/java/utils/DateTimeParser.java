package utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    public static LocalDateTime parseDateTime(String input) throws DuduException {
        DateTimeFormatter formatter;
        LocalDateTime dateTime;

        try {
            if (input.contains(" ")) {
                formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                dateTime = LocalDateTime.parse(input, formatter);
            } else {
                formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
                LocalDate date = LocalDate.parse(input, formatter);
                dateTime = date.atStartOfDay();
            }
        } catch (DateTimeException e) {
            throw new DuduException("Invalid date format. Please use d/M/yyyy or d/M/yyyy HHmm.");
        }
        return dateTime;
    }

    public static LocalDateTime parseFromFile(String input) {
        return LocalDateTime.parse(input);
    }

    public static DateTimeFormatter checkFormat(LocalDateTime dateTime) {
        if (dateTime.getHour() == 0 && dateTime.getMinute() == 0) {
            return DateTimeFormatter.ofPattern("MMM dd yyyy");
        } else {
            return DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
        }
    }

}
