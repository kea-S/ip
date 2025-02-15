package shep.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import shep.storage.Storage;
import shep.task.TaskList;

public class CommandsTest {

    @Test
    public void executeCommand_unknownCommand_statesUnkown() {
        String expectedOutput = ("\nShep says that command is invalid man, try again.\n");

        String actualOutput = Commands.executeCommand("remind", new TaskList(), false, null);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void executeCommand_saidBye_return() {
        String expectedOutput = "bye";

        String actualOutput = Commands.executeCommand("bye", new TaskList(), false, new Storage("../../../../main/data", "Shep.txt"));

        assertEquals(expectedOutput, actualOutput);
    }

}
