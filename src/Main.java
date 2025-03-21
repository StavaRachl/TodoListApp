import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Task> tasks;
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        tasks = Task.loadTask("tasks.txt");

        while (run) {
            Task.getMessages();

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