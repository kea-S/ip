package shep.command;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import shep.storage.Storage;
import shep.task.Deadline;
import shep.task.Event;
import shep.task.Task;
import shep.task.TaskList;
import shep.task.ToDo;

/**
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
        Scanner extractor = new Scanner(inputText);
        String keyword = extractor.next();
        Commands command = getCommand(keyword);

        String response = "";
        try {
            switch (command) {
            case list:
                response = handleListCommand(list);
                break;
            case mark:
                response = handleMarkCommand(extractor, list);
                break;
            case unmark:
                response = handleUnmarkCommand(extractor, list);
                break;
            case find:
                response = handleFindCommand(extractor, list);
                break;
            case delete:
                response = handleDeleteCommand(extractor, list);
                break;
            case todo:
                response = handleTodoCommand(inputText, list, printTaskAdded);
                break;
            case event:
                response = handleEventCommand(inputText, list, printTaskAdded);
                break;
            case deadline:
                response = handleDeadlineCommand(inputText, list, printTaskAdded);
                break;
            case bye:
                response = handleByeCommand(extractor, list, storage);
                break;
            default:
                response = "Shep says that command is invalid man, try again.";
                break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        } finally {
            extractor.close();
        }

        assert !response.isEmpty();
        return response;
    }

    private static Commands getCommand(String keyword) {
        try {
            return Commands.valueOf(keyword);
        } catch (IllegalArgumentException e) {
            return Commands.normal;
        }
    }

    private static String handleListCommand(TaskList list) {
        return list.toString();
    }

    private static String handleMarkCommand(Scanner extractor, TaskList list) {
        int markIndex;
        if (extractor.hasNextInt()) {
            markIndex = extractor.nextInt();
        } else {
            markIndex = -1;
        }

        try {
            if (list.markTask(markIndex)) {
                return "Shep says he's marked:\n   " + list.get(markIndex).toString();
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

        return "Invalid index for mark command.";
    }

    private static String handleUnmarkCommand(Scanner extractor, TaskList list) {
        int unmarkIndex;
        if (extractor.hasNextInt()) {
            unmarkIndex = extractor.nextInt();
        } else {
            unmarkIndex = -1;
        }

        try {
            if (list.unmarkTask(unmarkIndex)) {
                return "Shep says he's unmarked:\n   " + list.get(unmarkIndex).toString();
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return "Invalid index for unmark command.";
    }

    private static String handleFindCommand(Scanner extractor, TaskList list) {
        if (extractor.hasNext()) {
            String word = extractor.next();
            return list.findTasks(word).toString();
        }
        return "No search term provided for find command.";
    }

    private static String handleDeleteCommand(Scanner extractor, TaskList list) {
        int deleteIndex;
        if (extractor.hasNextInt()) {
            deleteIndex = extractor.nextInt();
        } else {
            deleteIndex = -1;
        }

        try {
            if (deleteIndex != -1) {
                Task removed = list.remove(deleteIndex);
                if (removed != null) {
                    return "Shep says he's deleted:\n   " + removed.toString();
                }
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        return "Invalid index for delete command.";
    }

    private static String handleTodoCommand(String inputText, TaskList list, boolean printTaskAdded) {
        try {
            Task currToDo = new ToDo(inputText);
            if (list.add(currToDo)) {
                if (printTaskAdded) {
                    return "Shep says he's added:\n   " + list.get(list.size()).toString();
                } else {
                    return "";
                }
            } else {
                return "Failed to add Task! Check if this task already exists.";
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    private static String handleEventCommand(String inputText, TaskList list, boolean printTaskAdded) {
        try {
            Task currEvent = new Event(inputText);
            if (list.add(currEvent)) {
                if (printTaskAdded) {
                    return "Shep says he's added:\n   " + list.get(list.size()).toString();
                } else {
                    return "";
                }
            } else {
                return "Failed to add Task! Check if this task already exists.";
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Dates must be of the format yyyy-MM-dd (e.g. 2025-02-21)";
        }
    }

    private static String handleDeadlineCommand(String inputText, TaskList list, boolean printTaskAdded) {
        try {
            Task currDeadline = new Deadline(inputText);
            if (list.add(currDeadline)) {
                if (printTaskAdded) {
                    return "Shep says he's added:\n   " + list.get(list.size()).toString();
                } else {
                    return "";
                }
            } else {
                return "Failed to add Task! Check if this task already exists.";
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Dates must be of the format yyyy-MM-dd (e.g. 2025-02-21)";
        }
    }

    private static String handleByeCommand(Scanner extractor, TaskList list, Storage storage) {
        extractor.close();
        storage = new Storage(list);
        return "bye";
    }

}