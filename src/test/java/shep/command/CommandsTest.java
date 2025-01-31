package shep.command;

import org.junit.jupiter.api.Test;

import shep.task.TaskList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CommandsTest {
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void executeCommand_unknownCommand_statesUnkown() {
        // Call the method that prints to System.out
        Commands.executeCommand("remind", new TaskList(), false);

        // Assert that the output is as expected
        assertEquals("\nShep says that command is invalid man, try again.\n".trim(), outContent.toString().trim());
    }

    @Test
    public void executeCommand_saidBye_returnTrue() {
        assertEquals(true, Commands.executeCommand("bye", new TaskList(), false));
    }

}
