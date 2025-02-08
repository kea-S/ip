package shep.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    
    @Test
    public void markTask_InRange_taskMarked() {
        Task expectedResult = new ToDo("Write Tests");
        expectedResult.markAsDone();

        TaskList actualTaskList = new TaskList();
        actualTaskList.add(new ToDo("Write Tests"));
        actualTaskList.markTask(1);

        assertEquals(expectedResult.toString(), actualTaskList.get(1).toString());
    }

    @Test
    public void removeTask_InRange_taskRemoved() {
        boolean expectedResult = new TaskList().isEmpty();

        TaskList actualTaskList = new TaskList();
        actualTaskList.add(new ToDo("Write Tests"));
        actualTaskList.remove(1);

        assertEquals(expectedResult, actualTaskList.isEmpty());
    }

    
}
