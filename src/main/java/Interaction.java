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

      // get the first word
      Scanner extractor = new Scanner(currUserInputText);
      String keyword = extractor.next();

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
        break;

        case mark:
        int markIndex = -1;
        if (extractor.hasNextInt()) {
          markIndex = extractor.nextInt();
        }

        if (this.list.markTask(markIndex)) {
          System.out.println("\nShep says he's marked:\n   " + list.get(markIndex).toString() + "\n");
        }

        break;

        case unmark:
        int unmarkIndex = -1;
        if (extractor.hasNextInt()) {
          unmarkIndex = extractor.nextInt();
        }

        if (this.list.unmarkTask(unmarkIndex)) {
          System.out.println("\nShep says he's unmarked:\n   " + list.get(unmarkIndex).toString() + "\n");
        }
        break;

        case todo:
        Task currToDo = new ToDo(currUserInputText);
        if (this.list.add(currToDo)) {
          System.out.println("\nShep says he's added:\n   " + list.get(list.size()).toString() + "\n");
        }
        break;

        case event:
        Task currEvent = new Event(currUserInputText);
        if (this.list.add(currEvent)) {
          System.out.println("\nShep says he's added:\n   " + list.get(list.size()).toString() + "\n");
        }
        break;

        case deadline:
        Task currDeadline = new Deadline(currUserInputText);
        if (this.list.add(currDeadline)) {
          System.out.println("\nShep says he's added:\n   " + list.get(list.size()).toString() + "\n");
        }
        break;

        default:
        System.out.println("\nadd a valid task type\n");
        break;

        case bye:
        // Break the loop to exit
        extractor.close();
        return;
      }
      extractor.close();

    }
  }

}
