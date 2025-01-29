import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String deadlineDate;

    public Deadline(String inputText) {
        super(inputText);

        int byIndex = inputText.indexOf("/by");

        // Check if "/by" is found
        if (byIndex != -1) {
            // Extract the part after "/by"
            String deadlineDateInfo = inputText.substring(byIndex + 4).trim(); // +4 to skip "/by "
            // Format the date
            this.deadlineDate = LocalDate.parse(deadlineDateInfo).format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            throw new IllegalArgumentException("The end of the deadline must be specified with a /");
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadlineDate + ")";
    }

}
