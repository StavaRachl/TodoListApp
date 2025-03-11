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

    static void saveTask(ArrayList<Task> tasks) {
      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("task.txt"))) {
          oos.writeObject(tasks);
          System.out.println("Задачи сохранены.");
      } catch (IOException e) {
          System.out.println("Произошла ошибка " + e.getMessage());
      }
    }

    static ArrayList<Task> loadTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("task.txt"))) {
            tasks = (ArrayList<Task>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден, создание файла произойдет автоматически");
        } catch (Exception e) {
            System.out.println("Произошла ошибка " + e.getMessage());
        }
        return tasks;
    }
}