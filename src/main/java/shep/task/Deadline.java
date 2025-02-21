package shep.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final int NOT_FOUND = -1;

    private String deadlineDate;

    public Deadline(String inputText) {
        super(inputText);

        this.deadlineDate = this.getDate(inputText);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate + ")";
    }

    private String getDate(String inputText) {
        int byIndex = inputText.indexOf("/by");

        String deadlineDateInfo = "";
        // Check if "/by" is found
        if (byIndex != NOT_FOUND) {
            // Extract the part after "/by"
            deadlineDateInfo = inputText.substring(byIndex + 4).trim(); // +4 to skip "/by "
            // Format the date
            this.deadlineDate = LocalDate.parse(deadlineDateInfo).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            throw new IllegalArgumentException("The end of the deadline must be specified with /by (e.g. event ask Shep /by 2025-02-21)");
        }

        return deadlineDateInfo;
    }

}
