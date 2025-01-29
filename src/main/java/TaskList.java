import java.util.List;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    // do this so that zero indexing and task list numbers
    // arent confusing
    @Override
    public Task get(int index) {
        return super.get(index - 1);
    }

    @Override
    public Task remove(int index) {
        return super.remove(index - 1);
    }

    public boolean markTask(int index) {
        this.get(index).markAsDone();
        return true;
    }

    public boolean unmarkTask(int index) {
        this.get(index).unmark();
        return true;
    }

    @Override
    public String toString() {
        String finalList = "";

        // this is because of the zero indexing thing
        for (int i = 1; i <= this.size(); i++) {
            finalList = finalList + String.valueOf(i) + ". " +
            this.get(i).toString() + "\n";
        }

        return finalList;
    }

    public void readFile(Path filePath) {
        // load filePath contents as a string
        List<String> fileContents = null;
        try {
            fileContents = Files.readAllLines(filePath);
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
            Commands.executeCommand(command, this, filePath, false);

            if (isMarked) {
                this.markTask(index + 1);   // taskList is 1 indexed
            }

            index++;
        }

    }

    public void writeToFile(Path filePath) {
        // overwrite previous taskList to be empty, no need to add anything to the file
        try (FileWriter fileWriter = new FileWriter(filePath.toString(), false)) {
        } catch (IOException e) {
            System.err.println("An error occurred while clearing the file: " + e.getMessage());
        }

        // write the tasklist to usual filepath
        Iterator<Task> taskIterator = this.iterator();
        while (taskIterator.hasNext()) {
            taskIterator.next().save(filePath);
        }
    }

}
