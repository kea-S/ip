package shep.task;

import java.io.FileWriter;
import java.io.IOException;

import shep.storage.Storage;

/**
 * Represents a task tracked by Shep.
 */
public abstract class Task {
    private String taskName;
    private boolean marked;
    private String inputText;
    private String saveFormat;

    public Task(String inputText) {
        int firstSpaceIndex = inputText.indexOf(' ');
        int firstForwardSlash = inputText.indexOf('/');

        String taskName = "";

        // Check if there is a space in the string

        if (firstForwardSlash != -1 && firstSpaceIndex != -1) {
            taskName = inputText.substring(firstSpaceIndex + 1, firstForwardSlash - 1);
        } else if (firstSpaceIndex != -1) {
            taskName = inputText.substring(firstSpaceIndex + 1);
        } else {  // no space detected, wrong input
            throw new IllegalArgumentException("The description of a " + 
                this.getClass().getSimpleName() + " cannot be empty!");
        }

        this.marked = false;
        this.inputText = inputText;
        this.saveFormat = this.inputText + " | " + Boolean.toString(marked);
        this.taskName = taskName;
    }

    /**
     * Marks this task as done.
     * 
     * @return true if succesfully marked, else false
     */
    public boolean markAsDone() {
        this.marked = true;
        this.saveFormat = this.inputText + " | " + Boolean.toString(marked);
        return true;
    }

    /**
     * Marks this task as not done.
     * 
     * @return true if succesfully marked, else false
     */
    public boolean unmark() {
        this.marked = false;
        this.saveFormat = this.inputText + " | " + Boolean.toString(marked);
        return true;
    }

    @Override
    public String toString() {
        String checkbox = this.marked ? "[X] " : "[ ] ";
        return checkbox + this.taskName;
    }

    /**
     * Saves this task into a storage file.
     * 
     * @param storage The {@link Storage} file to be saved into.
     * @see Storage
     */
    public void saveInto(Storage storage) {
        try (FileWriter fw = new FileWriter(storage.toString(), true)) {
			fw.write(this.saveFormat + System.lineSeparator());
			fw.close();
		} catch (IOException e) {
            System.out.println("Couldn't save file: " + this.saveFormat);
			e.printStackTrace();
		}
    }

}
