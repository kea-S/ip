package shep.task;

import java.io.FileWriter;
import java.io.IOException;

import shep.storage.Storage;

/**
 * Represents a task tracked by Shep.
 */
public abstract class Task {
    private String taskName;
    private boolean isMarked;
    private String inputText;
    private String saveFormat;

    public Task(String inputText) {
        this.isMarked = false;
        this.inputText = inputText;
        this.saveFormat = this.inputText + " | " + Boolean.toString(isMarked);
        this.taskName = this.getTaskName(inputText);
    }

    /**
     * Marks this task as done.
     * 
     * @return true if succesfully marked, else false
     */
    public boolean markAsDone() {
        this.isMarked = true;
        this.saveFormat = this.inputText + " | " + Boolean.toString(isMarked);

        return true;
    }

    /**
     * Marks this task as not done.
     * 
     * @return true if successfully marked, else false
     */
    public boolean unmark() {
        this.isMarked = false;
        this.saveFormat = this.inputText + " | " + Boolean.toString(isMarked);

        return true;
    }

    @Override
    public String toString() {
        String checkbox = this.isMarked ? "[X] " : "[ ] ";

        return checkbox + this.taskName;
    }

    /**
     * Saves this task into a storage file.
     * 
     * @param storage The {@link Storage} file to be saved into.
     * @see Storage
     */
    public void saveInto(Storage storage) {
        assert storage != null;

        try (FileWriter fw = new FileWriter(storage.toString(), true)) {
            fw.write(this.saveFormat + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            System.out.println("Couldn't save file: " + this.saveFormat);
        }
    }

    private String getTaskName(String inputText) {
        int firstSpaceIndex = inputText.indexOf(' ');
        int firstForwardSlash = inputText.indexOf('/');

        String taskName = "";

        // Get a taskName if it exists, check if it exists
        if (firstForwardSlash != -1 && firstSpaceIndex != -1 && firstSpaceIndex + 1 < firstForwardSlash - 1) {
            taskName = inputText.substring(firstSpaceIndex + 1, firstForwardSlash - 1).trim();
        } else if (firstSpaceIndex != -1 && this instanceof ToDo) {
            taskName = inputText.substring(firstSpaceIndex + 1).trim();
        }

        if (taskName.equals("")) {
            throw new IllegalArgumentException("The description of a "
                    + this.getClass().getSimpleName() + " cannot be empty!");
        }

        return taskName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Task objTaskCasted = (Task) obj;

        return this.inputText.equals(objTaskCasted.inputText);
    }

}