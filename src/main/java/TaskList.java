import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {

  @Override
  public String toString() {
    String finalList = "";

    for (int i = 0; i < this.size(); i++) {
      finalList = finalList + String.valueOf(i + 1) + ". " +
      this.get(i).toString() + "\n";
    }

    return finalList;
  }

}
