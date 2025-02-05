package shep.command;

import java.util.Scanner;

import shep.task.Deadline;
import shep.task.Event;
import shep.task.Task;
import shep.task.TaskList;
import shep.task.ToDo;

/*
 * Represents the parser for Shep, parsing user CLI input text.
 * Contains all recognised commands recognised by Shep at its current version.
 */
public enum Commands {
    list,
    mark,
    unmark,
    find,
    bye,
    todo,
    deadline,
    event,
    delete,
    normal;

    /**
     * Parses user CLI input text and executes corresponding command.
     * If the command is invalid, prompt user to retry.
     * If the command include {@link String} "bye", return true.
     *
     * @param inputText Input text from the CLI to be parsed.
     * @param list {@link TaskList} to be modified by a command execution.
     * @param printTaskAdded Whether or not to output to the CLI Shep's messages on execution of commands that modify the list.
     * @return true if the inputText given is bye. Else false.
     */
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

            case find:
                if (extractor.hasNext()) {
                    String word = extractor.next();
                    System.out.println(list.findTasks(word));
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

            case bye:
                // Break the loop to exit
                extractor.close();
                return true;

            default:
                System.out.println("\nShep says that command is invalid man, try again.\n");
                break;
            }


            extractor.close();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        // haven't finished executing
        return false;
    }

}