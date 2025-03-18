import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Task> tasks;
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        tasks = Task.loadTask("tasks.txt");

        while (run) {
            System.out.println("\n--- To-Do List ---");
            System.out.println("1. Показать задачи");
            System.out.println("2. Добавить задачу");
            System.out.println("3. Отметить задачу как выполненную");
            System.out.println("4. Удалить задачу");
            System.out.println("5. Сохранить задачу");
            System.out.println("6. Выйти");
            System.out.println("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                Task.showTasks(tasks);
            } else if (choice == 2) {
                Task.addTask(tasks, scanner);
            } else if (choice == 3) {
                Task.markTaskCompleted(tasks, scanner);
            } else if (choice == 4) {
                Task.deleteTask(tasks, scanner);
            } else if (choice == 5) {
                Task.saveTask(tasks, "tasks.txt");
            } else if (choice == 6){
                Task.saveTask(tasks, "tasks.txt");
                System.out.println("Выход из приложения...");
                run = false;
            } else {
                System.out.println("Неверный ввод. Попробуйте снова.");
            }
        }
        scanner.close();
    }
}