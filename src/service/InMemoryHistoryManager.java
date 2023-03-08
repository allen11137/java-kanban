package service;

import model.Task;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history = new LinkedList<>();
    private static final int COUNT = 10;

    @Override
    public void add(Task task) {
        history.add(task);
    }

    @Override
    public List<Task> getHistory() {
        System.out.println("<><><><><>><><><><><><><><><><><><><><>");
        List<Task> tasks = new ArrayList<>();
        for (int i = history.size() - 1; i >= 0; i--) {
            tasks.add(history.get(i));
            if (history.size() > COUNT && i + COUNT < history.size()) {
                history.remove(i);
            }
        }
        return tasks;
    }

    public static InMemoryHistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
