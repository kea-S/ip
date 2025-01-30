import java.util.Scanner;

public class Interaction {
    TaskList list;
    Storage storage;

    public Interaction() {
        this.list = new TaskList();
        this.storage = new Storage();
    }

    public void start() {
        // load storage into tasklist
        this.storage.writeInto(this.list);

        Scanner userInputScanner = new Scanner(System.in);
        boolean saidBye = false;
        while (!saidBye) {
            String currUserInputText = userInputScanner.nextLine();
            saidBye = Commands.executeCommand(currUserInputText, this.list, true);
        }
        userInputScanner.close();

        // save the taskList into storage, I'm not too sure about this
        this.storage.readFrom(this.list);
    }

}
