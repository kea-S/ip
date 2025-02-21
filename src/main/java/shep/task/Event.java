package shep.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final int NOT_FOUND = -1;

    private String eventStart;
    private String eventEnd;

    public Event(String inputText) {
        super(inputText);

        getDates(inputText);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.eventStart + " to: " + this.eventEnd + ")";
    }

    private void getDates(String inputText) {
        // Find the starting index of "/from" and "/to"
        int fromIndex = inputText.indexOf("/from");
        int toIndex = inputText.indexOf("/to");

        if (fromIndex != NOT_FOUND && toIndex != NOT_FOUND) {
            // Extract the "from" part
            String fromPart = inputText.substring(fromIndex + 6, toIndex).trim(); // +6 to skip "/from "

            // Extract the "to" part
            String toPart = inputText.substring(toIndex + 4).trim(); // +4 to skip "/to "

            this.eventStart = LocalDate.parse(fromPart).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.eventEnd = LocalDate.parse(toPart).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            throw new IllegalArgumentException("The start and end of the event need to be specified with /from and /to (e.g. event ask Shep /from 2025-02-21 /to 2025-02-22)");
        }

    }

}
