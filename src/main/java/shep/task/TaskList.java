package shep.task;

import java.util.ArrayList;

/**
 * Represents a tasklist, which is a list of {@link Task} objects.
 * A <code>Tasklist</code> is 1-indexed.
 *
 * @see Task
 */
public class TaskList extends ArrayList<Task> {
    @Override
    public Task get(int index) {
        return super.get(index - 1);
    }

    @Override
    public Task remove(int index) {
        return super.remove(index - 1);
    }

    /**
     * Marks {@link Task} at the index as done.
     *
     * @param index <code>int</code> value pointing to a {@link Task} in the TaskList
     */
    public boolean markTask(int index) {
        this.get(index).markAsDone();
        return true;
    }

    /**
     * Marks {@link Task} at the index as not done
     *
     * @param index <code>int</code> value pointing to a {@link Task} in the TaskList
     */
    public boolean unmarkTask(int index) {
        this.get(index).unmark();
        return true;
    }

    @Override
    public String toString() {
        String finalList = "";

        // this is because of the zero indexing thing
        for (int i = 1; i <= this.size(); i++) {
            finalList = finalList + String.valueOf(i) + ". "
                    + this.get(i).toString() + "\n";
        }

        return finalList;
    }

     public TaskList findTasks(String word) {
        assert word != "";

        TaskList matchingTasks = new TaskList();

        for (Task task : this) {
            if (task.toString().contains(word)) {
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }

}
