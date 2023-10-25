import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdminController {
    private Database database;
    private Scanner scanner;

    public AdminController(Database database, Scanner scanner) {
        this.database = database;
        this.scanner = scanner;
    }

    public void runAdminMenu() {
        char choice;
        do {
            System.out.print("        Admin System (c/g/p/r/s/x): ");
            choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case 'c':
                    clearDatabase();
                    break;
                case 'g':
                    groupStudentsByGrade();
                    break;
                case 'p':
                    partitionStudentsByPassFail();
                    break;
                case 'r':
                    removeStudent();
                    break;
                case 's':
                    showStudents();
                    break;
                case 'x':
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (choice != 'x');
    }

    private void clearDatabase() {
        System.out.println("Clearing the database...");
        database.clearAll();
        System.out.println("Database cleared.");
    }

    private void groupStudentsByGrade() {
        // Implement the logic to group students by grade and display the results
        // You can retrieve the list of students from the database
    }

    private void partitionStudentsByPassFail() {
        // Implement the logic to partition students into pass/fail categories and display the results
        // You can retrieve the list of students from the database
    }

    private void removeStudent() {
        System.out.print("Enter the student's ID to remove: ");
        int studentID = Integer.parseInt(scanner.nextLine());
        boolean removed = database.removeStudent(studentID);
        if (removed) {
            System.out.println("Student with ID " + studentID + " has been removed.");
        } else {
            System.out.println("Student with ID " + studentID + " not found.");
        }
    }

    private void showStudents() {
        List<Student> students = database.readStudents();
        if (students.isEmpty()) {
            System.out.println("No students in the database.");
        } else {
            System.out.println("List of Students:");
            students.forEach(student -> System.out.println(student.toString()));
        }
    }

}

// import java.util.Scanner;

// public class AdminController {
//     public AdminController(Scanner scanner) {
//         // Initialize and configure
//     }

//     public void run() {
//         // Implement menu and corresponding functionality
//         System.out.println("        Admin System (c/g/p/r/s/x): ");
//     }

//     // Implement methods for user stories 701, 702, 703, 801, 802, 803, 901, 902, 903, 1001, 1002, and 1003
// }