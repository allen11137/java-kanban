public interface TaskManager {
    void getAllTasks();
    void removeAll();
    void getById(int id);
    void createTask(Task task);
    void updateTask(Task task);
    void removeById(int id);
    void getAllSubtaskByEpicId(int id);
    void getHistory();
}
