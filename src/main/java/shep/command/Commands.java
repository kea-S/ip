package shep.command;

import java.util.Scanner;
import java.time.format.DateTimeParseException;

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

        assert command instanceof Commands;

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
                    response = "Shep says he's marked:\n   " + list.get(markIndex).toString();
                }

                break;

                case unmark:
                int unmarkIndex = -1;
                if (extractor.hasNextInt()) {
                    unmarkIndex = extractor.nextInt();
                }

                if (list.unmarkTask(unmarkIndex)) {
                    response = "Shep says he's unmarked:\n   " + list.get(unmarkIndex).toString();
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

                assert deleteIndex != -1;

                Task removed = list.remove(deleteIndex);

                assert removed != null;

                response = "Shep says he's deleted:\n   " + removed.toString();
                break;

                case todo:

                Task currToDo;

                try {
                    currToDo = new ToDo(inputText);
                } catch (IllegalArgumentException e) {
                    response = e.getMessage();
                    break;
                }

                if (list.add(currToDo)) {
                    if (printTaskAdded) {
                        response = "Shep says he's added:\n   " + list.get(list.size()).toString();
                    }
                } else {
                    response = "Failed to add Task! Check if this task already exists";
                }
                break;

                case event:

                Task currEvent;
                try {
                    currEvent = new Event(inputText);
                } catch (IllegalArgumentException e) {
                    response = e.getMessage();
                    break;
                } catch (DateTimeParseException e) {
                    response = "Dates must be of the format yyyy-MM-dd (e.g. 2025-02-21)";
                    break;
                }

                if (list.add(currEvent)) {
                    if (printTaskAdded) {
                        response = "Shep says he's added:\n   " + list.get(list.size()).toString();
                    }
                } else {
                    response = "Failed to add Task! Check if this task already exists";
                }
                break;

                case deadline:

                Task currDeadline;
                try {
                    currDeadline = new Deadline(inputText);
                } catch (IllegalArgumentException e) {
                    response = e.getMessage();
                    break;
                } catch (DateTimeParseException e) {
                    response = "Dates must be of the format yyyy-MM-dd (e.g. 2025-02-21)";
                    break;
                }


                if (list.add(currDeadline)) {
                    if (printTaskAdded) {
                        response = "Shep says he's added:\n   " + list.get(list.size()).toString();
                    }
                } else {
                    response = "Failed to add Task! Check if this task already exists";
                }
                break;

                case bye:
                extractor.close();

                storage = new Storage(list);

                response = "bye";
                break;

                default:
                response = "Shep says that command is invalid man, try again.";
                break;
            }

            extractor.close();
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        assert !response.isEmpty();

        return response;
    }

    }
