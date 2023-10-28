import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentController {
    private Database database;
    private List<Student> students;

    // Text color
    public static final String RESET = "\033[0m";      // RESET
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String CYAN = "\033[0;96m";    // CYAN

    public static void main(String[] args) {
        StudentController controller = new StudentController(null);
        controller.run();
    }

    public StudentController(Scanner scanner) {
        database = new Database();
        database.initialize();
        students = database.readStudents();
        this.students = new ArrayList<>();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.printf(CYAN + "%-8sStudent System (l/r/x): " + RESET,"");
            String choice = scanner.nextLine().trim();

            switch (choice.toLowerCase()) {
                case "l":
                    login();
                    break;
                case "r":
                    register(scanner);
                    break;
                case "x":
                    exit = true;
                    break;
                default:
                    System.out.printf(RED + "%-8sInvalid choice. Please try again." + RESET,"").println();
            }
        }
    }

    public boolean login() {
        System.out.printf("%-8sLogin Function","").println();
        return false;
    }

    public void register(Scanner scanner) {
        System.out.printf(GREEN + "%-8sStudent Sign Up" + RESET,"").println();

        while (true) {
            // Input from the registering student
            System.out.printf("%-8sEmail: ","");
            String email = scanner.nextLine();
    
            System.out.printf("%-8sPassword: ","");
            String password = scanner.nextLine();
    
            // To check if both email and password are in the right format
            if (isValidEmail(email) && isValidPassword(password)) {
                boolean exists = false;

                // To check if the student with the email already exists
                List<Student> students = database.readStudents();
                for (Student student : students) {
                    if (student.getEmail().equals(email)) {
                        exists = true;
                        System.out.printf(RED + "Student" + student.getName() + " already exists." + RESET,"").println();;
                        break;
                    }
                }

                // If the students doesn't exist, create a new student to the database
                if (!exists) {
////TO DO
                    //Write the updated list back to the database file
                    database.writeStudents(students);
                    System.out.printf(YELLOW + "%-8semail and password formats acceptable" + RESET,"").println();
                    break; // Registration successful, exit the loop
                }
            } else {
                System.out.printf(RED + "%-8sIncorrect email or password format" + RESET,"").println();
            }
        }
    }

    // Define email verification method
    // Email must have . before @ and must end with university.com
    public static final String EMAIL_REGEX = ".*\\..*@.*university\\.com"; 
    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    /**
     * Define password verification methods
     * 
     * Password must start with an uppercase letter, 
     * have a minimum length of 6 characters, 
     * and be followed by at least 3 digits.
     */
    public static final String PASSWORD_REGEX = "^[A-Z][A-Za-z]{6,}[0-9]{3,}$"; 
    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }
}