import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    String desc;
    boolean isCompleted;

    Task(String desc) {
        this.desc = desc;
        isCompleted = false;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[X] " : "[ ] ") + desc;
    }

    static void showTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст.");
        } else {
            System.out.println("Список задач:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    static void addTask(ArrayList<Task> tasks, Scanner scanner) {
        System.out.println("Введите описание задачи: ");
        String desc = scanner.nextLine();
        tasks.add(new Task(desc));
        System.out.println("Задача добавлена.");
    }

    static void markTaskCompleted(ArrayList<Task> tasks, Scanner scanner) {
        showTasks(tasks);
        if (!tasks.isEmpty()){
            System.out.println("Введите номер задачи: ");
            int taskNumber = scanner.nextInt();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                tasks.get(taskNumber - 1).isCompleted = true;
                System.out.println("Задача " + taskNumber + " помечена как выполненная.");
            } else {
                System.out.println("неверный номер задачи.");
            }
        }
    }
     static void deleteTask(ArrayList<Task> tasks, Scanner scanner) {
        showTasks(tasks);
        System.out.println("Введите номер задачи: ");
        int taskNumber = scanner.nextInt();
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            tasks.remove(taskNumber - 1);
            System.out.println("Задача " + taskNumber + " Удалена.");
        } else {
            System.out.println("Неверный номер задачи.");
        }
    }

    static void saveTask(ArrayList<Task> tasks, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task task:tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении задач в файл" + e.getMessage());
        }
    }

    String toFileString() {
        return (isCompleted ? "[X] " : "[ ] ") + "| " + desc;
    }


    static ArrayList<Task> loadTask(String filename) {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    static Task fromFileString(String fileString) {
        String[] parts = fileString.split("\\|", 2);
        boolean isCompleted = parts[0].equals("1");
        String desc = parts[1];
        Task task = new Task(desc);
        task.isCompleted = isCompleted;
        return task;
    }

    static void getMessages() {
        System.out.println("\n--- To-Do List ---");
        System.out.println("1. Показать задачи");
        System.out.println("2. Добавить задачу");
        System.out.println("3. Отметить задачу как выполненную");
        System.out.println("4. Удалить задачу");
        System.out.println("5. Сохранить задачу");
        System.out.println("6. Выйти");
        System.out.println("Выберите действие: ");
    }
}