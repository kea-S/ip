public class Event extends Task {
  String eventStartEnd;

  public Event(String inputText) {
    super(inputText);

    // Find the starting index of "/from" and "/to"
    int fromIndex = inputText.indexOf("/from");
    int toIndex = inputText.indexOf("/to");

    if (fromIndex != -1 && toIndex != -1) {
      // Extract the "from" part
      String fromPart = inputText.substring(fromIndex + 6, toIndex).trim(); // +6 to skip "/from "
      // Extract the "to" part
      String toPart = inputText.substring(toIndex + 4).trim(); // +4 to skip "/to "
      this.eventStartEnd = " (from: " + fromPart + " to: " + toPart + ")";
    } else {
      throw new IllegalArgumentException("The start and end of the deadline need to be specified with /");
    }

  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + this.eventStartEnd;
  }

}
