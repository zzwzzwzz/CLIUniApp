import java.util.Scanner;
import utils.*;

public class University {
    public static final String YELLOW = "\u001B[33m"; 
    public static final String RESET = "\033[0m"; // Text Reset
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentController studentController = new StudentController(scanner);
        AdminController adminController = new AdminController(null, scanner);

        char choice;
        do {
            System.out.print("University System: (A)dmin, (S)tudent, or X : ");
            choice = In.nextChar();

            switch (choice) {
                case 'A':
                    adminController.runAdminMenu();
                    break;
                case 'S':
                    studentController.run();
                    break;
                case 'X':
                    System.out.println(YELLOW +"Thank You" + RESET);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again with the correct uppercased letter.");
            }
        } while (choice != 'X');
    }
}