public abstract class Task {
  private String taskName;
  private boolean marked;

  public Task(String inputText) {
    int firstSpaceIndex = inputText.indexOf(' ');
    int firstForwardSlash = inputText.indexOf('/');

    String taskName = "";
    // Check if there is a space in the string
    if (firstForwardSlash != -1 && firstSpaceIndex != -1) {
      taskName = inputText.substring(firstSpaceIndex + 1, firstForwardSlash - 1);
    } else if (firstSpaceIndex != -1) {
      taskName = inputText.substring(firstSpaceIndex + 1);
    }

    this.taskName = taskName;
    this.marked = false;
  }

  public boolean markAsDone() {
    this.marked = true;
    return true;
  }

  public boolean unmark() {
    this.marked = false;
    return true;
  }

  @Override
  public String toString() {
    String checkbox = this.marked ? "[X] " : "[ ] ";
    return checkbox + this.taskName;
  }

}
