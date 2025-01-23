import java.util.Scanner;

public class Interaction {
  Scanner userInputScanner = new Scanner(System.in);
  TaskList list = new TaskList();

  // constructor
  public Interaction() {}

  public void start() {
    while (true) {
      String currUserInputText = this.userInputScanner.nextLine();
      if (currUserInputText.equals("bye")) {
        break;
      }
      if (currUserInputText.equals("list")) {
        System.out.println(list.toString());
        continue;
      }

      Task currTask = new Task(currUserInputText);
      boolean success = this.list.add(currTask);

      if (success) {
        System.out.println("\nShep says he's added:\n   " + currUserInputText + "\n");
      }
    }

  }

}
