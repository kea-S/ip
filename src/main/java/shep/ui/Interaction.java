package shep.ui;

import shep.storage.Storage;
import shep.task.TaskList;
import shep.command.Commands;

public class Interaction {
    TaskList list;
    Storage storage;

    public Interaction() {
        this.list = new TaskList();
        this.storage = new Storage();

        // load storage into tasklist
        this.storage.writeInto(this.list);

    }

    public String getResponse(String input) {
        String response = Commands.executeCommand(input, this.list, true, this.storage);
        return response;
    }

}
