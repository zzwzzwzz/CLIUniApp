import java.util.Scanner;
import utils.*;

public class University {
    // Text color
    public static final String RESET = "\033[0m";      // RESET
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String CYAN = "\033[0;96m";    // CYAN

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentController studentController = new StudentController(scanner);
        Database database = new Database(); // Order matters, create a Database instance
        Admin admin = new Admin(database, scanner);

        char choice;
        do {
            System.out.print(CYAN + "University System: (A)dmin, (S)tudent, or X : " + RESET);
            choice = In.nextChar();

            switch (choice) {
                case 'A':
                    admin.adminMenu();
                    break;
                case 'S':
                    studentController.menu();
                    break;
                case 'X':
                    System.out.println(YELLOW + "Thank You" + RESET);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 'X');
    }
}