package shep.task;

import java.util.ArrayList;
import java.util.List;

import shep.command.Commands;
import shep.storage.Storage;

/**
 * Represents a tasklist, which is a list of {@link Task} objects.
 * A <code>Tasklist</code> is 1-indexed.
 *
 * @see Task
 */
public class TaskList extends ArrayList<Task> {
    private Storage storage;

    public TaskList() {
        super();
    }

    /**
     * Adds saved {@link Task}s in the storage file to the specified {@link TaskList} taskList.
     *
     * @param tasklist The {@link Storage} to be read from
     * @see Task
     * @see Storage
     */
    public TaskList(Storage storage) {
        this.storage = storage;

        List<String> fileContents = storage.read();

        process(fileContents);
    }

    @Override
    public Task get(int index) {
        return super.get(index - 1);
    }

    @Override
    public Task remove(int index) {
        return super.remove(index - 1);
    }

    /**
     * Marks {@link Task} at the index as done.
     *
     * @param index <code>int</code> value pointing to a {@link Task} in the TaskList
     */
    public boolean markTask(int index) {
        this.get(index).markAsDone();

        return true;
    }

    /**
     * Marks {@link Task} at the index as not done
     *
     * @param index <code>int</code> value pointing to a {@link Task} in the TaskList
     */
    public boolean unmarkTask(int index) {
        this.get(index).unmark();

        return true;
    }

    @Override
    public String toString() {
        String finalList = "";

        // this is so that 1-index logic makes sense
        for (int i = 1; i <= this.size(); i++) {
            finalList = finalList + String.valueOf(i) + ". "
            + this.get(i).toString() + "\n";
        }

        return finalList;
    }

     public TaskList findTasks(String word) {
        assert word != "";

        TaskList matchingTasks = new TaskList();

        for (Task task : this) {
            if (task.toString().contains(word)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

    private void process(List<String> fileContents) {
        int index = 0;
        while (index < fileContents.size()) {
            // split the saveFormatted text
            String saveFormat = fileContents.get(index);
            String[] parts = saveFormat.split(" \\| ");

            // Check if the split was successful and print the parts
            if (parts.length != 2) {
                System.out.println("saveFormat not followed: (task) | (marked)");
            }

            boolean isMarked = Boolean.parseBoolean(parts[1]);
            String command = parts[0];

            Commands.executeCommand(command, this, false, this.storage);

            if (isMarked) {
                this.markTask(index + 1);   // fileContents is 0 indexed while TaskList is 1 indexed
            }

            index++;
        }

    }

}
