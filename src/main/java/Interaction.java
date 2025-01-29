import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Interaction {
    TaskList list;
    Path filePath;

    public Interaction() {
        this.list = new TaskList();

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
                System.out.println("As well as the file to store our tasks! \n");
            } catch (IOException e) {
                System.out.println("File not created");
                e.printStackTrace();
            }
        } 
        this.filePath = dataFilePath;

        // read the file
        this.list.readFile(dataFilePath);
    }

    public void start() {
        Scanner userInputScanner = new Scanner(System.in);
        boolean saidBye = false;
        while (!saidBye) {
            String currUserInputText = userInputScanner.nextLine();
            saidBye = Commands.executeCommand(currUserInputText, this.list, this.filePath, true);
        }
        userInputScanner.close();
    }

}
