package shep.command;

import java.util.Scanner;

import shep.storage.Storage;
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
     * @return {@link String} response tied to command.
     */
    public static String executeCommand(String inputText, TaskList list, boolean printTaskAdded, Storage storage) {
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

        String response = "";

        try {
            switch (command) {
                case list:
                response =  list.toString();
                break;

                case mark:
                int markIndex = -1;
                if (extractor.hasNextInt()) {
                    markIndex = extractor.nextInt();
                }

                if (list.markTask(markIndex)) {
                    response = "\nShep says he's marked:\n   " + list.get(markIndex).toString() + "\n";
                }

                break;

                case unmark:
                int unmarkIndex = -1;
                if (extractor.hasNextInt()) {
                    unmarkIndex = extractor.nextInt();
                }

                if (list.unmarkTask(unmarkIndex)) {
                    response = "\nShep says he's unmarked:\n   " + list.get(unmarkIndex).toString() + "\n";
                }
                break;

                case find:
                if (extractor.hasNext()) {
                    String word = extractor.next();
                    response = list.findTasks(word).toString();
                }
                break;

                case delete:
                int deleteIndex = -1;
                if (extractor.hasNextInt()) {
                    deleteIndex = extractor.nextInt();
                }

                Task removed = list.remove(deleteIndex);
                response = ("\nShep says he's deleted:\n   " + removed.toString() + "\n");
                break;


                case todo:
                Task currToDo = new ToDo(inputText);
                if (list.add(currToDo)) {
                    if (printTaskAdded) {
                        response = ("\nShep says he's added:\n   " + list.get(list.size()).toString() + "\n");
                    }
                }
                break;

                case event:
                Task currEvent = new Event(inputText);
                if (list.add(currEvent)) {
                    if (printTaskAdded) {
                        response = ("\nShep says he's added:\n   " + list.get(list.size()).toString() + "\n");
                    }
                }
                break;

                case deadline:
                Task currDeadline = new Deadline(inputText);
                if (list.add(currDeadline)) {
                    if (printTaskAdded) {
                        response = ("\nShep says he's added:\n   " + list.get(list.size()).toString() + "\n");
                    }
                }
                break;

                case bye:
                // Break the loop to exit
                extractor.close();
                // save the taskList into storage, I'm not too sure about this
                storage.readFrom(list);

                response = "bye";
                break;

                default:
                response = ("\nShep says that command is invalid man, try again.\n");
                break;
            }

            extractor.close();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        // if return "" failure, need to throw some things here better
        return response;
    }

}
