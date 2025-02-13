package shep.ui;

import shep.storage.Storage;
import shep.task.TaskList;
import shep.command.Commands;

public class Interaction {
    TaskList list;
    Storage storage;

    public Interaction() {
        this.storage = new Storage();
        this.list = new TaskList(storage);
    }

    public String getResponse(String input) {
        String response = Commands.executeCommand(input, this.list, true, this.storage);

        return response;
    }

}
