package shep.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import shep.task.Task;
import shep.task.TaskList;


/**
 *  Represents the storage file containing saved {@link Task}s.
 *  A <code>Storage</code> object points to the file Shep.txt under directory ../data/
 */
public class Storage {
    private String DEFAULT_DIRECTORY = "../data";
    private String DEFAULT_PATH = "../data/Shep.txt";
    private Path filePath;
    private TaskList taskList;

    public Storage() {
        createDataDirectory(DEFAULT_DIRECTORY);

        createDataFile(DEFAULT_PATH);

        this.filePath = Paths.get(DEFAULT_PATH);
    }

    public Storage(String directory, String file) {
        createDataDirectory(directory);

        String path = directory + "/" + file;

        createDataFile(path);

        this.filePath = Paths.get(path);
    }


    public Storage(TaskList tasklist) {
        this.taskList = tasklist;

        createDataDirectory(DEFAULT_DIRECTORY);

        createDataFile(DEFAULT_PATH);

        this.filePath = Paths.get(DEFAULT_PATH);

        readFrom();

    }

    private void createDataDirectory(String directory) {
        Path dataDirectoryPath = Paths.get(directory);
        if (!Files.exists(dataDirectoryPath)) {
            try {
                Files.createDirectories(dataDirectoryPath);
                System.out.println("I noticed that this is the first time we're talking,"
                    + "so I've made a directory to store our tasks - ");
            } catch (IOException e) {
                System.out.println("Directory not created");
                e.printStackTrace();
            }
        }
    }

    private void createDataFile(String file) {
        Path dataFilePath = Paths.get(file);
        if (!Files.exists(dataFilePath)) {
            try {
                Files.createFile(dataFilePath);
                System.out.println("I have also created the file to store our tasks! \n");
            } catch (IOException e) {
                System.out.println("File not created");
                e.printStackTrace();
            }
        }

    }

    
    public List<String> read() {
        List<String> fileContents = null;

        try {
            fileContents = Files.readAllLines(this.filePath);
        } catch (IOException e) {
            System.out.println("Failed to read file");
            e.printStackTrace();
        }

        assert fileContents != null;

        return fileContents;
    }

    /**
     * Saves {@link Task}s in the {@link TaskList} taskList into the storage file.
     *
     * @param tasklist The {@link TaskList} to be read from.
     * @see Task
     */
    private void readFrom() {
        // overwrite previous taskList to be empty, no need to add anything to the file
        try {
            FileWriter fileWriter = new FileWriter(this.toString(), false);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred while clearing the file: " + e.getMessage());
        }

        // write the tasklist to usual filepath
        Iterator<Task> taskIterator = this.taskList.iterator();
        while (taskIterator.hasNext()) {
            taskIterator.next().saveInto(this);
        }
    }

    @Override
    public String toString() {
        return this.filePath.toString();
    }

}
