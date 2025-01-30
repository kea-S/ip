import java.nio.file.Path;
import java.util.Scanner;

public enum Commands {
    list,
    mark,
    unmark,
    bye,
    todo,
    deadline,
    event,
    delete,
    normal;

    public static boolean executeCommand(String inputText, TaskList list, boolean printTaskAdded) {
        // get the first word
        Scanner extractor = new Scanner(inputText);
        String keyword = extractor.next();

        // Attempt to match the keyword with a command
        Commands command;
        try {
            command = Commands.valueOf(keyword);
        } catch (IllegalArgumentException e) {
            // If the keyword does not match any command, go to default
            command = Commands.normal;
        }

        try {
            switch (command) {
                case list:
                System.out.println(list.toString());
                break;

                case mark:
                int markIndex = -1;
                if (extractor.hasNextInt()) {
                    markIndex = extractor.nextInt();
                }

                if (list.markTask(markIndex)) {
                    System.out.println("\nShep says he's marked:\n   " + list.get(markIndex).toString() + "\n");
                }

                break;

                case unmark:
                int unmarkIndex = -1;
                if (extractor.hasNextInt()) {
                    unmarkIndex = extractor.nextInt();
                }

                if (list.unmarkTask(unmarkIndex)) {
                    System.out.println("\nShep says he's unmarked:\n   " + list.get(unmarkIndex).toString() + "\n");
                }
                break;

                case delete:
                int deleteIndex = -1;
                if (extractor.hasNextInt()) {
                    deleteIndex = extractor.nextInt();
                }

                Task removed = list.remove(deleteIndex);
                System.out.println("\nShep says he's deleted:\n   " + removed.toString() + "\n");
                break;


                case todo:
                Task currToDo = new ToDo(inputText);
                if (list.add(currToDo)) {
                    if (printTaskAdded) {
                        System.out.println("\nShep says he's added:\n   " + list.get(list.size()).toString() + "\n");
                    }
                }
                break;

                case event:
                Task currEvent = new Event(inputText);
                if (list.add(currEvent)) {
                    if (printTaskAdded) {
                        System.out.println("\nShep says he's added:\n   " + list.get(list.size()).toString() + "\n");
                    }
                }
                break;

                case deadline:
                Task currDeadline = new Deadline(inputText);
                if (list.add(currDeadline)) {
                    if (printTaskAdded) {
                        System.out.println("\nShep says he's added:\n   " + list.get(list.size()).toString() + "\n");
                    }
                }
                break;

                default:
                System.out.println("\nShep says that command is inalid man, try again.\n");
                break;

                case bye:
                // Break the loop to exit
                extractor.close();
                return true;
            }
            extractor.close();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        // haven't finished executing
        return false;
    }

}
