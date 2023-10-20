import java.util.Scanner;

import utils.In;

public class University {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentController studentController = new StudentController(scanner);
        AdminController adminController = new AdminController(scanner);

        char choice;
        do {
            System.out.print("University System: (A)dmin, (S)tudent, or X : ");
            choice = In.nextChar();

            switch (choice) {
                case 'A':
                    adminController.run();
                    break;
                case 'S':
                    studentController.run();
                    break;
                case 'X':
                    System.out.println("Thank You");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again with the correct uppercased letter.");
            }
        } while (choice != 'X');
    }
}