public class Main {

    public static void main(String[] args) {
        TaskManager manager = new InMemoryTaskManager();
        Task task1 = new Task("first", "info 1");
        manager.createTask(task1);
        Task task2 = new Task("second", "info 2");
        manager.createTask(task2);
        Epic epic1 = new Epic("epic1", "info epic 1" );
        manager.createTask(epic1);
        Subtask subtask1 = new Subtask(epic1.getId(), "subtask 1", "info subtask 1");
        manager.createTask(subtask1);
        Subtask subtask2 = new Subtask(epic1.getId(), "subtask 2", "info subtask 2");
        manager.createTask(subtask2);
        Epic epic2 = new Epic("epic 2", "info epic 2");
        manager.createTask(epic2);
        Subtask subtask3 = new Subtask(epic2.getId(), "subtask 3", "info subtask 3");
        manager.createTask(subtask3);
        manager.getAllTasks();

        task1.setStatus(Status.DONE);
        manager.updateTask(task1);
        manager.getAllTasks();

        subtask1.setStatus(Status.DONE);
        subtask3.setStatus(Status.DONE);
        manager.updateTask(subtask1);
        manager.updateTask(subtask3);
        manager.getAllTasks();

        manager.removeById(task1.getId());
        manager.removeById(epic1.getId());
        manager.getAllTasks();
        manager.getById(2);
        manager.getById(2);
        manager.getById(2);
        manager.getById(4);
        manager.getById(4);
        manager.getById(5);
        manager.getById(7);
        manager.getById(7);

        manager.getHistory();
    }
}
