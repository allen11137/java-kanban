import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
    private static Integer ids = 1;
    private static Map<Integer,Task> tasks = new HashMap<>();
    private static Map<Integer, Epic> epics = new HashMap<>();
    private static Map<Integer, Subtask> subtasks = new HashMap<>();

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

    public void removeAll() {
        tasks.clear();
        epics.clear();
        subtasks.clear();
    }

    public void getById(int id) {
        if (tasks.get(id) != null) {
            System.out.println(tasks.get(id));
        }
        if (epics.get(id) != null) {
            System.out.println(epics.get(id));
        }
        if (subtasks.get(id) != null) {
            System.out.println(subtasks.get(id));
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
            epic.setSubtasks((Subtask) task);
        }
        ids++;
    }

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

    public void getAllSubtaskByEpicId(int id) {
        Epic epic = epics.get(id);
        List<Subtask> subtasks = epic.getSubtasks();
        for (Subtask subtask : subtasks) {
            System.out.println(subtask);
        }
    }
}
