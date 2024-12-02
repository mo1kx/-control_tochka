import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Класс Task
class Task implements Comparable<Task> {
    protected String title;
    protected String description;
    protected boolean isCompleted;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.isCompleted = false;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    @Override
    public int compareTo(Task other) {
        return 0; // По умолчанию, без сортировки
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}

// Подкласс PriorityTask
class PriorityTask extends Task {
    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    private Priority priority;

    public PriorityTask(String title, String description, Priority priority) {
        super(title, description);
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public int compareTo(Task other) {
        if (other instanceof PriorityTask) {
            return this.priority.compareTo(((PriorityTask) other).priority);
        }
        return 0;
    }

    @Override
    public String toString() {
        return "PriorityTask{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                ", priority=" + priority +
                '}';
    }
}

// Класс TaskManager
class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public void changeTaskStatus(Task task, boolean isCompleted) {
        if (tasks.contains(task)) {
            if (isCompleted) {
                task.complete();
            }
        }
    }

    public void saveTasksToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(tasks);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadTasksFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            tasks = (List<Task>) ois.readObject();
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void sortTasks() {
        Collections.sort(tasks);
    }
}

// Класс ImmutableTask
final class ImmutableTask {
    private final String title;
    private final String description;
    private final boolean isCompleted;

    public ImmutableTask(String title, String description, boolean isCompleted) {
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    // Проблема: попытка изменить поля приведет к ошибке компиляции
    // public void setTitle(String title) { this.title = title; } // Ошибка компиляции

    @Override
    public String toString() {
        return "ImmutableTask{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}