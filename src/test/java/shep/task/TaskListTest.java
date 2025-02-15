package shep.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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

    @Test
    public void checkDuplicates_ExistingTask_true() {
        boolean expectedResult = true;

        TaskList actualTaskList = new TaskList();
        actualTaskList.add(new ToDo("Write Tests"));


        Task actualTask = new ToDo("Write Tests");
        boolean actualResult = actualTaskList.checkDuplicates(actualTask);

        assertEquals(expectedResult, actualResult);
    }
    
}
