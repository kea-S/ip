public class Deadline extends Task {
  String deadlineDate;

  public Deadline(String inputText) {
    super(inputText);

    int byIndex = inputText.indexOf("/by");

    // Check if "/by" is found
    if (byIndex != -1) {
      // Extract the part after "/by"
      String byPart = inputText.substring(byIndex + 4).trim(); // +4 to skip "/by "
      // Format the result
      this.deadlineDate = " (by: " + byPart + ")";
    }
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + this.deadlineDate;
  }

}
