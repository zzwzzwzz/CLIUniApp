import java.util.List;
import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;

public class StudentController {
    private Database database;
    private List<Student> students;
    private Student currentUser;

    public StudentController(Scanner scanner) {
        database = new Database();
        database.initialize();
        students = database.readStudents();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.print("        Student System (l/r/x): ");
            String choice = scanner.nextLine().trim();

            switch (choice.toLowerCase()) {
                case "l":
                    login();
                    break;
                case "r":
                    register();
                    break;
                case "x":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public boolean login() {
        // Implement the login logic here
        System.out.println("        Login Function");
        return false;
    }

    public void register() {
        // Implement the register logic here
        System.out.println("        Register Function");
    }

    public static void main(String[] args) {
        StudentController controller = new StudentController(null);
        controller.run();
    }
}




// import java.util.Scanner;

// public class StudentController {
//     public StudentController(Scanner scanner) {
//         // Initialize and configure
//     }

//     public void run() {
//         // Implement menu and corresponding functionality
//         System.out.println("        Student System (l/r/x): ");
//     }

//     // Implement methods for user stories 101, 201, 202, 203, 301, 401, 402, 403, 501, 601, 602, and 603
// }