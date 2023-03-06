import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private Integer ids = 1;
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, Subtask> subtasks = new HashMap<>();
    private HistoryManager historyManager = InMemoryHistoryManager.getDefaultHistory();

    @Override
    public void getAllTasks() {
        for (Task task : tasks.values()) {
            System.out.println(task);
        }
        for (Epic epic : epics.values()) {
            System.out.println(epic);
        }
        for (Subtask subtask : subtasks.values()) {
            System.out.println(subtask);
        }
        System.out.println("--------------------------------------------");
    }

    @Override
    public void removeAll() {
        tasks.clear();
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void getById(int id) {
        if (tasks.get(id) != null) {
            Task task = tasks.get(id);
            int i = task.getCount() + 1;
            task.setCount(i);
            System.out.println(task);
            historyManager.add(task);
        }
        if (epics.get(id) != null) {
            Epic epic = epics.get(id);
            int i = epic.getCount() + 1;
            epic.setCount(i);
            System.out.println(epic);
            historyManager.add(epic);
        }
        if (subtasks.get(id) != null) {
            Subtask subtask = subtasks.get(id);
            int i = subtask.getCount() + 1;
            subtask.setCount(i);
            System.out.println(subtask);
            historyManager.add(subtask);
        }
    }

    public void createTask(Task task) {
        if (task instanceof Task) {
            task.setId(ids);
            task.setStatus(Status.NEW);
            tasks.put(ids, task);
        } else if (task instanceof Epic) {
            task.setId(ids);
            task.setStatus(Status.NEW);
            epics.put(ids, (Epic) task);
        } else if (task instanceof Subtask) {
            task.setId(ids);
            task.setStatus(Status.NEW);
            subtasks.put(ids, (Subtask) task);

            int epicId = ((Subtask) task).getEpicId();
            Epic epic = epics.get(epicId);
            epic.setStatus(Status.NEW);
            epic.setSubtasks((Subtask) task);
        }
        ids++;
    }

    @Override
    public void updateTask(Task task) {
        if (task instanceof Task) {
            tasks.put(task.getId(), task);
        } else if (task instanceof Epic) {
            epics.put(task.getId(), (Epic) task);
        } else if (task instanceof Subtask) {
            subtasks.put(task.getId(), (Subtask) task);
            int epicId = ((Subtask) task).getEpicId();
            Epic epic = epics.get(epicId);
            int countSubtasks = epic.getSubtasks().size();
            int statusDoneSubtasks = 0;
            for (Subtask subtask : epic.getSubtasks()) {
                if (subtask.getStatus().equals(Status.DONE)) {
                    statusDoneSubtasks++;
                }
            }
            if (statusDoneSubtasks == countSubtasks) {
                epic.setStatus(Status.DONE);
            }
        }
    }

    @Override
    public void removeById(int id) {
        if (tasks.get(id) != null) {
            tasks.remove(id);
        }
        if (epics.get(id) != null) {
            Epic epic = epics.get(id);
            List<Subtask> subtasksList = epic.getSubtasks();
            for (Subtask s : subtasksList) {
                subtasks.remove(s.getId());
            }
            epics.remove(id);
        }
        if (subtasks.get(id) != null) {
            subtasks.remove(id);
        }
    }

    @Override
    public void getAllSubtaskByEpicId(int id) {
        Epic epic = epics.get(id);
        List<Subtask> subtasks = epic.getSubtasks();
        for (Subtask subtask : subtasks) {
            System.out.println(subtask);
        }
    }

    @Override
    public void getHistory() {
        historyManager.getHistory();
    }
}
