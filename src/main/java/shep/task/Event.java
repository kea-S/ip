package shep.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final int NOT_FOUND = -1;

    private String eventStart;
    private String eventEnd;

    public Event(String inputText) {
        super(inputText);

        String[] startEnd = this.getDates(inputText);
        this.eventStart = startEnd[0];
        this.eventEnd = startEnd[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.eventStart + " to: " + this.eventEnd + ")";
    }

    private String[] getDates(String inputText) {
        // Find the starting index of "/from" and "/to"
        int fromIndex = inputText.indexOf("/from");
        int toIndex = inputText.indexOf("/to");

        String[] formattedDates = new String[2];
        if (fromIndex != NOT_FOUND && toIndex != NOT_FOUND) {
            String fromPart = inputText.substring(fromIndex + 6, toIndex).trim(); // +6 to skip "/from "
            String toPart = inputText.substring(toIndex + 4).trim(); // +4 to skip "/to "

            formattedDates[0] = LocalDate.parse(fromPart).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            formattedDates[1] = LocalDate.parse(toPart).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            throw new IllegalArgumentException("The start and end of the event need to be specified with /from and /to (e.g. event ask Shep /from 2025-02-21 /to 2025-02-22)");
        }

        return formattedDates;
    }

}
