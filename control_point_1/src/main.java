import java.io.IOException;
public class main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("Задача 1", "Описание задачи 1");
        PriorityTask priorityTask1 = new PriorityTask("Приоритетная задача 1", "Описание приоритетной задачи 1", PriorityTask.Priority.HIGH);
        taskManager.addTask(task1);
        taskManager.addTask(priorityTask1);
        taskManager.changeTaskStatus(task1, true);
        System.out.println("Задачи:");
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }
        String filename = "tasks.dat";
        try {
            taskManager.saveTasksToFile(filename);
            System.out.println("Задачи успешно сохранены в файл: " + filename);
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении задач: " + e.getMessage());
        }
        TaskManager newTaskManager = new TaskManager();
        try {
            newTaskManager.loadTasksFromFile(filename);
            System.out.println("Задачи успешно загружены из файла: " + filename);
            System.out.println("Загруженные задачи:");
            for (Task task : newTaskManager.getTasks()) {
                System.out.println(task);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка при загрузке задач: " + e.getMessage());
        }
        ImmutableTask immutableTask = new ImmutableTask("Невозможная задача", "Описание", false);
        System.out.println("ImmutableTask: " + immutableTask);
    }
}