import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

  // do this so that zero indexing and task list numbers
  // arent confusing
  @Override
  public Task get(int index) {
    return super.get(index - 1);
  }

  @Override
  public Task remove(int index) {
    return super.remove(index - 1);
  }

  public boolean markTask(int index) {
    this.get(index).markAsDone();
    return true;
  }

  public boolean unmarkTask(int index) {
    this.get(index).unmark();
    return true;
  }

  @Override
  public String toString() {
    String finalList = "";

    // this is because of the zero indexing thing
    for (int i = 1; i <= this.size(); i++) {
      finalList = finalList + String.valueOf(i) + ". " +
      this.get(i).toString() + "\n";
    }

    return finalList;
  }

}
