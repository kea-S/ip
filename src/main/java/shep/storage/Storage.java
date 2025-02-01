package shep.storage;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.io.FileWriter;
import java.util.Iterator;

import shep.task.TaskList;
import shep.task.Task;
import shep.command.Commands;

/**
 *  Represents the storage file containing saved {@link Task}s.
 *  A <code>Storage</code> object points to the file Shep.txt under directory ../data/
 */
public class Storage {
    private Path filePath;

    public Storage() {
        // load or initialise data directory
        Path dataDirectoryPath = Paths.get("../data");
        if (!Files.exists(dataDirectoryPath)) {
            try {
                Files.createDirectories(dataDirectoryPath);
                System.out.println("I noticed that this is the first time we're talking," +
                        "so I've made a directory to store our tasks - ");
            } catch (IOException e) {
                System.out.println("Directory not created");
                e.printStackTrace();
            }
        }

        // initialise data file if not there
        Path dataFilePath = Paths.get("../data/Shep.txt");
        if (!Files.exists(dataFilePath)) {
            try {
                Files.createFile(dataFilePath);
                System.out.println("I have also created the file to store our tasks! \n");
            } catch (IOException e) {
                System.out.println("File not created");
                e.printStackTrace();
            }
        }

        this.filePath = dataFilePath;
    }

    /**
     * Adds saved {@link Task}s in the storage file to the specified {@link TaskList} taskList.
     * Mutates specified {@link TaskList} taskList.
     *
     * @param tasklist The {@link TaskList} to be written into.
     * @see Task
    */
    public void writeInto(TaskList tasklist) {
        // load filePath contents as a string
        List<String> fileContents = null;
        try {
            fileContents = Files.readAllLines(this.filePath);
        } catch (IOException e) {
            System.out.println("Failed to read file");
            e.printStackTrace();
        }

        // read the file and add to list, remember list is 1-indexed
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
            Commands.executeCommand(command, tasklist, false);

            if (isMarked) {
                tasklist.markTask(index + 1);   // taskList is 1 indexed
            }

            index++;
        }

    }

    /**
     * Saves {@link Task}s in the {@link TaskList} taskList into the storage file.
     *
     * @param tasklist The {@link TaskList} to be read from.
     * @see Task
    */
    public void readFrom(TaskList tasklist) {
        // overwrite previous taskList to be empty, no need to add anything to the file
        try (FileWriter fileWriter = new FileWriter(this.toString(), false)) {
        } catch (IOException e) {
            System.err.println("An error occurred while clearing the file: " + e.getMessage());
        }

        // write the tasklist to usual filepath
        Iterator<Task> taskIterator = tasklist.iterator();
        while (taskIterator.hasNext()) {    // I don't know a good way to do this without using getters
            taskIterator.next().saveInto(this); // should the file be responsible for saving itself?
        }
    }

    @Override
    public String toString() {
        return this.filePath.toString();
    }

}