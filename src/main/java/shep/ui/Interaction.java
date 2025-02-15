package shep.ui;

import shep.command.Commands;
import shep.storage.Storage;
import shep.task.TaskList;

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