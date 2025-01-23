public class Task {
  private String taskName;
  private boolean marked;

  public Task(String taskName) {
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
