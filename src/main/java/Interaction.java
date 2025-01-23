import java.util.Scanner;

public class Interaction {
  Scanner userInputScanner;
  TaskList list;

  // constructor
  public Interaction() {
    this.userInputScanner = new Scanner(System.in);
    this.list = new TaskList();
  }

  public void start() {
    while (true) {
      String currUserInputText = this.userInputScanner.nextLine();
      Scanner extractor = new Scanner(currUserInputText);

      String keyword = extractor.next();
      // Check if the next token is an integer
      int intParameters = -1;
      if (extractor.hasNextInt()) {
        intParameters = extractor.nextInt();
      }         

      // Free memory
      extractor.close();

      // Attempt to match the keyword with a command
      Commands command;
      try {
        command = Commands.valueOf(keyword);
      } catch (IllegalArgumentException e) {
        // If the keyword does not match any command, go to default
        command = Commands.normal;
      }

      switch (command) {
        case list:
        System.out.println(list.toString());
        continue;
        case mark:
        if (this.list.markTask(intParameters)) {
          System.out.println("\nShep says he's marked:\n   " + list.get(intParameters).toString() + "\n");
        }
        continue;
        case unmark:
        if (this.list.unmarkTask(intParameters)) {
          System.out.println("\nShep says he's unmarked:\n   " + list.get(intParameters).toString() + "\n");
        }
        continue;
        case bye:
        // Break the loop to exit
        return;
        default:
        // If no valid command is matched, treat it as a task
        Task currTask = new Task(currUserInputText);
        if (this.list.add(currTask)) {
          System.out.println("\nShep says he's added:\n   " + currUserInputText + "\n");
        }
        continue;
      }

    }
  }

}
