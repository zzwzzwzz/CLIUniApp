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

    // Constructor
    public StudentController(Scanner scanner) {
        database = new Database();
        database.initialize();
        students = database.readStudents();
    }

    // Define email verification method
    // Email must have . before @ and must end with university.com
    public static final String EMAIL_REGEX = "^[A-Za-z]+\\.[A-Za-z]+@university\\.com$"; 
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

    // Student register
    public void register(Scanner scanner) {
        System.out.printf(GREEN + "%-8sStudent Sign Up" + RESET,"").println();

        while (true) {
            // Email and password input from the registering student
            System.out.printf("%-8sEmail: ","");
            String email = scanner.nextLine();
    
            System.out.printf("%-8sPassword: ","");
            String password = scanner.nextLine();
    
            // To check if both email and password are the right format
            if (isValidEmail(email) && isValidPassword(password)) {
                System.out.printf(YELLOW + "%-8semail and password formats acceptable" + RESET,"").println();
                
                // To check if the student with the email already exists
                boolean exists = false;
                for (Student student : students) {
                    if (student.getEmail().equals(email)) {
                        exists = true;
                        System.out.printf(RED + "%-8sStudent " + student.getName() + " already exists" + RESET, "").println();
                        break;
                    }
                }

                // If the students doesn't exist, create a new student to the database
                if (!exists) {
                    // Input name from the registering students
                    System.out.printf("%-8sName: ", "");
                    String name = scanner.nextLine();
                    System.out.printf(YELLOW + "%-8sEnrolling student %s" + RESET, "", name).println();

                    // Create the new student and add them to the database
                    Student newStudent = new Student(name, email, password, students);
                    students.add(newStudent);
                    Database.writeStudents(students);
                }
                break;

            } else {
                System.out.printf(RED + "%-8sIncorrect email or password format" + RESET,"").println();
            }
        }
    }

    // Student login 
    public boolean login(Scanner scanner) {
        System.out.printf(GREEN + "%-8sStudent Sign In" + RESET,"").println();

        // Input email for login
        System.out.printf("%-8sEmail: ","");
        String email = scanner.nextLine();
    
        // Input password for login
        System.out.printf("%-8sPassword: ","");
        String password = scanner.nextLine();
    
        // Setting false means student has not logged in yet.
        boolean loggedIn = false;

        // Verify if the email and password is valid
        if (isValidEmail(email) && isValidPassword(password)) {
            System.out.printf(YELLOW + "%-8semail and password formats acceptable" + RESET,"").println();
            List<Student> students = database.readStudents();
            
            // To check if the email and password already exists
            for (Student student : students) {
                if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                    // Student is found and the provided email and password match
                    loggedIn = true;
                    new SubjectController(database, scanner, student).menu();
                    break; // Successful login, exit the loop
                }
            }

            // No matching student is found
            if (!loggedIn) {
                System.out.printf(RED + "%-8sStudent does not exist" + RESET,"").println();
            }

        } else {
            System.out.printf(RED + "%-8sIncorrect email or password format" + RESET,"").println();
        }

        // Boolean needs to have a return value, true or false
        return loggedIn;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.printf(CYAN + "%-8sStudent System (l/r/x): " + RESET,"");
            String choice = scanner.nextLine().trim();

            // choice.toLowerCase() make sure the input choice is valid lowercase
            switch (choice.toLowerCase()) {
                case "l":
                    login(scanner);
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
}