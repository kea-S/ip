public abstract class Task {
  private String taskName;
  private boolean marked;

  public Task(String taskName) {
    this.taskName = taskName;
    this.marked = false;
  }

  @Override
  public String toString() {
    String checkbox = this.marked ? "[X] " : "[ ] ";
    return checkbox + this.taskName;
  }

}
