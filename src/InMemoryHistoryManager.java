import java.util.*;

public class InMemoryHistoryManager implements HistoryManager{
    private List<Task> history = new ArrayList<>();

    @Override
    public void add(Task task) {
        history.add(task);
    }

    @Override
    public void getHistory() {
        System.out.println("<><><><><>><><><><><><><><><><><><><><>");
        int count = 10;
        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println(history.get(i));
            count--;
            if (count == 0) {
                return;
            }
        }
    }

    public static InMemoryHistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
